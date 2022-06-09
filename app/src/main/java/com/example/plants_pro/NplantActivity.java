package com.example.plants_pro;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

public class NplantActivity extends Activity {

    public EditText EditName;
    public ImageView choicePic;
    public Button btnPic;
    public Button btnOk;
    public String name;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Intent data = new Intent(NplantActivity.this,PlantActivity.class);
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.new_plant);
        EditName = (EditText) findViewById(R.id.editName);
        btnPic = (Button)findViewById(R.id.btnPic);
        btnOk = (Button) findViewById(R.id.btnOk);

        btnPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,0);
            }
        });
        name = EditName.getText().toString();

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.putExtra("name",name);
                startActivityForResult(data,2);
                finish();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        choicePic = (ImageView)findViewById(R.id.choicePic);
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0){
            if(resultCode == RESULT_OK){
                Glide.with(getApplicationContext()).load(data.getData()).override(500,500).into(choicePic);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }



}
