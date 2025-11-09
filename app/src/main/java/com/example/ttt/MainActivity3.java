package com.example.ttt;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import com.google.android.gms.tasks.OnFailureListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity3 extends AppCompatActivity {
    Button add;
    EditText ageEdit,nameEdit,addresEdit,phoneEdit;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        add=findViewById(R.id.add_button);
        db=FirebaseFirestore.getInstance();
        ageEdit=findViewById(R.id.id_age);
        nameEdit=findViewById(R.id.id_name);
        addresEdit=findViewById(R.id.id_address);
        phoneEdit=findViewById(R.id.id_phone);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String age=ageEdit.getText().toString();
                String name=nameEdit.getText().toString();
                String address=addresEdit.getText().toString();
                String phone=phoneEdit.getText().toString();

                Map<String, Object> client = new HashMap<>();
                client.put("Name", name);
                client.put("Age", age);
                client.put("Address", address);
                client.put("Phone", phone);

                db.collection("Clients")
                        .add(client)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(MainActivity3.this, "success", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MainActivity3.this, "failed", Toast.LENGTH_SHORT).show();

                            }
                        });
            }
        });

    }
}