package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Listtodo> listitems;
    RecyclerView.Adapter adapter;
    RecyclerView recyclerView;
    Button add;
    EditText input;
    private db dbs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerView);
        add=findViewById(R.id.button);
        input=findViewById(R.id.editTextText);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);



        dbs = new db(this);
        listitems= dbs.getnots();



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (input.getText().length()==0){

                    Toast.makeText(MainActivity.this, "Please Add Text", Toast.LENGTH_SHORT).show();

                }else{

                    String note =  input.getText().toString();
                    Listtodo n= new Listtodo(note);

                    try {
                        dbs.Add_Note(n);
                    }
                    catch(Exception e) {
                        Log.d("info",String.valueOf(e.getMessage()));
                    }
                    input.setText("");
                    Toast.makeText(MainActivity.this, "Add", Toast.LENGTH_SHORT).show();

                    listitems= dbs.getnots();
                    adapter=new MyAdapter(listitems,MainActivity.this);
                    recyclerView.setAdapter(adapter);

                }

            }
        });

        adapter=new MyAdapter(listitems,this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}




























