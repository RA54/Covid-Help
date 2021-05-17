package com.foreantech.help.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.*;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.foreantech.help.After_detail_view;
import com.foreantech.help.Model.Detail_model;
import com.foreantech.help.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Detail_Adapter extends RecyclerView.Adapter<Detail_Adapter.ViewHolder> {

    Context mContext;
    List<Detail_model> obj_list_detail;

    public Detail_Adapter(Context mContext, List<Detail_model> obj_list_detail) {
        this.mContext = mContext;
        this.obj_list_detail = obj_list_detail;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_detail,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        // bind the data
        Detail_model detail_model_obj = obj_list_detail.get(position);
        holder.songTitle.setText(obj_list_detail.get(position).getName());
        Picasso.get().load(obj_list_detail.get(position).getImage()).into(holder.songCoverImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext.getApplicationContext(), After_detail_view.class);
                intent.putExtra("url_loader",detail_model_obj.getUrl_for_intent());
                intent.putExtra("header_title",detail_model_obj.getName());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);;
            }
        });

    }

    @Override
    public int getItemCount() {
        return obj_list_detail.size();
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
