package com.kostikum.itac.dz6;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.kostikum.itac.R;

class FellasListViewHolder extends RecyclerView.ViewHolder {

    private Fellow mFellow;

    private TextView mFellowNameTextView;
    private TextView mFellowSurameTextView;
    private TextView mFellowAgeTextView;
    private CheckBox mIsDegreeCheckBox;

    FellasListViewHolder(@NonNull View itemView) {
        super(itemView);
        mFellowNameTextView = itemView.findViewById(R.id.fellow_name_textview);
        mFellowSurameTextView = itemView.findViewById(R.id.fellow_surname_textview);
        mFellowAgeTextView = itemView.findViewById(R.id.fellow_age_textview);
        mIsDegreeCheckBox = itemView.findViewById(R.id.fellow_is_degree_checkbox);

    }

    void bindViewHolder(Fellow fellow) {
        mFellow = fellow;
        mFellowNameTextView.setText(mFellow.getName());
        mFellowSurameTextView.setText(mFellow.getSurname());
        mFellowAgeTextView.setText(Integer.toString(fellow.getAge()));
        //mIsDegreeCheckBox.setChecked(mFellow.isDegree());
    }
}
