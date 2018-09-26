package com.computecrib.bakingapp.util;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.textclassifier.TextLinks;

import java.io.IOException;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public final class NetworkUtils {

    public static String getBakingRecipesJSONString(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();

        if (response.body() != null) {
            return response.body().string();
        }else{
            return null;
        }
    }

    //Inspired by StackOverflow thread:
    //https://stackoverflow.com/questions/4238921/detect-whether-there-is-an-internet-connection-available-on-android
    //Date: 15 July 2018
    public static boolean isConnected(ConnectivityManager cm) {
        NetworkInfo networkStatus = null;
        if (cm != null) {
            networkStatus = cm.getActiveNetworkInfo();
        }
        return networkStatus != null && networkStatus.isConnected();
    }

}
