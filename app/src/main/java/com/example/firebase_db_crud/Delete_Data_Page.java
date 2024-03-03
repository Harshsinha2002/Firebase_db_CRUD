package com.example.firebase_db_crud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Delete_Data_Page extends AppCompatActivity {

    Button View_Data,Update_Data,Save_Data, Delete_btn;
    EditText Email_editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_data_page);

        Email_editText = findViewById(R.id.Email_editTxt);
        View_Data = findViewById(R.id.View_Data);
        Update_Data = findViewById(R.id.Update_Data);
        Save_Data = findViewById(R.id.Save_Data);
        Delete_btn = findViewById(R.id.Delete_btn);

        DatabaseReference db;


        View_Data.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(Delete_Data_Page.this, View_Data_Page.class));
            }
        });

        Update_Data.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(Delete_Data_Page.this, Update_Data_Page.class));
            }
        });

        Save_Data.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(Delete_Data_Page.this, MainActivity.class));
            }
        });

        db = FirebaseDatabase.getInstance().getReference("Student");
        Delete_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String Email = Email_editText.getText().toString().replace(".", "");
                db.child(Email).removeValue();
                Toast.makeText(Delete_Data_Page.this, "Deleted Sucessfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}