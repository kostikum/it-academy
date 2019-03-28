package com.kostikum.itac.dz9;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FellasLab2 {
    private static FellasLab2 sFellasLab;
    private List<Fellow> mFellas;
    private OnListDownloadedListener mListener;

    public void setListener(OnListDownloadedListener listener) {
        this.mListener = listener;
    }

    public static FellasLab2 get() {
        if (sFellasLab == null) {
            sFellasLab = new FellasLab2();
        }
        return sFellasLab;
    }

    private FellasLab2() {
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
            if (fellow.getName().toLowerCase().contains(keyWord) ||
                    fellow.getSurname().toLowerCase().contains(keyWord)) {
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
