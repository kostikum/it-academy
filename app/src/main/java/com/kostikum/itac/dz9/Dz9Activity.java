package com.kostikum.itac.dz9;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.kostikum.itac.R;

import java.util.UUID;

public class Dz9Activity extends AppCompatActivity implements FellasListFragment.Callbacks, EditFellowFragment.Callbacks{
    
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
    
        FellasLab2.get().setListener(new FellasLab2.OnListDownloadedListener() {
            @Override
            public void onDownloaded() {
                updateMainFragment();
            }
        });
    }
    
    @Override
    public void onFellowSelected(UUID uuid) {
        EditFellowFragment fragment = EditFellowFragment.newInstance(uuid);
    
        if (findViewById(R.id.detailed_fragment_container) == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_fragment_container, fragment)
                    .commit();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detailed_fragment_container, fragment)
                    .commit();
        }
    }
    
    @Override
    public void onFellowChanged() {
        if (findViewById(R.id.detailed_fragment_container) == null) {
            FellasListFragment fragment = new FellasListFragment();
        
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_fragment_container, fragment)
                    .commit();
        } else {
            updateMainFragment();
    
            FragmentManager fm = getSupportFragmentManager();
            Fragment fragment = fm.findFragmentById(R.id.detailed_fragment_container);
    
            getSupportFragmentManager().beginTransaction()
                    .remove(fragment)
                    .commit();
        }
    }
    
    private void updateMainFragment() {
        if (findViewById(R.id.detailed_fragment_container) != null) {
            FellasListFragment listFragment = (FellasListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.main_fragment_container);
            listFragment.updateUI();
        }
    }
}
