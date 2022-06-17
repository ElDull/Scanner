package com.example.scannerapp;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class FirebaseHandler {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public FirebaseHandler() {
        this.db = db;
    }

    public void addItem(@NonNull StoreItem item) {
        Map<String, Object> itemMap = new HashMap<>();
        itemMap.put("code", item.getCode());
        itemMap.put("name", item.getName());
        itemMap.put("price", item.getPrice());

        this.db.collection("items")
                .add(itemMap)
                .addOnSuccessListener(documentReference ->
                        Log.d(TAG, "DocumentSnapshot added with ID: "
                                + documentReference.getId()))
                .addOnFailureListener(e -> Log.w(TAG, "Error adding document", e));
    }
    public void getAllItems() {
        this.db.collection("items")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()){
                                Log.d(TAG, documentSnapshot.getId() + "=> " + documentSnapshot.getData());
                            }
                        }
                        else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }
    public void getItem(String code) {
        db.collection("items")
                .whereEqualTo("code", code)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
}
