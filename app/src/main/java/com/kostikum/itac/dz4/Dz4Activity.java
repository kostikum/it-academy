package com.kostikum.itac.dz4;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.kostikum.itac.R;

public class Dz4Activity extends Activity {

    public static Intent getIntent(Context context) {
        return new Intent(context, Dz4Activity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dz4);


    }
}
