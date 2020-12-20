package com.one.firebaseapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


public class CloudStorageExample extends AppCompatActivity {

    //private StorageReference mRef;
    private StorageReference mRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloud_storage_example);

        ((Button) findViewById(R.id.btn_run)).setOnClickListener(this::runCode);
        mRef = FirebaseStorage.getInstance().getReference("docs/");
    }

    private void runCode(View view) {
        StorageReference child = mRef.child("office/abc.txt");
        String data = "This is demo data...";
        UploadTask uploadTask = child.putBytes(data.getBytes());
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(CloudStorageExample.this,"File uploaded..",Toast.LENGTH_LONG).show();
            }
        });
    }
}