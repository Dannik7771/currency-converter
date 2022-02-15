package com.drownedman.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.drownedman.currencyconverter.databinding.ActivityMainBinding;
import com.drownedman.currencyconverter.presentation.repository.AppService;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppService.getInstance().initLocalRepository(getApplication());

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}