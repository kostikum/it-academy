package com.kostikum.itac.dz9;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.kostikum.itac.R;

import java.util.UUID;

public class FellasListFragment extends Fragment {
    
    private FellasListAdapter adapter;
    private Callbacks mCallbacks;
    
    public interface Callbacks {
        void onFellowSelected(UUID uuid);
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
        return inflater.inflate(R.layout.fragment_fellas_list, container,false);
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    
        final EditText filterEditText = view.findViewById(R.id.filter_list_edit_text);
    
        final RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
    
        adapter = new FellasListAdapter();
    
        filterEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
        
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        
            @Override
            public void afterTextChanged(Editable s) {
                adapter.setListWithFilter(s.toString().toLowerCase());
            }
        });
    
        adapter.setListener(new FellasListAdapter.OnItemClickListener() {
            @Override
            public void onClick(Fellow fellow, int position) {
                mCallbacks.onFellowSelected(fellow.getUuid());
            }
        });
    
        FellasLab2.get().setListener(new FellasLab2.OnListDownloadedListener() {
            @Override
            public void onDownloaded() {
                updateUI();
            }
        });
    
        adapter.setListWithFilter(null);
    
        recyclerView.setHasFixedSize(true);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                Configuration.ORIENTATION_PORTRAIT);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    
        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallbacks.onFellowSelected(null);
            }
        });
    
    }
    
    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }
    
    public void updateUI() {
        adapter.notifyDataSetChanged();
    }
}
