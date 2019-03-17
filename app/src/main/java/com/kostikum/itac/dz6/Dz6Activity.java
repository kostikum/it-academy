package com.kostikum.itac.dz6;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.kostikum.itac.R;

public class Dz6Activity extends Activity {

    public static Intent getIntent(Context context) {
        return new Intent(context, Dz6Activity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dz6);

        final EditText filterEditText = findViewById(R.id.filter_list_edit_text);
        final RecyclerView recyclerView = findViewById(R.id.recycler_view);

        final FellasListAdapter adapter = new FellasListAdapter();


        filterEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                adapter.setList(FellasLab.get().getFilteredFellas(s.toString()));
            }
        });

        adapter.setListener(new FellasListAdapter.OnItemClickListener() {
            @Override
            public void onClick(Fellow item, int position) {
                Intent intent = new Intent(Dz6Activity.this, EditFellowActivity.class);
                intent.putExtra("EXTRA_FELLOW_ID", item.getId());
                startActivity(intent);
            }
        });

        adapter.setList(FellasLab.get().getFellas());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dz6Activity.this, EditFellowActivity.class);
                startActivity(intent);
            }
        });
    }
}
