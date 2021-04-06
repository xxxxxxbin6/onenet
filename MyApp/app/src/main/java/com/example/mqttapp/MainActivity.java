package com.example.mqttapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mqttapp.Bean.Datapoints;
import com.example.mqttapp.Bean.Datastreams;
import com.example.mqttapp.Bean.JsonRootBean;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

//    private static final String DeviceID = "691294037"; //设备id
//    private static final String ApiKey = "43L04i8y4qEGNNxQ3DOv9neRMnM="; // api-key
    private String responseData; //响应返回的json
    private TextView textView; //用户看到的信息
    private String dataValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button get = findViewById(R.id.get);
        textView = findViewById(R.id.json_text);
        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Get();
            }
        });
    }

    public void Get() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://10.10.10.38:8080/data/concentration") //请求本地服务器的地址,10.10.10.38可替换成自己电脑ip
                            .build();
                    // 请求主体部分
                    Response response = client.newCall(request).execute();
                    responseData = Objects.requireNonNull(response.body()).string();
                    parseJSONWithGSON(responseData);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }


    private void parseJSONWithGSON(String jsonData) {
        JsonRootBean app = new Gson().fromJson(jsonData, JsonRootBean.class);
        List<Datastreams> streams = app.getData().getDatastreams();
        List<Datapoints> points = streams.get(0).getDatapoints();
        int count = app.getData().getCount();//获取数据的数量
        for (int i = 0; i < points.size(); i++) {
            String time = points.get(i).getAt();
            dataValue = points.get(i).getValue();
        }
        showValue(dataValue);

    }

    private void showValue(final String value) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText(value + "ppm");
            }
        });
    }
}
