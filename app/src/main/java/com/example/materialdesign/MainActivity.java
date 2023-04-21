package com.example.materialdesign;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private EditText mName;
    private EditText mEmail;
    private EditText mPhoneNo;
    private Button saveButton;


    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("Users");

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mName = findViewById(R.id.name);
        mEmail = findViewById(R.id.email);
        mPhoneNo = findViewById(R.id.phoneno);
        saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                String name = mName.getText().toString();
                String email = mEmail.getText().toString();
                String phoneno = mPhoneNo.getText().toString();

                HashMap<String, String> userMap = new HashMap<>();
                userMap.put("name", name);
                userMap.put("email", email);
                userMap.put("phoneno", phoneno);

                root.push().setValue(userMap);

            }
        });

    }

}