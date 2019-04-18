package com.kostikum.itac;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.kostikum.itac.dz1.Dz1Activity;
import com.kostikum.itac.dz2.Dz2Activity;
import com.kostikum.itac.dz3.Dz3Activity;
import com.kostikum.itac.dz3.Dz3LoginActivity;
import com.kostikum.itac.dz4.Dz4PagerActivity;
import com.kostikum.itac.dz5.Dz5Activity;
import com.kostikum.itac.dz6.Dz6Activity;
import com.kostikum.itac.dz9.Dz9Activity;


public class MainActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.dz1_button).setOnClickListener(this);
        findViewById(R.id.dz2_button).setOnClickListener(this);
        findViewById(R.id.dz3_1_button).setOnClickListener(this);
        findViewById(R.id.dz3_2_button).setOnClickListener(this);
        findViewById(R.id.dz4_button).setOnClickListener(this);
        findViewById(R.id.dz5_button).setOnClickListener(this);
        findViewById(R.id.dz6_button).setOnClickListener(this);
        findViewById(R.id.dz9_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dz1_button:
                startActivity(Dz1Activity.getIntent(this));
                break;
            case R.id.dz2_button:
                startActivity(Dz2Activity.getIntent(this));
                break;
            case R.id.dz3_1_button:
                startActivity(Dz3Activity.getIntent(this));
                break;
            case R.id.dz3_2_button:
                startActivity(Dz3LoginActivity.getIntent(this));
                break;
            case R.id.dz4_button:
                startActivity(Dz4PagerActivity.getIntent(this));
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            case R.id.dz5_button:
                startActivity(Dz5Activity.getIntent(this));
                break;
            case R.id.dz6_button:
                startActivity(Dz6Activity.getIntent(this));
                break;
            case R.id.dz9_button:
                startActivity(Dz9Activity.getIntent(this));
                break;
        }
    }
}
