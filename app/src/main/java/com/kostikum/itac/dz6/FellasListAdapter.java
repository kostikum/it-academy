package com.kostikum.itac.dz6;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kostikum.itac.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class FellasListAdapter extends RecyclerView.Adapter<FellasListViewHolder> {

    private List<Fellow> mFellas;
    private OnItemClickListener mListener;

    public void setListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public void setList(List<Fellow> fellas) {
        mFellas = fellas;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FellasListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.item_fellas_list, parent, false);
        final FellasListViewHolder holder = new FellasListViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (mListener != null) {
                    mListener.onClick(mFellas.get(position + 1), position);
                }
            }
        });
        return new FellasListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FellasListViewHolder holder, int position) {
        holder.bindViewHolder(mFellas.get(position));
    }

    @Override
    public int getItemCount() {
        return mFellas.size();
    }

    public interface OnItemClickListener {
        void onClick(Fellow item, int position);
    }
}
