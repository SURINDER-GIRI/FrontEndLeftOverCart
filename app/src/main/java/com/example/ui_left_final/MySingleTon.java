package com.example.ui_left_final;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MySingleTon {
    private static MySingleTon mInstance;
    private RequestQueue requestQueue;
    private static Context mCtx;

    private MySingleTon(Context context){
        mCtx = context;
        requestQueue = getRequestQueue();

    }

    public static synchronized MySingleTon getInstance(Context context){
        if (mInstance == null){
            mInstance = new MySingleTon(context);
        }

        return mInstance;
    }
    public RequestQueue getRequestQueue(){
        if(requestQueue == null){

            requestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());

        }
        return requestQueue;

    }

    public <T> void addTorequestque(Request<T> request){
        requestQueue.add(request);
    }
}
