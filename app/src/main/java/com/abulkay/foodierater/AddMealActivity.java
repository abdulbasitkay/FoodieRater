package com.abulkay.foodierater;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class AddMealActivity extends ActionBarActivity {

    Toolbar toolbar;
    ImageButton cameraButton;
    ImageView iv;
    Button saveButton;
    FileOutputStream fOut;
    EditText mealNameTxtBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_meal);

        mealNameTxtBox = (EditText) findViewById(R.id.mealName);
        cameraButton = (ImageButton) findViewById(R.id.imageView);
        iv = (ImageView) findViewById(R.id.image_display);
        saveButton = (Button) findViewById(R.id.save);


        try {
            fOut = openFileOutput("file name here", MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            Toast.makeText(AddMealActivity.this, "Sorry an error occured\n Please restart the app",
                    Toast.LENGTH_SHORT).show();
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivityForResult(intent, 0);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = "data";
                try {
                    fOut.write(str.getBytes());
                    fOut.close();
                } catch (IOException ignored) {
                    Toast.makeText(AddMealActivity.this, "Sorry an error occured\n Please restart the app",
                            Toast.LENGTH_SHORT).show();

                }


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        iv.setImageBitmap(bitmap);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}