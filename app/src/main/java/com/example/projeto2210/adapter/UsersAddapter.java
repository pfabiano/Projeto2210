package com.example.projeto2210.adapter;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projeto2210.R;
import com.example.projeto2210.model.User;
import com.example.projeto2210.view.Activity2;

import java.util.List;


public class UsersAddapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<User> dados;

    public UsersAddapter(List<User> dados) {
        this.dados = dados;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutVH = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_user_vh,parent,false);
        return new UserViewHolder(layoutVH);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        User obj = dados.get(position);
        TextView tv1 = holder.itemView.findViewById(R.id.textViewId);
        tv1.setText(obj.getId()+"");
        ((TextView) holder.itemView.findViewById(R.id.textViewName)).setText(obj.getName());
        holder.itemView.setOnClickListener((view)->{
            Intent intent = new Intent(view.getContext(), Activity2.class);
            intent.putExtra("id",obj.getId());
            view.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return dados.size();
    }
}
class UserViewHolder extends RecyclerView.ViewHolder {
    public View view;
    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView;
    }
}
