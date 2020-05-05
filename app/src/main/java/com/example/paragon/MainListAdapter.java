package com.example.paragon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.MainListHolder> {

    private List<String> mDataSet;
    private LayoutInflater mInflater;

    public static class MainListHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public MainListHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.tvReceipt);

        }
    }

    public MainListAdapter(Context context, List<String> dataSet) {
        this.mInflater = LayoutInflater.from(context);
        mDataSet = dataSet;
    }

    @Override
    public MainListAdapter.MainListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.custom_text_view, parent, false);

        return new MainListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainListHolder holder, int position) {
        String dataItem = mDataSet.get(position);
        holder.textView.setText(dataItem);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}
