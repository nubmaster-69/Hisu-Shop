package com.hisu.hisushop.api;

import com.hisu.hisushop.entity.Customer;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IApiService {

    IApiService API_SERVICE = new Retrofit.Builder()
            .baseUrl(IApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(IApiService.class);

    @GET(IApiConstants.CUSTOMER_URI)
    Call<List<Customer>> getCustomers();

    @GET(IApiConstants.CUSTOMER_URI + "/{id}")
    Call<Customer> getCustomerByCustomerID(@Path("id") int id);
}