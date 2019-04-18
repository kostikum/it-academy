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
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
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
                    if (!modifyFellow(mFellow, view.getContext(),
                            fellowAgeEditText.getText().toString(),
                            fellowNameEditText.getText().toString(),
                            fellowSurnameEditText.getText().toString(),
                            fellowIsDegreeCheckbox.isChecked())) {
                        return;
                    }
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
                    if (!modifyFellow(fellow, view.getContext(),
                            fellowAgeEditText.getText().toString(),
                            fellowNameEditText.getText().toString(),
                            fellowSurnameEditText.getText().toString(),
                            fellowIsDegreeCheckbox.isChecked())) {
                        return;
                    }
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
    
    private Boolean modifyFellow(Fellow fellow, Context context,
                              String age, String name, String surname, Boolean checked) {
        try {
            fellow.setAge(Integer.parseInt(age));
        } catch (NumberFormatException nfe) {
            Toast.makeText(context, "Incorrect age format", Toast.LENGTH_LONG).show();
            return false;
        }
        fellow.setName(name);
        fellow.setSurname(surname);
        fellow.setDegree(checked);
        return true;
    }
}
