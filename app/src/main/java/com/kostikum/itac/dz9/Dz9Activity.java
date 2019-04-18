package com.kostikum.itac.dz9;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.kostikum.itac.R;

import java.util.UUID;

public class Dz9Activity extends AppCompatActivity implements FellasListFragment.Callbacks, EditFellowFragment.Callbacks{
    
    private Boolean isTablet;
    
    public static Intent getIntent(Context context) {
        return new Intent(context, Dz9Activity.class);
    }
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masterdetail);
    
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.main_fragment_container);
        
        if (fragment == null) {
            fragment = new FellasListFragment();
            fm.beginTransaction()
                    .add(R.id.main_fragment_container, fragment)
                    .commit();
        }
        
        isTablet = findViewById(R.id.detailed_fragment_container) != null;
    
        FellasLab2.get().setListener(new FellasLab2.OnListDownloadedListener() {
            @Override
            public void onDownloaded() {
                updateMainFragment();
            }
        });
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        FellasLab2.get().setListener(null);
    }
    
    @Override
    public void onFellowSelected(UUID uuid) {
        EditFellowFragment fragment = EditFellowFragment.newInstance(uuid);
    
        if (isTablet) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detailed_fragment_container, fragment)
                    .commit();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_fragment_container, fragment)
                    .addToBackStack(null)
                    .commit();
        }
    }
    
    @Override
    public void onFellowChanged() {
        if (isTablet) {
            updateMainFragment();
    
            FragmentManager fm = getSupportFragmentManager();
            Fragment fragment = fm.findFragmentById(R.id.detailed_fragment_container);
    
            getSupportFragmentManager().beginTransaction()
                    .remove(fragment)
                    .commit();
        } else {
            FellasListFragment fragment = new FellasListFragment();
    
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_fragment_container, fragment)
                    .commit();
        }
    }
    
    private void updateMainFragment() {
        if (isTablet) {
            FellasListFragment listFragment = (FellasListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.main_fragment_container);
            listFragment.updateUI();
        }
    }
}
