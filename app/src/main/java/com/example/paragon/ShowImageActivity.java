package com.example.paragon;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class ShowImageActivity extends AppCompatActivity {

    private String mFileName;
    private Receipt m_receipt;
    ImageView receiptImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_image_view);

        m_receipt = (Receipt) getIntent().getSerializableExtra("receipt");
        mFileName = (String) getIntent().getSerializableExtra("filePath");

        File imgFile = new  File(mFileName);
        if(imgFile.exists())
        {
            receiptImage = (ImageView) findViewById(R.id.iv_receiptImage);
            receiptImage.setImageURI(Uri.fromFile(imgFile));
        }
    }

    public void backButtonClicked(View view) {
        finish();
        Intent intent = new Intent(ShowImageActivity.this, ReceiptViewActivity.class);
        intent.putExtra("receipt", m_receipt);
        startActivity(intent);
    }
}
