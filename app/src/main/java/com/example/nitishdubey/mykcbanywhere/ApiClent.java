package com.example.nitishdubey.mykcbanywhere;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Nitish Dubey on 24-01-2018.
 */

public class ApiClent {

    public static final String BASE_URL="http://196.41.37.54:55255/eCCL_CMD/";
    public static Retrofit retrofit=null;
    public static Retrofit getApiClient()
    {
        if(retrofit==null)
        {
            retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
