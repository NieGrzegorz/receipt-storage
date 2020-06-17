package com.example.paragon;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class ReceiptViewActivity extends AppCompatActivity {
    private Receipt m_receipt;
    private TextView tv_receiptName, tv_purchaseDate, tv_warrantyPeriod;
    private Context mCtx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receipt_view);

        mCtx = getApplicationContext();

        tv_receiptName = findViewById(R.id.tv_receiptName);
        tv_purchaseDate = findViewById(R.id.tv_purchaseDate);
        tv_warrantyPeriod = findViewById(R.id.tv_warrantyPeriod);

        m_receipt = (Receipt) getIntent().getSerializableExtra("receipt");
        loadReceipt(m_receipt);
    }

    private void showReceiptImage() {
        String filePath = m_receipt.getFileName();
        Intent intent = new Intent(ReceiptViewActivity.this, ShowImageActivity.class);
        intent.putExtra("filePath",  filePath);
        intent.putExtra("receipt", m_receipt);
        startActivity(intent);
    }

    private void loadReceipt(Receipt receipt) {
        tv_receiptName.setText(receipt.getName());
        tv_purchaseDate.setText(receipt.getStartDate());
        tv_warrantyPeriod.setText(receipt.getEndDate());
    }

    private void deleteReceipt(final Receipt receipt) {
        class DeleteReceipt extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .receiptDao()
                        .delete(receipt);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Receipt entry removed", Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(ReceiptViewActivity.this, MainActivity.class));
            }
        }

        DeleteReceipt dr = new DeleteReceipt();
        dr.execute();
    }

    public void backButtonClicked(View view) {
        finish();
        startActivity(new Intent(ReceiptViewActivity.this, MainActivity.class));
    }

    public void deleteButtonClicked(View view) {
        deleteReceipt(m_receipt);
    }

    public void showImageButtonClicked(View view) {
        showReceiptImage();
    }
}
