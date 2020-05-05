package com.example.paragon;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NewReceiptActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_paragon_group);
    }

    public void onSubmitBtnClicked(View view) {
        Intent intent = new Intent(this, MainActivity.class);

        EditText et_groupName = (EditText)findViewById(R.id.et_groupName);
        String groupName = et_groupName.getText().toString();
        intent.putExtra("Result", groupName);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
