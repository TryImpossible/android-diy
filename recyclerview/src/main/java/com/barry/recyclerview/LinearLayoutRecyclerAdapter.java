package com.barry.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LinearLayoutRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<String> mData;

    public LinearLayoutRecyclerAdapter(Context context, List<String> data) {
        super();
        this.mContext = context;
        this.mData = data;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_linear_layout_recycler, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof VH) {
            String text = mData.get(position);
            ((VH) holder).mTextView.setText(text);
        }
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    public void setNewData(List<String> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    public void addData(List<String> mData) {
        this.mData.addAll(mData);
        notifyDataSetChanged();
    }

    public List<String> getData() {
        return this.mData;
    }

    public static class VH extends RecyclerView.ViewHolder {

        private TextView mTextView;

        public VH(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.text);
        }
    }
}
