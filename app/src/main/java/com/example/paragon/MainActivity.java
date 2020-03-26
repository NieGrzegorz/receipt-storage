package com.example.paragon;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String res = data.getStringExtra("Result");
        TextView groupResult = (TextView) findViewById(R.id.tv_result);
        groupResult.setText(res);
    }

    public void onAddBtnClicked(View view) {
        Intent intent = new Intent(this, NewParagonGroupActivity.class);
        int reqCode = 1;
        startActivityForResult(intent, reqCode);
    }


}
