package com.example.demochat.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demochat.Message;
import com.example.demochat.R;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
    Context context;
    ArrayList<Message> TinNhans;

    public MessageAdapter(Context context, ArrayList<Message> tinNhans) {
        this.context = context;
        TinNhans = tinNhans;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_message, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(TinNhans.get(position).getTinNhan() == ""){
            holder.tvGui.setText(TinNhans.get(position).getTinGui());
            holder.container_nguoi_nhan.setVisibility(View.GONE);
            holder.tvGui.setVisibility(View.VISIBLE);
        }
        if(TinNhans.get(position).getTinGui() == ""){
            holder.tvNhan.setText(TinNhans.get(position).getTinNhan());
            holder.tvGui.setVisibility(View.GONE);
            holder.container_nguoi_nhan.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return TinNhans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout container_nguoi_nhan;
        TextView tvNhan;
        TextView tvGui;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNhan = (TextView) itemView.findViewById(R.id.tvNhan);
            tvGui = (TextView) itemView.findViewById(R.id.tvGui);
            container_nguoi_nhan = (LinearLayout) itemView.findViewById(R.id.container_nguoi_nhan);
        }
    }
}
