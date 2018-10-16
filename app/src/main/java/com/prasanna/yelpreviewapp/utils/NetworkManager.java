package com.prasanna.yelpreviewapp.utils;

import com.prasanna.yelpreviewapp.model.CategoryResponse;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkManager {
    private CallBackToView mCallback;

    final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(5000, TimeUnit.SECONDS)
            .writeTimeout(5000, TimeUnit.SECONDS)
            .readTimeout(5000, TimeUnit.SECONDS)
            .build();

    public void doGetCategoryRequest(String filter, Class<CategoryResponse> categoryResponseClass, final CallBackToView mCallback) throws IOException {

        Request request = new Request.Builder()
                .url(AppConstants.CATEGORY_URL)
                .addHeader("Authorization", "Bearer " + AppConstants.API_KEY)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mCallback.onFailure(e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mCallback.onSuccess(response);
            }
        });
    }
}