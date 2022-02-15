package com.drownedman.currencyconverter.presentation.network.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.drownedman.currencyconverter.databinding.CurrencyConverterFragmentBinding;
import com.drownedman.currencyconverter.domain.model.CurrencyValue;
import com.drownedman.currencyconverter.presentation.network.viewModel.CurrencyConverterViewModel;
import com.drownedman.currencyconverter.presentation.repository.AppService;

import java.util.ArrayList;
import java.util.List;

public class CurrencyConverterView extends Fragment {
    CurrencyConverterViewModel viewModel;
    CurrencyConverterFragmentBinding binding;

    ArrayAdapter<String> codesAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = CurrencyConverterFragmentBinding.inflate(getLayoutInflater(), container, false);

        viewModel = new ViewModelProvider(this).get(CurrencyConverterViewModel.class);

        viewModel.getAllCurrencyCodes(getViewLifecycleOwner()).observe(getViewLifecycleOwner(), new Observer<List<CurrencyValue>>() {
            @Override
            public void onChanged(List<CurrencyValue> currencyValues) {
                //TODO: переделать
                AppService.getInstance().updateCodes(currencyValues);

                codesAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,
                        AppService.getInstance().getCodes());
                codesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                binding.firstCode.setAdapter(codesAdapter);
                binding.secondCode.setAdapter(codesAdapter);
            }
        });

        binding.updateCurrenciesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.getAllCurrencyCodes(getViewLifecycleOwner()).observe(getViewLifecycleOwner(), new Observer<List<CurrencyValue>>() {
                    @Override
                    public void onChanged(List<CurrencyValue> currencyValues) {
                        System.out.println(currencyValues.get(0).getCode());
                    }
                });
            }
        });

        return binding.getRoot();
    }
}
