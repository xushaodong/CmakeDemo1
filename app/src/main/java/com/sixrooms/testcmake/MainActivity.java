package com.sixrooms.testcmake;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initSocket();
    }

    private void initSocket() {
        try {
            Socket mSocket = new Socket("192.168.2.45",1989);
            mSocket.isConnected();

            //接收服务器数据
            InputStream inputStream = mSocket.getInputStream();
            InputStreamReader isr = new InputStreamReader(inputStream);
            BufferedReader bd = new BufferedReader(isr);
            bd.readLine();


            //发送数据
            OutputStream outputStream = mSocket.getOutputStream();
            outputStream.write(("测试数据"+"\n").getBytes("utf-8"));
            outputStream.flush();

            //断开连接
            inputStream.close();
            outputStream.close();
            mSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
