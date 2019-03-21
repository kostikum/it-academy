package com.kostikum.itac.dz6;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FellasLab {
    private static FellasLab sFellasLab;
    private List<Fellow> mFellas;
    private OnListDownloadedListener mListener;

    public void setListener(OnListDownloadedListener listener) {
        this.mListener = listener;
    }

    public static FellasLab get() {
        if (sFellasLab == null) {
            sFellasLab = new FellasLab();
        }
        return sFellasLab;
    }

    private FellasLab() {
        new FetchFellasTask().execute();
        mFellas = new ArrayList<>();
    }

    public List<Fellow> getFellas() {
        return mFellas;
    }

    public void addFellas(List<Fellow> fellasList) {
        mFellas.addAll(fellasList);
        if (mListener != null) {
            mListener.onDownloaded();
        }
    }

    public void addFellow(Fellow fellow) {
        mFellas.add(fellow);
    }

    public void deleteFellow(Fellow fellow) {
        mFellas.remove(fellow);
    }


    public List<Fellow> getFilteredFellas(String keyWord) {
        List<Fellow> filteredFellas = new ArrayList<>();
        for (Fellow fellow : mFellas) {
            if (fellow.getName().contains(keyWord) || fellow.getSurname().contains(keyWord)) {
                filteredFellas.add(fellow);
            }
        }
        return filteredFellas;
    }

    public Fellow getFellow(UUID uuid) {
        for (Fellow fellow : mFellas) {
            if (fellow.getUuid().equals(uuid)) {
                return fellow;
            }
        }
        return null;
    }



    public interface OnListDownloadedListener {
        void onDownloaded();
    }
}
