package com.kostikum.itac.dz6;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kostikum.itac.R;

import java.util.List;

public class FellasListAdapter extends RecyclerView.Adapter<FellasListViewHolder>
        implements FellasLab.OnListDownloadedListener{

    private List<Fellow> mFellas;
    private OnItemClickListener mListener;

    public void setListWithFilter(String keyWord) {
        if (keyWord == null || keyWord.isEmpty()) {
            mFellas = FellasLab.get().getFellas();
        } else {
            mFellas = FellasLab.get().getFilteredFellas(keyWord);
        }
        notifyDataSetChanged();
    }

    public void setListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    @NonNull
    @Override
    public FellasListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.item_fellas_list, parent, false);
        return new FellasListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FellasListViewHolder holder, int position) {
        holder.bindViewHolder(mFellas.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (mListener != null) {
                    mListener.onClick(mFellas.get(position), position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFellas.size();
    }

    @Override
    public void onDownloaded() {
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onClick(Fellow item, int position);
    }
}
