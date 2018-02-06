package com.codepath.greenhouse;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codepath.greenhouse.Model.UserModel;

import java.util.ArrayList;

/**
 * Created by uchit on 05/02/2018.
 */

class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {
    Context applicationContext;
    int row_user;
    ArrayList<UserModel> arrayList;

    public UserAdapter(Context applicationContext, int row_user, ArrayList<UserModel> arrayList) {
        this.applicationContext = applicationContext;
        this.row_user = row_user;
        this.arrayList = arrayList;
    }
    //load the row layout
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(row_user,parent, false));
    }
   // Load data into individual UI
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mName.setText(arrayList.get(position).getmName());
        holder.mAge.setText(String.valueOf(arrayList.get(position).getAge()));
        holder.mDate.setText(String.valueOf(arrayList.get(position).getmDate()));
     //   holder.mCountry.setText(arrayList.get(position).getmCountry());
       // holder.mAge.setText(arrayList.get(position).getAge());


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

/*
2. Create Inner ViewHolder class
     2.1 MyviewHolder extend View
     2.2 Create a construtor of viewholder class:initialize the row elements  then implement
*/

    public class MyViewHolder extends RecyclerView.ViewHolder  {
        /*
       Cache View references
        */
        private TextView mName, mAge, mDate, mCountry, mGender, mPostcode;

        public MyViewHolder(View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.rvName);
            mAge = itemView.findViewById(R.id.rvAge);
            mDate = itemView.findViewById(R.id.rvDate);
           // mCountry = itemView.findViewById(R.id.rvCountry);
        }
    }
}
