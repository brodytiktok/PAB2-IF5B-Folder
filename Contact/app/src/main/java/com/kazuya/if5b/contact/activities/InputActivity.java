package com.kazuya.if5b.contact.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.kazuya.if5b.contact.R;
import com.kazuya.if5b.contact.databinding.ActivityMainBinding;

public class InputActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
    }
}