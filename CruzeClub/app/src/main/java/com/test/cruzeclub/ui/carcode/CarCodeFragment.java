package com.test.cruzeclub.ui.carcode;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.test.cruzeclub.CarCode;
import com.test.cruzeclub.Cruze;
import com.test.cruzeclub.GlobalVar;
import com.test.cruzeclub.R;
import com.test.cruzeclub.databinding.FragmentGalleryBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarCodeFragment extends Fragment {

    private CarCodeViewModel carCodeViewModel;
    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        carCodeViewModel =
                new ViewModelProvider(this).get(CarCodeViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView;
        recyclerView = root.findViewById(R.id.carcoderecyclerview);

        ArrayList<CarCode> cruzeList = new ArrayList<>();
        CarCodeAdapter carcodeAdapter = new CarCodeAdapter(root.getContext(), cruzeList);

        LinearLayoutManager layoutManager  = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(carcodeAdapter);

        GlobalVar.service.getCarCodes().enqueue(new Callback<List<CarCode>>() {
            @Override
            public void onResponse(Call<List<CarCode>> call, Response<List<CarCode>> response) {
                cruzeList.addAll(response.body());
                carcodeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<CarCode>> call, Throwable t) {

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

class CarCodeAdapter extends RecyclerView.Adapter<CarCodeAdapter.ViewHolder> {


    ArrayList<CarCode> tables;
    LayoutInflater inflater;

    public CarCodeAdapter(Context ctx, ArrayList<CarCode> tables) {
        this.tables = tables;
        this.inflater = LayoutInflater.from(ctx);
    }


    @NonNull
    @NotNull
    @Override
    public CarCodeAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.code_item, parent, false);
        return new CarCodeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CarCodeAdapter.ViewHolder holder, int position) {

        holder.txtCodeName.setText(tables.get(position).codeName);
        holder.txtCodeDes.setText("Địa chỉ: " + tables.get(position).codeDes);
    }

    @Override
    public int getItemCount() {
        return tables.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtCodeName;
        TextView txtCodeDes;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCodeName = itemView.findViewById(R.id.txtCodeName);
            txtCodeDes = itemView.findViewById(R.id.txtCodeDes);

        }
    }
}