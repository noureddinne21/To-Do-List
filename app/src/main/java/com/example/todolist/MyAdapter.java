package com.example.todolist;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViweHolder> {

    List<Listtodo> listitems;
    Context context;
    private ViweHolder holder;
    private int position;
    private db dbs;
    RecyclerView.Adapter adapter;
    RecyclerView recyclerView;

    public MyAdapter(List listitems, Context context) {
        this.listitems = listitems;
        this.context = context;
    }


    @NonNull
    @Override
    public ViweHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viwe = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo,parent,false);
        return new ViweHolder(viwe);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViweHolder holder, @SuppressLint("RecyclerView") int position) {

        dbs = new db(context);

        Listtodo item = listitems.get(position);
        holder.textView.setText(item.getTodo());

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder d=new AlertDialog.Builder(context);
                d.setTitle("Delit")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        listitems.remove(position);
                        dbs.deleteCourse(item.getTodo());
                        notifyDataSetChanged();

                    }
                })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("position", String.valueOf(position));
                    }
                })

                        .show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return listitems.size();
    }

    public class ViweHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;

        public ViweHolder(@NonNull View itemView) {
            super(itemView);

            textView=itemView.findViewById(R.id.textView);
            imageView = itemView.findViewById(R.id.imageView);

        }




    }
}
