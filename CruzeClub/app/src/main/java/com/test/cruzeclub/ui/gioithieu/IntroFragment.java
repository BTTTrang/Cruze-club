package com.test.cruzeclub.ui.gioithieu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.cruzeclub.GlobalVar;
import com.test.cruzeclub.R;
import com.test.cruzeclub.databinding.FragmentIntroBinding;
import com.test.cruzeclub.databinding.FragmentPartnerBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class IntroFragment extends Fragment {
    private FragmentIntroBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentIntroBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        TextView txt_intro = root.findViewById(R.id.txt_intro);
        GlobalVar.service.getInfo().enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                txt_intro.setText(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

        return root;
    }

}