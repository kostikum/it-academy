package com.kostikum.itac.dz6;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FetchFellasTask extends AsyncTask<Void,Void,List<Fellow>> {

    @Override
    protected List<Fellow> doInBackground(Void... params) {
        try {
            return new FellasFetcher().jsonFetcher();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    @Override
    protected void onPostExecute(List<Fellow> fellas) {
        FellasLab.get().addFellas(fellas);
    }

    private class FellasFetcher {
        private ArrayList<Fellow> jsonFetcher() throws IOException {

            URL url = new URL("http://kiparo.ru/t/test.json");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            String st;

            try {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                InputStream in = conn.getInputStream();

                if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    throw new IOException(conn.getResponseMessage());
                }

                int bytesRead = 0;
                byte[] buffer = new byte[1024];

                while ((bytesRead = in.read(buffer)) > 0) {
                    out.write(buffer, 0, bytesRead);
                }
                out.close();
                st = new String(out.toByteArray());
            } finally {
                conn.disconnect();
            }

            Gson gson = new Gson();
            Gsonner gsonner = gson.fromJson(st, Gsonner.class);
            return gsonner.people;

        }
    }

    private class Gsonner {
        ArrayList<Fellow> people;
    }
}
