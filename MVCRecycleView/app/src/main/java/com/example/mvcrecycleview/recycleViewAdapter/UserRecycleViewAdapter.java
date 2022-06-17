package com.example.mvcrecycleview.recycleViewAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvcrecycleview.R;
import com.example.mvcrecycleview.controller.EditActivity;
import com.example.mvcrecycleview.model.User;

import java.util.ArrayList;

public class UserRecycleViewAdapter extends RecyclerView.Adapter<UserRecycleViewAdapter.ViewHolder> {

    private ArrayList<User> objects = new ArrayList<>();
    private Context context;

    public UserRecycleViewAdapter(Context context, ArrayList<User> objects)
    {
        this.context = context;
        this.objects = objects;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.activity_main_element, parent, false);

        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = objects.get(position);

        String name = user.getName();
        String phone = user.getPhone();

        holder.name.setText( name);
        holder.phone.setText( phone);
        holder.parentLayout.setOnClickListener(view ->{
            Intent intent = new Intent(context, EditActivity.class);
            intent.putExtra("position", position);
            intent.putExtra("name", name);
            intent.putExtra("phone", phone);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView name;
        private TextView phone;
        private LinearLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            setupComponent(itemView);
        }


        private void setupComponent(View itemView)
        {
            name = itemView.findViewById(R.id.elementName);
            phone = itemView.findViewById(R.id.elementPhone);
            parentLayout = itemView.findViewById(R.id.parentLayout);
        }
    }
}
