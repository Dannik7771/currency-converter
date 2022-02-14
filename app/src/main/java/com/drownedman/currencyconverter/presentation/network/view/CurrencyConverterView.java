package com.drownedman.currencyconverter.presentation.network.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.drownedman.currencyconverter.databinding.CurrencyConverterFragmentBinding;
import com.drownedman.currencyconverter.presentation.network.viewModel.CurrencyConverterViewModel;

public class CurrencyConverterView extends Fragment {
    CurrencyConverterViewModel viewModel;
    CurrencyConverterFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = CurrencyConverterFragmentBinding.inflate(getLayoutInflater(), container, false);

        viewModel = new ViewModelProvider(this).get(CurrencyConverterViewModel.class);



        return binding.getRoot();
    }
}
