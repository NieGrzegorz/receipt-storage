package com.example.paragon;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mainView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainView = (RecyclerView)findViewById(R.id.mainList);
        mainView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        mainView.setLayoutManager(layoutManager);

        getReceipts();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String res = data.getStringExtra("Result");
        mAdapter.notifyDataSetChanged();
        getReceipts();
    }

    public void onAddBtnClicked(View view) {
        Intent intent = new Intent(this, NewReceiptActivity.class);
        int reqCode = 1;
        startActivityForResult(intent, reqCode);
    }

    private void getReceipts() {
        class GetReceipts extends AsyncTask<Void, Void, List<Receipt>> {

            @Override
            protected List<Receipt> doInBackground(Void... voids) {
                List<Receipt> receiptList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .receiptDao()
                        .getAll();
                return receiptList;
            }

            @Override
            protected void onPostExecute(List<Receipt> receipts) {
                super.onPostExecute(receipts);
                mAdapter = new MainListAdapter(MainActivity.this, receipts);
                mainView.setAdapter(mAdapter);
            }
        }

        GetReceipts gt = new GetReceipts();
        gt.execute();
    }


}
