package com.sonu.karvytest.network;

import com.sonu.karvytest.utils.Constants;

import org.jetbrains.annotations.NotNull;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Sonu Sinha on 21/09/2020.
 */
public class ApiService {

    private static ApiService instance = null;
    private ServicesAPI servicesAPI = null;
    private ServicesAPI multiPartServicesAPI = null;
    private ServicesAPI nodeJsServicesAPI = null;

    private ApiService() {
    }

    public static synchronized ApiService getInstance() {
        if (instance == null) {
            instance = new ApiService();
        }
        return instance;
    }


    public synchronized ServicesAPI getApiService() {
        if (servicesAPI != null) {
            return servicesAPI;
        }

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new RequestInterceptor());
        builder.addInterceptor(new ResponseInterceptor());
        OkHttpClient client = builder.build();
        Retrofit service = getRetrofitService(client, Constants.BASE_URL);
        servicesAPI = service.create(ServicesAPI.class);
        return servicesAPI;
    }

    @NotNull
    private Retrofit getRetrofitService(OkHttpClient client, String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}