package com.one.firebaseapplication.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.one.firebaseapplication.Model.CommonModel;
import com.one.firebaseapplication.R;
import com.one.firebaseapplication.Utils;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private Context mContext;
    private List<CommonModel> mDatalist;

    public UserAdapter(Context mContext,List<CommonModel> mDatalist){
        this.mContext = mContext;
        this.mDatalist = mDatalist;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.item_view_list,parent,false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CommonModel commonModel = mDatalist.get(position);
        holder.txt_getName.setText(commonModel.getName()+ " | " +commonModel.getAge());

        holder.txt_getName.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                String userId = commonModel.getuId();
                Log.e("TEST","Get User Id :"+userId);
                Task<Void> voidTask = Utils.removeUsers(userId);
                voidTask.addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(mContext,"User remove from database....",Toast.LENGTH_LONG).show();
                    }
                });
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatalist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txt_getName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_getName = itemView.findViewById(R.id.txt_getName);
        }
    }
}
