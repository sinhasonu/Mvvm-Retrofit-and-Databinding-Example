package com.sonu.karvytest.network;

import android.util.Log;

import com.sonu.karvytest.utils.Constants;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Sonu Sinha on 21/09/2020.
 */
public class RequestInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        Request request = chain.request();
        HttpUrl httpUrl = request.url();
        HttpUrl.Builder urlBuilder = httpUrl.newBuilder();
        Request.Builder requestBuilder = request.newBuilder();
        String suffixURL = getSuffixURL(request.url().toString());
        Log.d("intercept: Full Url = ", "" + request.url() + " \t Suffix Url = " + suffixURL);

        Request updatedRequest = requestBuilder.url(urlBuilder.build()).build();

        return chain.proceed(updatedRequest);
    }

    private String getSuffixURL(String url) {
        String baseURL = Constants.BASE_URL;
        String suffixURL = url.substring(baseURL.length());
        if (suffixURL.contains("?")) {
            suffixURL = suffixURL.substring(0, suffixURL.indexOf('?'));
        }
        return suffixURL;
    }

}
