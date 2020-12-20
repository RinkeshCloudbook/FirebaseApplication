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

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.one.firebaseapplication.Model.CommonModel;

import java.util.HashMap;
import java.util.Map;

public class InsertMultipleValue extends AppCompatActivity {

    private Button btn_runcode,btn_GetCode;
    private EditText edt_name,edt_age;
    private TextView txt_getData;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    private ValueEventListener mlistner;
    private ChildEventListener mChildListner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_multiple_value);

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

        mChildListner = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                CommonModel cm = snapshot.getValue(CommonModel.class);

                Log.e("TEST","Name :"+cm.getName());
                Log.e("TEST","Age :"+cm.getAge());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

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
        };

        mRef.addChildEventListener(mChildListner);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRef.removeEventListener(mChildListner);
    }

    private void getFBValue(View view) {

    }

    private void runCode(View view) {
        String name = edt_name.getText().toString();
        String age = edt_age.getText().toString();

        CommonModel cm = new CommonModel(name,age);

        String key = mRef.push().getKey();
        mRef.child(key).setValue(cm);
        /*Map<String,Object> insertData = new HashMap<>();
        insertData.put("Name",name);
        insertData.put("Age",age);

        String key = mRef.push().getKey();
        mRef.child(key).setValue(insertData);*/

        Toast.makeText(this,"Data Inserted....",Toast.LENGTH_LONG).show();
    }
}