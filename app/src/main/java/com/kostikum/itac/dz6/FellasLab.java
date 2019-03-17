package com.kostikum.itac.dz6;

import java.util.ArrayList;
import java.util.List;

public class FellasLab {
    private static FellasLab sFellasLab;
    private List<Fellow> mFellas;

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

    public Fellow getFellow(int id) {
        for (Fellow fellow : mFellas) {
            if (fellow.getId() == id) {
                return fellow;
            }
        }
        return null;
    }
}
