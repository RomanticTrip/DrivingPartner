package com.devfactory.drivingpartner.app;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.devfactory.drivingpartner.R;
import com.devfactory.drivingpartner.app.seach.SearchActivity;

public class SplashActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Button a = (Button) findViewById(R.id.buttnnnn);
        a.setOnClickListener(this);
        TextView bb = (TextView) findViewById(R.id.Texttttt);
        bb.setOnClickListener(this);


        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.cancel(2345);

//        Handler handler = new Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//                finish();
//            }
//        };
//
//        handler.sendEmptyMessageDelayed(0, 3000);
    }

    @Override
    public void onClick(View v) {
        Intent intent;

        switch (v.getId()){
            case R.id.buttnnnn:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.Texttttt:
                intent = new Intent(this, SinginActivity.class);
                startActivity(intent);
        }
    }
}
