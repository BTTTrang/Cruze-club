package com.test.cruzeclub.ui.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.test.cruzeclub.Cruze;
import com.test.cruzeclub.CruzeHolderAPI;
import com.test.cruzeclub.GlobalVar;
import com.test.cruzeclub.R;
import com.test.cruzeclub.databinding.FragmentHomeBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView;
        recyclerView = root.findViewById(R.id.homerecyclerview);

        ArrayList<Cruze> cruzeList = new ArrayList<>();
        HomeAdapter homeAdapter = new HomeAdapter(root.getContext(), cruzeList);

        LinearLayoutManager layoutManager  = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(homeAdapter);

        GlobalVar.service.getAllPlate().enqueue(new Callback<List<Cruze>>() {
            @Override
            public void onResponse(Call<List<Cruze>> call, Response<List<Cruze>> response) {
                cruzeList.addAll(response.body());
                homeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Cruze>> call, Throwable t) {

            }
        });


        EditText txtSearchPlate = (EditText) root.findViewById(R.id.txtSearchPlate);

        txtSearchPlate.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    GlobalVar.service.searchWithPlate(txtSearchPlate.getText().toString()).enqueue(new Callback<List<Cruze>>() {
                        @Override
                        public void onResponse(Call<List<Cruze>> call, Response<List<Cruze>> response) {
                            if (response.body() == null) {
                                GlobalVar.service.getAllPlate().enqueue(new Callback<List<Cruze>>() {
                                    @Override
                                    public void onResponse(Call<List<Cruze>> call, Response<List<Cruze>> response) {
                                        cruzeList.clear();
                                        cruzeList.addAll(response.body());
                                        homeAdapter.notifyDataSetChanged();
                                    }

                                    @Override
                                    public void onFailure(Call<List<Cruze>> call, Throwable t) {

                                    }
                                });

                            }
                            else {
                                cruzeList.clear();
                                cruzeList.addAll(response.body());
                                homeAdapter.notifyDataSetChanged();
                            }

                        }

                        @Override
                        public void onFailure(Call<List<Cruze>> call, Throwable t) {

                        }
                    });
                    return true;
                }
                return false;
            }
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }




}


class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {


    ArrayList<Cruze> tables;
    LayoutInflater inflater;

    public HomeAdapter(Context ctx, ArrayList<Cruze> tables) {
        this.tables = tables;
        this.inflater = LayoutInflater.from(ctx);
    }


    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.registered_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {

        holder.txtFullName.setText(tables.get(position).txtFullName);
        holder.txtAddress.setText("Địa chỉ: " + tables.get(position).txtAddress);
        holder.txtFBName.setText("Tên fb: " +tables.get(position).txtFBName);
        holder.txtPhone.setText("Sđt: " + tables.get(position).txtPhone);
        holder.txtGender.setText("Giới tính: " + tables.get(position).txtGender);
        holder.txtStamp.setText("Tem số: "+ tables.get(position).txtStamp);
        holder.txtPlate.setText("Biển số xe: "+ tables.get(position).txtPlate);
    }

    @Override
    public int getItemCount() {
        return tables.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtFullName;
        TextView txtFBName;
        TextView txtPhone;
        TextView txtAddress;
        TextView txtGender;
        TextView txtStamp;
        TextView txtPlate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtFullName = itemView.findViewById(R.id.txtFullName);
            txtFBName = itemView.findViewById(R.id.txtFBName);
            txtPhone = itemView.findViewById(R.id.txtPhone);
            txtAddress = itemView.findViewById(R.id.txtAddress);
            txtGender = itemView.findViewById(R.id.txtGender);
            txtStamp = itemView.findViewById(R.id.txtStamp);
            txtPlate = itemView.findViewById(R.id.txtPlate);
        }
    }
}
