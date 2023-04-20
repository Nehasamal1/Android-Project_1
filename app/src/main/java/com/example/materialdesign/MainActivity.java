package com.example.materialdesign;

import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("Users");
        usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    User user = userSnapshot.getValue(User.class);
                    Log.d(TAG, "Retrieved user: " + user.getName() + ", " + user.getEmail() + ", " + user.getPhoneNo());

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "onCancelled", databaseError.toException());

            }
        });


    }

    public class User {
        private String name;
        private String email;
        private String phoneNo;

        public User() {}

        public User(String name, String email, String phoneNo) {
            this.name = name;
            this.email = email;
            this.phoneNo = phoneNo;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public String getPhoneNo() {
            return phoneNo;
        }
    }

}