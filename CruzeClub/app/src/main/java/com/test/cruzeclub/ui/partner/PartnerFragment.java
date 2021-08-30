package com.test.cruzeclub.ui.partner;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.test.cruzeclub.GlobalVar;
import com.test.cruzeclub.Mien;
import com.test.cruzeclub.R;
import com.test.cruzeclub.databinding.FragmentPartnerBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PartnerFragment extends Fragment {
    private FragmentPartnerBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPartnerBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView;
        recyclerView = root.findViewById(R.id.customrecyclerview2);


        Button doxe = root.findViewById(R.id.doxe);
        Button baohiem = root.findViewById(R.id.baohiem);



        ArrayList<Mien> cruzeList = new ArrayList<>();
        PartnerAdapter homeAdapter = new PartnerAdapter(root.getContext(), cruzeList);

        LinearLayoutManager layoutManager  = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(homeAdapter);

        GlobalVar.service.getPartner("doxe").enqueue(new Callback<List<Mien>>() {
            @Override
            public void onResponse(Call<List<Mien>> call, Response<List<Mien>> response) {
                cruzeList.addAll(response.body());
                homeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Mien>> call, Throwable t) {

            }
        });
        doxe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doxe.setBackgroundColor(getResources().getColor(R.color.purple_500));
                baohiem.setBackgroundColor(getResources().getColor(R.color.white));
                doxe.setTextColor(getResources().getColor(R.color.white));
                baohiem.setTextColor(getResources().getColor(R.color.purple_500));

                GlobalVar.service.getPartner("doxe").enqueue(new Callback<List<Mien>>() {
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
        baohiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doxe.setBackgroundColor(getResources().getColor(R.color.white));
                baohiem.setBackgroundColor(getResources().getColor(R.color.purple_500));
                doxe.setTextColor(getResources().getColor(R.color.purple_500));
                baohiem.setTextColor(getResources().getColor(R.color.white));
                GlobalVar.service.getPartner("baohiem").enqueue(new Callback<List<Mien>>() {
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
}

class PartnerAdapter extends RecyclerView.Adapter<PartnerAdapter.ViewHolder> {
    ArrayList<Mien> tables;
    LayoutInflater inflater;

    public PartnerAdapter(Context ctx, ArrayList<Mien> tables) {
        this.tables = tables;
        this.inflater = LayoutInflater.from(ctx);
    }


    @NonNull
    @NotNull
    @Override
    public PartnerAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.mien_item, parent, false);
        return new PartnerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PartnerAdapter.ViewHolder holder, int position) {
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