package com.wzy.jsouptest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MainActivity extends AppCompatActivity {

    private RequestQueue mQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest("http://live.91vst.com/", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                initJsoup(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mQueue.add(stringRequest);

    }

    private void initJsoup(String response) {
        Document document = Jsoup.parse(response);
        Log.e("initj",document.toString());
        Elements links = document.select("a[href]");
        Log.e("ahref",links.toString());
        for (Element link : links) {
            Log.e("links", link.attr("href")+link.text());
        }


//        Elements media = document.select("[src]");
//        Log.e("media",media.toString());
//
//        Elements imports = document.select("link[href]");
//        Log.e("imports",imports.toString());

    }
}
