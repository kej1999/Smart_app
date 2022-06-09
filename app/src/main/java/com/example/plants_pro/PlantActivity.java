package com.example.plants_pro;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.sql.Array;
import java.util.ArrayList;

public class PlantActivity extends Activity{
    Dialog dialog;
    ImageView choicePic;
    ListView listView;

    final ArrayList<item> list = new ArrayList<>();
    CustomAdapter adapter = new CustomAdapter(PlantActivity.this,list);


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plant);

        dialog = new Dialog(PlantActivity.this);
        dialog.setContentView(R.layout.new_plant);
        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        Button btnReturn = (Button) findViewById(R.id.btnReturn);
        Button btnNew = (Button) findViewById(R.id.btnNew);

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //String plantN = (String)adapterView.getItemAtPosition(position);
                //Bitmap bitmap = (Bitmap)adapterView.getItemAtPosition(position);

                Intent info = new Intent(PlantActivity.this,ContentActivity.class);
                //info.putExtra("name",plantN);

                startActivity(info);
            }
        });


    }

    public void showDialog(){
        dialog.show();

        Button btnPic = dialog.findViewById(R.id.btnPic);
        Button btnOk = dialog.findViewById(R.id.btnOk);
        EditText editName = dialog.findViewById(R.id.editName);

        btnPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent data = new Intent();
                data.setType("image/*");
                data.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(data,0);
            }
        });


        btnOk.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                int count;
                count = adapter.getCount();

                String name = editName.getText().toString();
                BitmapDrawable bitmapDrawable = (BitmapDrawable)choicePic.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                list.add(new item(bitmap,name));
                adapter.notifyDataSetChanged();
                dialog.dismiss();

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        choicePic = dialog.findViewById(R.id.choicePic);
        if(requestCode == 0){
            if(resultCode == RESULT_OK){
                Glide.with(getApplicationContext()).load(data.getData()).override(500,500).into(choicePic);
            }
        }
    }


}
