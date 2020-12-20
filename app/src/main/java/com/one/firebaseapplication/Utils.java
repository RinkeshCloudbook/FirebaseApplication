package com.one.firebaseapplication;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

public class Utils {

    public static Task<Void> removeUsers(String userId){
        Task<Void> task = FirebaseDatabase.getInstance().getReference("users")
                .child(userId)
                .setValue(null);
        return task;
    }
}
