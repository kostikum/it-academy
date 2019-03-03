package com.kostikum.itac.dz3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.kostikum.itac.R;

public class LoginDz3Activity extends Activity {

    public static Intent getIntent(Context context) {
        return new Intent(context, LoginDz3Activity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dz3_login);
    }
}