package com.one.firebaseapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity {
    String TAG = "TAG";
    private Button btn_runcode,btn_GetCode;
    private EditText edt_name,edt_age;
    private TextView txt_getData;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    private ValueEventListener mlistner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_name = findViewById(R.id.edt_name);
        btn_runcode = findViewById(R.id.btn_runcode);
        txt_getData = findViewById(R.id.txt_getData);
        btn_GetCode = findViewById(R.id.btn_GetCode);
        edt_age = findViewById(R.id.edt_age);

        mDatabase = FirebaseDatabase.getInstance();
        //mRef = mDatabase.getReference();
        mRef = mDatabase.getReference("users");

        this.btn_runcode.setOnClickListener(this::runCode);
        this.btn_GetCode.setOnClickListener(this::getFBValue);

        /*mlistner = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String getValue = snapshot.getValue(String.class);
                txt_getData.setText(getValue);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        mRef.child("user1").addListenerForSingleValueEvent(mlistner);*/
    }

  /*  @Override
    protected void onDestroy() {
        super.onDestroy();
        mRef.child("user1").removeEventListener(mlistner);
    }*/

    private void getFBValue(View view) {
        /*mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String getValue = snapshot.getValue(String.class);
                txt_getData.setText(getValue);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/
       /* mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //String getValue = snapshot.getValue(String.class);

                for (DataSnapshot snapshot1:snapshot.getChildren()) {
                    Map<String,Object> data = (Map<String, Object>) snapshot1.getValue();
                    Log.e("TEST","Name :"+data.get("Name"));
                    Log.e("TEST","Age :"+data.get("Age"));
                    Log.e("TEST","KEY :"+snapshot1.getKey());
                }

                *//*Map<String,Object> data = (Map<String, Object>) snapshot.getValue();
                Log.e("TEST","Name :"+data.get("Name"));
                Log.e("TEST","Age :"+data.get("Age"));*//*
                //txt_getData.setText(getValue);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/
        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Map<String,Object> data = (Map<String, Object>) snapshot.getValue();
                Log.e("TEST","Name :"+data.get("Name"));
                Log.e("TEST","Age :"+data.get("Age"));
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Log.e("TEST","OnChange");
                Map<String,Object> data = (Map<String, Object>) snapshot.getValue();
                Log.e("TEST","Age :"+data.get("Age"));
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void runCode(View view) {
        String name = edt_name.getText().toString();
        String age = edt_age.getText().toString();
        //mRef.setValue(data);
        /*mRef.child("user1").child("Name").setValue(name);
        mRef.child("user1").child("Age").setValue(age);*/

        String key = mRef.push().getKey();
        mRef.child(key).child("Name").setValue(name);
        mRef.child(key).child("Age").setValue(age);

        /*Map<String,Object> updateValues = new HashMap<>();
        updateValues.put("/user1/Name",name);
        updateValues.put("user1/Age",age);

        mRef.updateChildren(updateValues);*/

        //mRef.child("user1").child("Name").setValue(null);

       /* Task<Void> task = mRef.child("user1").removeValue();
        task.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG,"OnSuccess : Data REMOVED");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG,"onFailure : Data REMOVED FAILED");
            }
        });*/

        Toast.makeText(this,"Data Inserted..",Toast.LENGTH_LONG).show();
    }
}