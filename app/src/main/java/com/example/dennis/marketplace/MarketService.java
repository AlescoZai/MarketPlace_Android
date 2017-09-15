package com.example.dennis.marketplace;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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
        call.enqueue(callback);

    }
    //Now in this method we are going to get all the data that is passed here and try and display them
    public ArrayList<Market>processResults (Response response){
        ArrayList<Market>markets = new ArrayList<>();
        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject walmartJSON = new JSONObject(jsonData);
                JSONArray itemsJSON = walmartJSON.getJSONArray("items");
                for (int i = 0; i <itemsJSON.length(); i++) {
                    JSONObject itemJSON = itemsJSON.getJSONObject(i);

                    String image = itemJSON.getString("largeImage");
                    String salePrice = itemJSON.getString("salePrice");
                    String name = itemJSON.getString("name");
                    String stock = itemJSON.getString("stock");


                    Market market = new Market(name, image, salePrice, stock);
                    markets.add(market);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return markets;
    }

}
