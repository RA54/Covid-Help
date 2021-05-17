package com.foreantech.help.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.foreantech.help.Detail;
import com.foreantech.help.Model.Model_Main;
import com.foreantech.help.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter_Main extends RecyclerView.Adapter<Adapter_Main.ViewHolder> {
    Context context;
    List<Model_Main> obj_sootradhar;

    public Adapter_Main(Context context, List<Model_Main> obj_sootradhar) {
        this.context = context;
        this.obj_sootradhar = obj_sootradhar;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_main,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        // bind the data
        Model_Main currentItem= obj_sootradhar.get(position);
        holder.songTitle.setText(obj_sootradhar.get(position).getName());
        Picasso.get().load(obj_sootradhar.get(position).getUrl()).into(holder.songCoverImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), Detail.class);
                intent.putExtra("de_id",currentItem.getId());
                intent.putExtra("detial_tittle",currentItem.getName());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return obj_sootradhar.size();
    }

    public  class ViewHolder extends  RecyclerView.ViewHolder{
        TextView songTitle;
        ImageView songCoverImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            songTitle = itemView.findViewById(R.id.songTitle);
            songCoverImage = itemView.findViewById(R.id.coverImage);
        }
    }
}
