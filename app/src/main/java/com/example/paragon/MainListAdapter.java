package com.example.paragon;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.MainListHolder> {

    private List<Receipt> mDataSet;
    private LayoutInflater mInflater;
    private Context mCtx;

    public class MainListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textView;

        public MainListHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.tvReceipt);
            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            Receipt receipt = mDataSet.get(getAdapterPosition());
            Intent intent = new Intent(mCtx, ReceiptViewActivity.class);
            intent.putExtra("receipt",  receipt);
            mCtx.startActivity(intent);
        }
    }

    public MainListAdapter(Context context, List<Receipt> dataSet) {
        this.mInflater = LayoutInflater.from(context);
        this.mCtx = context;
        this.mDataSet = dataSet;
    }

    @Override
    public MainListAdapter.MainListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.custom_text_view, parent, false);

        return new MainListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainListHolder holder, int position) {
        Receipt dataItem = mDataSet.get(position);
        holder.textView.setText(dataItem.getName());
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}
