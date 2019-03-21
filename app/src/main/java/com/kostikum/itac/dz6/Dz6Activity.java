package com.kostikum.itac.dz6;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.kostikum.itac.R;

import java.util.UUID;

public class Dz6Activity extends Activity {
    
    private static final int REQUEST_CODE_MODIFICATION = 0;
    private FellasListAdapter adapter;

    public static Intent getIntent(Context context) {
        return new Intent(context, Dz6Activity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dz6);

        final EditText filterEditText = findViewById(R.id.filter_list_edit_text);

        final RecyclerView recyclerView = findViewById(R.id.recycler_view);

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
                adapter.setListWithFilter(s.toString());
            }
        });

        adapter.setListener(new FellasListAdapter.OnItemClickListener() {
            @Override
            public void onClick(Fellow item, int position) {
                Intent intent = EditFellowActivity.getIntent(Dz6Activity.this, item.getUuid());
                startActivityForResult(intent, REQUEST_CODE_MODIFICATION);
            }
        });

        FellasLab.get().setListener(new FellasLab.OnListDownloadedListener() {
            @Override
            public void onDownloaded() {
                adapter.notifyDataSetChanged();
            }
        });

        adapter.setListWithFilter(null);

        recyclerView.setHasFixedSize(true);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                Configuration.ORIENTATION_PORTRAIT);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dz6Activity.this, EditFellowActivity.class);
                startActivityForResult(intent, REQUEST_CODE_MODIFICATION);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_CODE_MODIFICATION) {
            if (intent == null) {
                return;
            }
            if (EditFellowActivity.wasModified(intent)) {
                adapter.notifyDataSetChanged();

        }
    }
    }
}
