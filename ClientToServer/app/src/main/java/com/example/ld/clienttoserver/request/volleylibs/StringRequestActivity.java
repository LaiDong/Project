package com.example.ld.clienttoserver.request.volleylibs;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LD on 7/23/2016.
 */
public class StringRequestActivity {

    public static void postStringRequest(Context mContext,final String url, final String key,final String values){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {
                        Log.e("Response volley: ", response);
                        onRequestStringListener.finishLoadJSon(null,response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        VolleyLog.e("TAG", "Error:" + volleyError.getMessage());
                        onRequestStringListener.finishLoadJSon(volleyError.getMessage(), null);
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put(key,values);
                return params;

            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        requestQueue.add(stringRequest);

    }

    // using interface send json to class other
    public interface OnRequestStringListener {
        void finishLoadJSon(String error, String json);
    }

    public static OnRequestStringListener onRequestStringListener;

    public void setOnRequestStringListener(OnRequestStringListener onRequestStringListener) {
        this.onRequestStringListener = onRequestStringListener;
    }
}
