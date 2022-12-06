package com.if5b.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.if5b.myapplication.Models.ValueNoData;
import com.if5b.myapplication.Services.APIService;
import com.if5b.myapplication.Services.Utilities;
import com.if5b.myapplication.databinding.ActivityMainBinding;
import com.if5b.myapplication.databinding.ActivityRegisterBinding;

import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;
    private Button btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String username = binding.etUsername.getText().toString();
                String password = binding.etPassword.getText().toString();
                String konfirmasiPassword = binding.etKonfirmasiPassword.getText().toString();
                boolean bolehRegister = true;

                if (TextUtils.isEmpty(username)) {
                    bolehRegister = false;
                    binding.etUsername.setError("Username tidak boleh kosong");
                }
                if (TextUtils.isEmpty(password)) {
                    bolehRegister = false;
                    binding.etPassword.setError("Password tidak boleh kosong");
                }
                if (password.length() < 6) {
                    bolehRegister = false;
                    binding.etKonfirmasiPassword.setError("Password minimal 6 karakter!");
                }
                if (bolehRegister) {
                    register(username, password);
                }
            }



            private void register(String username, String password) {
                showProgressBar();
                APIService api = Utilities.getRetrofit().create(APIService.class);
                api.register(Utilities.API_KEY, username, password).enqueue(new Callback<ValueNoData>() {
                    @Override
                    public void onResponse(Call<ValueNoData> call, Response<ValueNoData> response) {
                        if (response.code() == 200) {
                            int success = response.body().getSuccess();
                            String message = response.body().getMessage();
                            if (success == 1) {
                                Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(RegisterActivity.this, "Response Code", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(RegisterActivity.this, "AA", Toast.LENGTH_SHORT).show();
                        }
                        hideProgressBar();
                    }

                    @Override
                    public void onFailure(Call<ValueNoData> call, Throwable t) {

                    }
                });
            }
        });
    }

    private void showProgressBar() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        binding.progressBar.setVisibility(View.GONE);
    }
}