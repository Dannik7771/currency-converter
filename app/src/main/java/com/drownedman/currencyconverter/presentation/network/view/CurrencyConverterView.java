package com.drownedman.currencyconverter.presentation.network.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

import java.util.List;

public class CurrencyConverterView extends Fragment implements TextWatcher, AdapterView.OnItemSelectedListener {
    CurrencyConverterViewModel viewModel;
    CurrencyConverterFragmentBinding binding;

    ArrayAdapter<String> codesAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = CurrencyConverterFragmentBinding.inflate(getLayoutInflater(), container, false);

        viewModel = new ViewModelProvider(this).get(CurrencyConverterViewModel.class);

        viewModel.getCurrencyCodes(getViewLifecycleOwner()).observe(getViewLifecycleOwner(), new Observer<List<CurrencyValue>>() {
            @Override
            public void onChanged(List<CurrencyValue> currencyValues) {
                viewModel.updateCurrencyCodes(currencyValues);
                codesAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,
                        AppService.getInstance().getCodes());
                codesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                binding.firstCode.setAdapter(codesAdapter);
                binding.secondCode.setAdapter(codesAdapter);
            }
        });

        binding.firstValue.addTextChangedListener(this);

        binding.firstCode.setOnItemSelectedListener(this);
        binding.secondCode.setOnItemSelectedListener(this);

        //Update Currencies
        binding.updateCurrenciesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.getCurrencyCodes(getViewLifecycleOwner()).observe(getViewLifecycleOwner(), new Observer<List<CurrencyValue>>() {
                    @Override
                    public void onChanged(List<CurrencyValue> currencyValues) {
                        viewModel.updateCurrencyCodes(currencyValues);
                        convertAndShow();
                    }
                });
            }
        });

        return binding.getRoot();
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        convertAndShow();
    }

    private void convertAndShow() {
        if (!binding.firstValue.getText().toString().isEmpty()
                && !binding.firstCode.getSelectedItem().toString().isEmpty()
                && !binding.secondCode.getSelectedItem().toString().isEmpty())
            binding.secondValue.setText(String.valueOf(
                    viewModel.convertValue(Double.parseDouble(binding.firstValue.getText().toString()),
                            binding.firstCode.getSelectedItem().toString(),
                            binding.secondCode.getSelectedItem().toString())));
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        convertAndShow();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
