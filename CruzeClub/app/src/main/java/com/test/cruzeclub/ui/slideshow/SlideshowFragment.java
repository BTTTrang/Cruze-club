package com.test.cruzeclub.ui.slideshow;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.test.cruzeclub.Cruze;
import com.test.cruzeclub.GlobalVar;
import com.test.cruzeclub.Mien;
import com.test.cruzeclub.R;
import com.test.cruzeclub.databinding.FragmentSlideshowBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    private FragmentSlideshowBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button mienbac = root.findViewById(R.id.mienbac);
        Button mientrung = root.findViewById(R.id.mientrung);
        Button miennam = root.findViewById(R.id.miennam);




        RecyclerView recyclerView;
        recyclerView = root.findViewById(R.id.customrecyclerview1);

        ArrayList<Mien> cruzeList = new ArrayList<>();
        SlideShowAdapter homeAdapter = new SlideShowAdapter(root.getContext(), cruzeList);

        LinearLayoutManager layoutManager  = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(homeAdapter);
        GlobalVar.service.getAgency("mienbac").enqueue(new Callback<List<Mien>>() {
            @Override
            public void onResponse(Call<List<Mien>> call, Response<List<Mien>> response) {
                cruzeList.addAll(response.body());
                homeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Mien>> call, Throwable t) {

            }
        });
        mienbac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mienbac.setBackgroundColor(getResources().getColor(R.color.purple_500));
                mientrung.setBackgroundColor(getResources().getColor(R.color.white));
                miennam.setBackgroundColor(getResources().getColor(R.color.white));
                mienbac.setTextColor(getResources().getColor(R.color.white));
                mientrung.setTextColor(getResources().getColor(R.color.purple_500));
                miennam.setTextColor(getResources().getColor(R.color.purple_500));
                GlobalVar.service.getAgency("mienbac").enqueue(new Callback<List<Mien>>() {
                    @Override
                    public void onResponse(Call<List<Mien>> call, Response<List<Mien>> response) {
                        cruzeList.clear();
                        cruzeList.addAll(response.body());
                        homeAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<List<Mien>> call, Throwable t) {

                    }
                });
            }
        });

        mientrung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mienbac.setBackgroundColor(getResources().getColor(R.color.white));
                mientrung.setBackgroundColor(getResources().getColor(R.color.purple_500));
                miennam.setBackgroundColor(getResources().getColor(R.color.white));
                mienbac.setTextColor(getResources().getColor(R.color.purple_500));
                mientrung.setTextColor(getResources().getColor(R.color.white));
                miennam.setTextColor(getResources().getColor(R.color.purple_500));
                GlobalVar.service.getAgency("mientrung").enqueue(new Callback<List<Mien>>() {
                    @Override
                    public void onResponse(Call<List<Mien>> call, Response<List<Mien>> response) {
                        cruzeList.clear();
                        cruzeList.addAll(response.body());
                        homeAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<List<Mien>> call, Throwable t) {

                    }
                });
            }
        });

        miennam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mienbac.setBackgroundColor(getResources().getColor(R.color.white));
                mientrung.setBackgroundColor(getResources().getColor(R.color.white));
                miennam.setBackgroundColor(getResources().getColor(R.color.purple_500));
                mienbac.setTextColor(getResources().getColor(R.color.purple_500));
                mientrung.setTextColor(getResources().getColor(R.color.purple_500));
                miennam.setTextColor(getResources().getColor(R.color.white));
                GlobalVar.service.getAgency("miennam").enqueue(new Callback<List<Mien>>() {
                    @Override
                    public void onResponse(Call<List<Mien>> call, Response<List<Mien>> response) {
                        cruzeList.clear();
                        cruzeList.addAll(response.body());
                        homeAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<List<Mien>> call, Throwable t) {

                    }
                });
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

class SlideShowAdapter extends RecyclerView.Adapter<SlideShowAdapter.ViewHolder> {
    ArrayList<Mien> tables;
    LayoutInflater inflater;

    public SlideShowAdapter(Context ctx, ArrayList<Mien> tables) {
        this.tables = tables;
        this.inflater = LayoutInflater.from(ctx);
    }


    @NonNull
    @NotNull
    @Override
    public SlideShowAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.mien_item, parent, false);
        return new SlideShowAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull SlideShowAdapter.ViewHolder holder, int position) {
        holder.btnCall1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+tables.get(position).hotline));
                v.getContext().startActivity(intent);
            }
        });
        holder.txtAgencyName.setText(tables.get(position).agencyname);
        holder.txtAgencyAddress.setText("Địa chỉ: " + tables.get(position).agencyaddress);
        holder.txtHotline.setText("Tên fb: " +tables.get(position).hotline);
    }

    @Override
    public int getItemCount() {
        return tables.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtAgencyName;
        TextView txtAgencyAddress;
        TextView txtHotline;
        Button btnCall1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtAgencyName = itemView.findViewById(R.id.txtAgencyName);
            txtAgencyAddress = itemView.findViewById(R.id.txtAgencyAddress);
            txtHotline = itemView.findViewById(R.id.txtAgencyHotline);
            btnCall1 = itemView.findViewById(R.id.btnCall1);
        }
    }
}



