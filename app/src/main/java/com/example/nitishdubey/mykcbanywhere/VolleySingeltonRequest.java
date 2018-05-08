package com.example.nitishdubey.mykcbanywhere;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.lang.ref.ReferenceQueue;

/**
 * Created by Nitish Dubey on 23-01-2018.
 */

public class VolleySingeltonRequest {

    private static VolleySingeltonRequest mInstance;
    private RequestQueue requestQueue;
    private static Context mContext;

    private VolleySingeltonRequest(Context context)
    {
        mContext=context;
        requestQueue=getRequestQueue();
    }
    public RequestQueue getRequestQueue()
    {
        if(requestQueue==null)
        {
            requestQueue= Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized VolleySingeltonRequest getmInstance(Context context)
    {
        if(mInstance==null)
        {
            mInstance=new VolleySingeltonRequest(context);
        }
        return mInstance;
    }

    public<T> void addToRequestqueue(Request<T> request)
    {
        requestQueue.add(request);
    }
}
