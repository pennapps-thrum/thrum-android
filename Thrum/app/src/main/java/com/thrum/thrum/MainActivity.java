package com.thrum.thrum;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.iid.FirebaseInstanceId;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void togglePurr(View view) {
        Button btn = (Button)view;
        final String status = (String)(view.getTag());
        if ("0".equals(status)) { // turn on
            view.setTag("1");
            btn.setText(getString(R.string.button_switch_off));
            sendToggleMessage(true);
        } else { // turn off
            view.setTag("0");
            btn.setText(getString(R.string.button_switch_on));
            sendToggleMessage(false);
        }
    }

    public void sendToggleMessage(final boolean turnOn) {
        System.out.println("reached here");
        new Thread(new Runnable() {
            public void run() {
                URL url = null;
                HttpURLConnection client = null;
                try {
                    String urlStr = "http://35.196.92.217:8080/toggle?status=" + (turnOn ? "on" : "off");
                    System.out.println("connecting to url " + urlStr);
                    url = new URL(urlStr);
                    client = (HttpURLConnection) url.openConnection();
                    client.setRequestMethod("GET");
                    //InputStream in = client.getInputStream();
                    //InputStreamReader isw = new InputStreamReader(in);

                    int code = client.getResponseCode();
                    System.out.println(code);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (client != null) {
                        client.disconnect();
                    }
                }
            }
        }).start();

    }
}
