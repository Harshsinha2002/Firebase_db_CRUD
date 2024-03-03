package com.example.firebase_db_crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    EditText name;
    EditText email;
    EditText age;

    TextView output;

    Button save_btn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button  View = findViewById(R.id.View_Data);
        Button Update = findViewById(R.id.Update_Data);
        Button Delete = findViewById(R.id.Delete_Data);

        name = findViewById(R.id.name_editTxt);
        email = findViewById(R.id.email_editTxt);
        age = findViewById(R.id.age_editTxt);
        save_btn = findViewById(R.id.save_btn);
        output = findViewById(R.id.output);


        View.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, View_Data_Page.class));
            }
        });

        Update.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, Update_Data_Page.class));
            }
        });

        Delete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, Delete_Data_Page.class));
            }
        });

        save_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String Name = name.getText().toString();
                String Email = email.getText().toString();
                String Age = age.getText().toString();

                HashMap<String, String> map = new HashMap<>();
                map.put("Name", Name);
                map.put("Email", Email);
                map.put("Age", Age);
                DatabaseReference db = FirebaseDatabase.getInstance().getReference("Student");

                try
                {
                    if( db.child(Email.replace(".",""))!= null)
                    {
                        db.child(Email.replace(".", "")).setValue(map);
                    }
                }
                catch (Exception e)
                {
                    Toast.makeText(MainActivity.this, "Error",Toast.LENGTH_SHORT).show();
                }


                output.setText(Name + " " + Age + " " + Email);
            }
        });
    }
}