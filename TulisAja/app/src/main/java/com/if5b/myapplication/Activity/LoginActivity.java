package com.if5b.myapplication.Activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.if5b.myapplication.Models.ValueNoData;
import com.if5b.myapplication.Services.APIService;
import com.if5b.myapplication.Services.Utilities;
import com.if5b.myapplication.databinding.ActivityLoginBinding;
import com.if5b.myapplication.databinding.ActivityMainBinding;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.internal.Util;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = binding.etUsername.getText().toString();
                String password = binding.etPassword.getText().toString();

                boolean bolehLogin = true;
                if (TextUtils.isEmpty(username)) {
                    bolehLogin = false;
                    binding.etUsername.setError("Username tidak boleh kosong !");
                }
                if (TextUtils.isEmpty(password)) {
                    bolehLogin = false;
                    binding.etPassword.setError("Password tidak boleh kosong !");
                }
                if (bolehLogin) {
                    login(username,password);
                }
            }

            private void login(String username, String password) {
                showProgressBar();
                APIService api = Utilities.getRetrofit().create(APIService.class);
                api.login(Utilities.TULIS_AJA_APIKEY, username, password).enqueue(new Callback<ValueNoData>(){

                    @Override
                    public void onResponse(Call<ValueNoData> call, Response<ValueNoData> response) throws IOException {

                    }

                    @Override
                    public void onFailure(Call call, IOException e) {

                    }
                });
            }
        });
    }

    private void showProgressBar() {
    }
}
