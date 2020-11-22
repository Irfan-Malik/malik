package com.faaqed.imagespicker;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;

import java.io.File;

public class MainActivity extends AppCompatActivity implements  ImagePicker.ImagePickerListener, View.OnClickListener {

    String TAG = "Gallery";
    private String encodImage = null;
    ImageView imgDisplay;
    Button uploadImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setView();
        setActions();
    }

    public void setView(){
        imgDisplay = (ImageView)findViewById(R.id.imageView);
        uploadImage = (Button) findViewById(R.id.uploadImage);
    }

    public void setActions(){
        uploadImage.setOnClickListener(this);
    }

    private void displayImagePicker() {
        ImagePicker picker = ImagePicker.newInstance();
        picker.setImagePickerListener(this);
        picker.show(getSupportFragmentManager(), TAG);
    }

    @Override
    public void OnPhotoRemove() {

    }

    @Override
    public void OnImageFound(File file, byte[] bytes, boolean fromCamera) {
        Bitmap myBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        imgDisplay.setImageBitmap(myBitmap);
        encodImage = Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.uploadImage:
                displayImagePicker();
                break;
        }
    }
}