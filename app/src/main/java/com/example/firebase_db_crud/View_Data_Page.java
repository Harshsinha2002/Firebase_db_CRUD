package com.example.firebase_db_crud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class View_Data_Page extends AppCompatActivity {

    EditText name;
    EditText email;
    EditText age;

    EditText DataToView;
    Button View_btn;

    DatabaseReference db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data_page);

        Button  Save = findViewById(R.id.Save_Data);
        Button Update = findViewById(R.id.Update_Data);
        Button Delete = findViewById(R.id.Delete_Data);

        name = findViewById(R.id.name_editTxt);
        email = findViewById(R.id.email_editTxt);
        age = findViewById(R.id.age_editTxt);
        View_btn = findViewById(R.id.View_btn);
        DataToView = findViewById(R.id.Data_to_View);

        Save.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(View_Data_Page.this, MainActivity.class));
            }
        });

        Update.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(View_Data_Page.this, Update_Data_Page.class));
            }
        });

        Delete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(View_Data_Page.this, Delete_Data_Page.class));
            }
        });

        View_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String Email_View = DataToView.getText().toString().trim().replace(".","");
                if(Email_View.isEmpty())
                {
                    Toast.makeText(View_Data_Page.this, "Enter the Email To View", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    db = FirebaseDatabase.getInstance().getReference("Student");
                    db.child(Email_View).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task)
                        {
                            if(task.isSuccessful()) /* checks wether a child of name Email_View exists or not*/
                            {
                                if(task.getResult().exists()) /*checks wether data in child of name Email_View exists or not*/
                                {
                                    DataSnapshot dataSnapshot = task.getResult();
                                    String Name = String.valueOf(dataSnapshot.child("Name").getValue());
                                    String Age = String.valueOf(dataSnapshot.child("Age").getValue());
                                    String Email = String.valueOf(dataSnapshot.child("Email").getValue());

                                    name.setText(Name);
                                    age.setText(Age);
                                    email.setText(Email);
                                }

                                else
                                {
                                    Toast.makeText(View_Data_Page.this, "No child exists", Toast.LENGTH_SHORT).show();
                                }
                            }

                            else
                            {
                                Toast.makeText(View_Data_Page.this, "No data exists", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });



    }
}