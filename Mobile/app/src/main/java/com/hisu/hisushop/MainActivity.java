package com.hisu.hisushop;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.hisu.hisushop.api.IApiService;
import com.hisu.hisushop.databinding.ActivityMainBinding;
import com.hisu.hisushop.entity.Customer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mMainBinding;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mMainBinding.getRoot());

        mMainBinding.btnLogin.setOnClickListener(view -> getCustomerFromApi());
    }

    private void getCustomerFromApi() {
        dialog.setMessage("Loading...");
        dialog.show();

        IApiService.API_SERVICE.getCustomerByCustomerID(4017).enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                dialog.dismiss();
                Customer customer = response.body();
                if (customer != null) {
                    mMainBinding.edtFirstName.setText(customer.getFirstName());
                    mMainBinding.edtLastName.setText(customer.getLastName());
                    mMainBinding.edtEmail.setText(customer.getEmail());
                }
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {
                dialog.dismiss();
                Log.e("Error", t.getMessage());
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        dialog = new ProgressDialog(this);
    }
}