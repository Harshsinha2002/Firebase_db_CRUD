package com.example.firebase_db_crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Update_Data_Page extends AppCompatActivity {

    EditText name;
    EditText email;
    EditText age;

    TextView output;

    Button Update_btn;

    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data_page);

        Button  View = findViewById(R.id.View_Data);
        Button Save = findViewById(R.id.Save_Data);
        Button Delete = findViewById(R.id.Delete_Data);

        name = findViewById(R.id.name_editTxt);
        email = findViewById(R.id.email_editTxt);
        age = findViewById(R.id.age_editTxt);
        Update_btn = findViewById(R.id.Update_btn);
        output = findViewById(R.id.output);

        View.setOnClickListener(new android.view.View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(Update_Data_Page.this, View_Data_Page.class));
            }
        });

        Save.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(Update_Data_Page.this, Update_Data_Page.class));
            }
        });

        Delete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(Update_Data_Page.this, Delete_Data_Page.class));
            }
        });


        Update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v)
            {
                String Email = email.getText().toString();
                String Name =  name.getText().toString();
                String  Age = age.getText().toString();

                HashMap<String, String> map = new HashMap<>();
                map.put("Name", Name);
                map.put("Email", Email);
                map.put("Age", Age);


                db = FirebaseDatabase.getInstance().getReference("Student");
                db.child(Email.replace(".", "")).setValue(map);
            }
        });

    }
}