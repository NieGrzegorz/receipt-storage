package com.example.paragon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewReceiptActivity extends AppCompatActivity {

    static private int REQUEST_IMAGE_CAPTURE = 1;

    private EditText et_receiptName, et_purchaseDate, et_warrantyPeriod;
    private String m_filePath;
    private Uri m_file;
    Bitmap help1;
    ImageView imageView;
    ThumbnailUtils thumbnail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_paragon_group);

        et_receiptName = (EditText)findViewById(R.id.et_receiptName);
        et_purchaseDate = (EditText)findViewById(R.id.et_purchaseDate);
        et_warrantyPeriod = (EditText)findViewById(R.id.et_warrantyPeriod);

    }

    public void onSubmitBtnClicked(View view) {
        createDatabaseEntry();
    }

    public void onAddPhotoClicked(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_IMAGE_CAPTURE) {
            if(resultCode == Activity.RESULT_OK) {
                try {
                    help1 = MediaStore.Images.Media.getBitmap(getContentResolver(), m_file);
                    imageView.setImageBitmap( thumbnail.extractThumbnail(help1,help1.getWidth(),help1.getHeight()));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    private void createDatabaseEntry()
    {
        final String receiptName = et_receiptName.getText().toString().trim();
        final String purchaseDate = et_purchaseDate.getText().toString().trim();
        final String warrantyPeriod = et_warrantyPeriod.getText().toString().trim();

        class SaveTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                Receipt receipt = new Receipt();
                receipt.setName(receiptName);
                receipt.setStartDate(purchaseDate);
                receipt.setEndDate(warrantyPeriod);
                receipt.setFileName(m_filePath);

                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .receiptDao()
                        .insert(receipt);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                intent.putExtra("Result", receiptName);
                setResult(Activity.RESULT_OK, intent);
                finish();
                Toast.makeText(getApplicationContext(), "Created", Toast.LENGTH_LONG).show();
            }
        }

        SaveTask st = new SaveTask();
        st.execute();
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        m_filePath = image.getAbsolutePath();
        return image;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        // TODO Auto-generated method stub

        outState.putString("photopath", m_filePath);


        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("photopath")) {
                m_filePath = savedInstanceState.getString("photopath");
            }
        }

        super.onRestoreInstanceState(savedInstanceState);
    }

}
