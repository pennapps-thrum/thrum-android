package com.thrum.thrum;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.iid.FirebaseInstanceId;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void togglePurr(View view) {
        System.out.println(FirebaseInstanceId.getInstance().getToken());
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

    public void sendToggleMessage(boolean turnOn) {

    }
}
