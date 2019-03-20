package com.kostikum.itac.dz6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.kostikum.itac.R;

import java.util.Locale;
import java.util.Random;
import java.util.UUID;

public class EditFellowActivity extends Activity {

    private static final String EXTRA_ANSWER_MODIFIED = "com.kostikum.itac.dz6.modified";
    private Fellow mFellow;

    public static boolean wasModified(Intent result) {
        return result.getBooleanExtra(EXTRA_ANSWER_MODIFIED, false);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_fellow);

        UUID uuid = Dz6Activity.fellowIdFromIntent(getIntent());

        final EditText fellowNameEditText = findViewById(R.id.fellow_name_textview);
        final EditText fellowSurnameEditText = findViewById(R.id.fellow_surname_textview);
        final EditText fellowAgeEditText = findViewById(R.id.fellow_age_textview);
        final CheckBox fellowIsDegreeCheckbox = findViewById(R.id.fellow_is_degree_checkbox);

        Button saveChangesButton = findViewById(R.id.save_changes_button);
        Button deleteButton = findViewById(R.id.delete_button);
        Button createButton = findViewById(R.id.create_button);
        Button discardButton = findViewById(R.id.discard_button);

        mFellow = FellasLab.get().getFellow(uuid);
        if (mFellow != null) {
            fellowNameEditText.setText(mFellow.getName());
            fellowSurnameEditText.setText(mFellow.getSurname());
            fellowAgeEditText.setText(String.format(Locale.ENGLISH, "%d", mFellow.getAge()));
            fellowIsDegreeCheckbox.setChecked(mFellow.isDegree());

            saveChangesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        String ageString = fellowAgeEditText.getText().toString();
                        mFellow.setAge(Integer.parseInt(ageString));
                    } catch (NumberFormatException nfe) {
                        Toast.makeText(v.getContext(), "Incorrect age format", Toast.LENGTH_LONG).show();
                        return;
                    }
                    mFellow.setName(fellowNameEditText.getText().toString());
                    mFellow.setSurname(fellowSurnameEditText.getText().toString());
                    mFellow.setDegree(fellowIsDegreeCheckbox.isChecked());
                    setModificationResult(true);
                    finish();

                }
            });

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FellasLab.get().deleteFellow(mFellow);
                    setModificationResult(true);
                    finish();
                }
            });


            createButton.setEnabled(false);
            discardButton.setEnabled(false);
        } else {
            createButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fellow fellow = new Fellow();
                    try {
                        String string = fellowAgeEditText.getText().toString();
                        fellow.setAge(Integer.parseInt(string));
                    } catch (NumberFormatException nfe) {
                        Toast.makeText(v.getContext(), "Incorrect age format", Toast.LENGTH_LONG).show();
                        return;
                    }
                    fellow.setName(fellowNameEditText.getText().toString());
                    fellow.setSurname(fellowSurnameEditText.getText().toString());
                    fellow.setDegree(fellowIsDegreeCheckbox.isChecked());
                    FellasLab.get().addFellow(fellow);
                    setModificationResult(true);
                    finish();

                }
            });

            discardButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setModificationResult(false);
                    finish();
                }
            });


            saveChangesButton.setEnabled(false);
            deleteButton.setEnabled(false);
        }
    }

    private void setModificationResult(boolean isModified) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_ANSWER_MODIFIED, isModified);
        setResult(RESULT_OK, intent);
    }
}
