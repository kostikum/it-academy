package com.kostikum.itac;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.kostikum.itac.dz1.Dz1Activity;
import com.kostikum.itac.dz2.Dz2Activity;
import com.kostikum.itac.dz3.Dz3Activity;
import com.kostikum.itac.dz3.LoginDz3Activity;
import com.kostikum.itac.dz4.Dz4PagerActivity;
import com.kostikum.itac.dz5.Dz5Activity;


public class MainActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.hw1_button).setOnClickListener(this);
        findViewById(R.id.hw2_button).setOnClickListener(this);
        findViewById(R.id.dz3_button).setOnClickListener(this);
        findViewById(R.id.login_activity_button).setOnClickListener(this);
        findViewById(R.id.dz4_button).setOnClickListener(this);
        findViewById(R.id.dz5_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.hw1_button:
                startActivity(Dz1Activity.getIntent(this));
                break;
            case R.id.hw2_button:
                startActivity(Dz2Activity.getIntent(this));
                break;
            case R.id.dz3_button:
                startActivity(Dz3Activity.getIntent(this));
                break;
            case R.id.login_activity_button:
                startActivity(LoginDz3Activity.getIntent(this));
                break;
            case R.id.dz4_button:
                startActivity(Dz4PagerActivity.getIntent(this));
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            case R.id.dz5_button:
                startActivity(Dz5Activity.getIntent(this));
                break;
        }
    }
}
