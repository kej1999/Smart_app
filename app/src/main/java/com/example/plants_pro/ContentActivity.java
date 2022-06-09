package com.example.plants_pro;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;


public class ContentActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plant_info);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        TextView textView = (TextView)findViewById(R.id.pltTitle);
        ImageView imageView = (ImageView) findViewById(R.id.pltPic);
        byte[] arr = intent.getByteArrayExtra("image");
        Bitmap bm = BitmapFactory.decodeByteArray(arr,0,arr.length);

        textView.setText(name);
        imageView.setImageBitmap(bm);



        Button btnRe = (Button) findViewById(R.id.btnReturn2);
        btnRe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
