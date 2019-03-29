package com.kostikum.itac.dz9;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.kostikum.itac.R;

import java.util.Locale;
import java.util.UUID;

public class EditFellowFragment extends Fragment {
    
    private static final String EXTRA_UUID = "com.kostikum.itac.dz9.fellow_uuid";
    private Fellow mFellow;
    private Callbacks mCallbacks;
    
    public interface Callbacks{
        void onFellowChanged();
    }
    
    public static EditFellowFragment newInstance(UUID uuid) {
        EditFellowFragment editFellowFragment = new EditFellowFragment();
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_UUID, uuid);
        editFellowFragment.setArguments(args);
        return editFellowFragment;
    }
    
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Callbacks) {
            mCallbacks = (Callbacks) context;
        }
    }
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_fellow, container, false);
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final EditText fellowNameEditText = view.findViewById(R.id.fellow_name_textview);
        final EditText fellowSurnameEditText = view.findViewById(R.id.fellow_surname_textview);
        final EditText fellowAgeEditText = view.findViewById(R.id.fellow_age_textview);
        final CheckBox fellowIsDegreeCheckbox = view.findViewById(R.id.fellow_is_degree_checkbox);
    
        Button saveChangesButton = view.findViewById(R.id.save_changes_button);
        Button deleteButton = view.findViewById(R.id.delete_button);
        Button createButton = view.findViewById(R.id.create_button);
        Button discardButton = view.findViewById(R.id.discard_button);
    
        UUID fellowUUID = (UUID) getArguments().getSerializable(EXTRA_UUID);
        mFellow = FellasLab2.get().getFellow(fellowUUID);
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
                    mCallbacks.onFellowChanged();
                }
            });
        
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FellasLab2.get().deleteFellow(mFellow);
                    mCallbacks.onFellowChanged();
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
                    FellasLab2.get().addFellow(fellow);
                    mCallbacks.onFellowChanged();
                }
            });
        
            discardButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCallbacks.onFellowChanged();
                }
            });
        
        
            saveChangesButton.setEnabled(false);
            deleteButton.setEnabled(false);
        }
    }
    
    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }
}
