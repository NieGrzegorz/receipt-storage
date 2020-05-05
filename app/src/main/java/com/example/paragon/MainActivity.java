package com.example.paragon;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mainView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String> dataSet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataSet = new ArrayList<>();
        dataSet.add("One");
        dataSet.add("Two");
        dataSet.add("Three");

        mainView = (RecyclerView)findViewById(R.id.mainList);
        mainView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        mainView.setLayoutManager(layoutManager);

        mAdapter = new MainListAdapter(this, dataSet);
        mainView.setAdapter(mAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String res = data.getStringExtra("Result");
        dataSet.add(res);
        mAdapter.notifyDataSetChanged();
    }

    public void onAddBtnClicked(View view) {
        Intent intent = new Intent(this, NewReceiptActivity.class);
        int reqCode = 1;
        startActivityForResult(intent, reqCode);
    }


}
