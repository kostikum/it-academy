package com.kostikum.itac;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.kostikum.itac.hw1.Hw1Activity;
import com.kostikum.itac.hw2.Hw2Activity;
import com.kostikum.itac.hw2.LoginActivity;

public class MainActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button hw1_button = findViewById(R.id.hw1_button);
        hw1_button.setOnClickListener(this);

        Button hw2_button = findViewById(R.id.hw2_button);
        hw2_button.setOnClickListener(this);

        Button login_activity_button = findViewById(R.id.login_activity_button);
        login_activity_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.hw1_button:
                startActivity(Hw1Activity.getIntent(this));
                break;
            case R.id.hw2_button:
                startActivity(Hw2Activity.getIntent(this));
                break;
            case R.id.login_activity_button:
                startActivity(LoginActivity.getIntent(this));
        }

    }
}
