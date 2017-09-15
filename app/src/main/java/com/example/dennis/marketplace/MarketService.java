package com.example.dennis.marketplace;

import javax.security.auth.callback.Callback;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by dennis on 9/15/17.
 */

public class MarketService {
    //Now lets create a method to find the specific items
    public static void findItem(String item, Callback callback){
    //Now lets build a URL
        OkHttpClient client = new OkHttpClient.Builder().build();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.API_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.API_BASE_QUERY, item);
        urlBuilder.addQueryParameter(Constants.API_BASE_FORMAT, "json");
        urlBuilder.addQueryParameter(Constants.API_KEY_HOLDER, Constants.API_KEY );

        //NOW LETS TAKE THE URL INTO A STRING AND PARSE IT
        String url = urlBuilder.build().toString();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        call.enqueue((okhttp3.Callback) callback);

    }

}
