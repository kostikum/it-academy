package com.kostikum.itac.hw2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import com.kostikum.itac.R;

public class Hw2Activity extends Activity {

    public static Intent getIntent(Context context) {
        return new Intent(context, Hw2Activity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hw2);

        final LinearLayout allFlagsLinearLayout = findViewById(R.id.all_flags_linear_layout);

        allFlagsLinearLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int flagsLayoutWidth = allFlagsLinearLayout.getWidth();

                updateFlagViewHeight(R.id.flag_of_austria_layout, flagsLayoutWidth, 2, 3);
                updateFlagViewHeight(R.id.flag_of_poland_layout, flagsLayoutWidth, 5, 8);
                updateFlagViewHeight(R.id.flag_of_italy_layout, flagsLayoutWidth, 2, 3);
                updateFlagViewHeight(R.id.flag_of_columbia_layout, flagsLayoutWidth, 2, 3);
                updateFlagViewHeight(R.id.flag_of_madagascar_layout, flagsLayoutWidth, 2, 3);
                updateFlagViewHeight(R.id.flag_of_thailand_layout, flagsLayoutWidth, 2, 3);
                // Proportion from Wikipedia: 28:34 (14:17) to 28:37, let's take something in between
                updateFlagViewHeight(R.id.flag_of_denmark_layout, flagsLayoutWidth, 28, 35);

                allFlagsLinearLayout.setVisibility(View.VISIBLE);
            }
        });
    }

    private void updateFlagViewHeight(int flagViewId, int flagsLayoutWidth, int yAxisProportion, int xAxisProportion) {
        ViewGroup flagLayout = findViewById(flagViewId);
        ViewGroup.LayoutParams params = flagLayout.getLayoutParams();

        params.height = flagsLayoutWidth * yAxisProportion / xAxisProportion;
        flagLayout.setLayoutParams(params);
    }
}
