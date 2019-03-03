package com.kostikum.itac.dz1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.kostikum.itac.R;

public class Dz1Activity extends Activity implements View.OnClickListener {
    private TextView textView1;
    private TextView textView2;

    public static Intent getIntent(Context context) {
        return new Intent(context, Dz1Activity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dz1);

        View.OnClickListener swapButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swapTextViews();
            }
        };

        textView1 = findViewById(R.id.text_view_1);
        textView1.setOnClickListener(this);

        textView2 = findViewById(R.id.text_view_2);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swapTextViews();
            }
        });

        findViewById(R.id.swap_button).setOnClickListener(swapButtonClickListener);
    }

    @Override
    public void onClick(View v) {
        swapTextViews();
    }

    private void swapTextViews(){
        CharSequence cs = textView1.getText();
        textView1.setText(textView2.getText());
        textView2.setText(cs);

        Drawable color = textView1.getBackground();
        textView1.setBackground(textView2.getBackground());
        textView2.setBackground(color);
    }
}
