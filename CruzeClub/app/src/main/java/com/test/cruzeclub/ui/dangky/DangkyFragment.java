package com.test.cruzeclub.ui.dangky;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.test.cruzeclub.Cruze;
import com.test.cruzeclub.GlobalVar;
import com.test.cruzeclub.R;
import com.test.cruzeclub.databinding.FragmentDangkyBinding;
import com.test.cruzeclub.databinding.FragmentIntroBinding;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;
import static androidx.core.content.PermissionChecker.checkSelfPermission;


public class DangkyFragment extends Fragment {
    static final int REQUEST_TAKE_PHOTO = 1;
    private ImageView imageViewUploadedPlate;
    private boolean imageUploadExist = false;
    private FragmentDangkyBinding binding;
    private Bitmap bitmap;
    private Bitmap bitmapFinal;
    private Uri photoURI;
    String mCurrentPhotoPath;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDangkyBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        TextView txtInsertFullName = root.findViewById(R.id.txtInsertFullName);
        TextView txtInsertFBName = root.findViewById(R.id.txtInsertFBName);
        TextView txtInsertPhone = root.findViewById(R.id.txtInsertPhone);
        TextView txtInsertAddress = root.findViewById(R.id.txtInsertAddress);
        TextView txtInsertStamp = root.findViewById(R.id.txtInsertStamp);
        TextView txtInsertPlate = root.findViewById(R.id.txtInsertPlate);
        TextView txtInsertGender = root.findViewById(R.id.txtInsertFBName);

        AlertDialog alertDialog = new AlertDialog.Builder(root.getContext()).setCancelable(true).create();
        alertDialog.setTitle("Thông báo");


        imageViewUploadedPlate = root.findViewById(R.id.imageViewUploadedPlate);

        imageViewUploadedPlate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                    File photoFile = null;
                    try {
                        photoFile = createImageFile();
                    } catch (IOException ex) {

                    }
                    if (photoFile != null) {
                        photoURI = FileProvider.getUriForFile(getContext(),
                                "com.example.android.fileprovider",
                                photoFile);

                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                        startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);

                    }
                }
            }
        });




        Button btnDangKy = root.findViewById(R.id.btnDangKy);
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtInsertFullName.getText().toString().trim() != "" && txtInsertFBName.getText().toString().trim()  != "" && txtInsertPhone.getText().toString().trim() != "" && txtInsertAddress.getText().toString().trim() != "" && txtInsertGender.getText().toString().trim() != "" && txtInsertStamp.getText().toString().trim() != "" && txtInsertPlate.getText().toString().trim() != "" && imageUploadExist){
                    //                File file = new File(photoURI.getPath());
                    ProgressDialog dialog = ProgressDialog.show(getContext(), "",
                            "Loading. Please wait...", true);
                    File file = new File(getContext().getCacheDir(), System.currentTimeMillis()+"_profile.jpg");
                    OutputStream os = null;
                    try {
                        file.createNewFile();
                        os = new BufferedOutputStream(new FileOutputStream(file));
                        bitmapFinal.compress(Bitmap.CompressFormat.JPEG, 100, os);
                        os.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
                    MultipartBody.Part body = MultipartBody.Part.createFormData("plateimage", file.getName(), reqFile);

                    RequestBody fullname = RequestBody.create(MediaType.parse("text/plain"), txtInsertFullName.getText().toString());
                    RequestBody fb_name = RequestBody.create(MediaType.parse("text/plain"), txtInsertFBName.getText().toString());
                    RequestBody phone = RequestBody.create(MediaType.parse("text/plain"), txtInsertPhone.getText().toString());
                    RequestBody address = RequestBody.create(MediaType.parse("text/plain"), txtInsertAddress.getText().toString());
                    RequestBody gender = RequestBody.create(MediaType.parse("text/plain"), txtInsertGender.getText().toString());
                    RequestBody stamp = RequestBody.create(MediaType.parse("text/plain"), txtInsertStamp.getText().toString());
                    RequestBody plate = RequestBody.create(MediaType.parse("text/plain"), txtInsertPlate.getText().toString());


                    GlobalVar.service.createTicket(fullname,fb_name,phone,address,gender,stamp,plate,body).enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            dialog.dismiss();
                            alertDialog.setTitle("Đăng ký thành công");
                            alertDialog.setMessage("Vui lòng đợi để đơn đăng ký của bạn được duyệt");
                            alertDialog.show();
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            System.out.println(t.getMessage());
                            dialog.dismiss();
                            alertDialog.setTitle("Thông báo");
                            alertDialog.setMessage("Có lỗi xảy ra");
                            alertDialog.show();
                        }
                    });

                }
                else {
                    alertDialog.setTitle("Thông báo");
                    alertDialog.setMessage("Bạn phải nhập đủ trường để đăng ký!!!");
                    alertDialog.show();
                }

            }
        });

        return root;
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK && data != null) {
//            Uri uri = data.getData();
////            f = new File(getContext().getCacheDir(), System.currentTimeMillis()+"_profile.jpg");
////            Bundle bundle = data.getExtras();
////            Uri uri = data.getData();
////            Bitmap bitmap = null;
////            try {
////                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
////            bitmapFinal = (Bitmap) bitmap;
////            imageViewUploadedPlate.setImageBitmap(bitmapFinal);
////            imageUploadExist = true;
//        }
//
//    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            imageUploadExist = true;
            Toast.makeText(getContext(), "Image saved", Toast.LENGTH_SHORT).show();

            bitmapFinal = null;
            try {
                bitmapFinal = MediaStore.Images.Media.getBitmap(this.getActivity().getContentResolver(), photoURI);
                imageViewUploadedPlate.setImageBitmap(bitmapFinal);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".png",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }
}