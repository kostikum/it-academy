package com.kostikum.itac.dz6;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.kostikum.itac.R;

public class EditFellowActivity extends Activity {

    private Fellow mFellow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_fellow);

        int id = getIntent().getIntExtra("EXTRA_FELLOW_ID", 0);

        EditText fellowNameEditText = findViewById(R.id.fellow_name_textview);
        EditText fellowSurnameEditText = findViewById(R.id.fellow_surname_textview);
        EditText fellowAgeEditText = findViewById(R.id.fellow_age_textview);
        CheckBox fellowIsDegreeCheckbox = findViewById(R.id.fellow_is_degree_checkbox);

        Button saveChangesButton = findViewById(R.id.save_changes_button);
        Button deleteButton = findViewById(R.id.delete_button);
        Button createButton = findViewById(R.id.create_button);
        Button discardButton = findViewById(R.id.discard_button);

        if (id != 0) {
            mFellow = FellasLab.get().getFellow(id);
            fellowNameEditText.setText(mFellow.getName());
            fellowSurnameEditText.setText(mFellow.getSurname());
            fellowAgeEditText.setText(Integer.toString(mFellow.getAge()));
            fellowIsDegreeCheckbox.setChecked(mFellow.isDegree());

            saveChangesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            deleteButton.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return true;
                }
            });
            createButton.setEnabled(false);
            discardButton.setEnabled(false);
        } else {
            saveChangesButton.setEnabled(false);
            deleteButton.setEnabled(false);
            createButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            discardButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }
}
