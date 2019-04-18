package com.kostikum.itac.dz9;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.kostikum.itac.R;

import java.util.Locale;

class FellasListViewHolder extends RecyclerView.ViewHolder {

    private TextView mFellowNameTextView;
    private TextView mFellowSurameTextView;
    private TextView mFellowAgeTextView;
    private TextView mIsDegreeTextView;

    FellasListViewHolder(@NonNull View itemView) {
        super(itemView);
        mFellowNameTextView = itemView.findViewById(R.id.fellow_name_textview);
        mFellowSurameTextView = itemView.findViewById(R.id.fellow_surname_textview);
        mFellowAgeTextView = itemView.findViewById(R.id.fellow_age_textview);
        mIsDegreeTextView = itemView.findViewById(R.id.fellow_is_degree_textview);

    }

    void bindViewHolder(Fellow fellow) {
        mFellowNameTextView.setText(fellow.getName());
        mFellowSurameTextView.setText(fellow.getSurname());
        mFellowAgeTextView.setText(String.format(Locale.ENGLISH, "Age: %d", fellow.getAge()));
        if (fellow.isDegree()) {
            mIsDegreeTextView.setText(R.string.yes_is_degree_check_box_text);
        } else {
            mIsDegreeTextView.setText(R.string.no_is_degree_check_box_text);
        }
    }
}
