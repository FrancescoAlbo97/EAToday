package com.eatoday.ui.recipes;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eatoday.R;

import java.util.ArrayList;

public class StringAdapter extends RecyclerView.Adapter<StringAdapter.ViewHolder> {

    private ArrayList<String> strings;
    private StringAdapter.ItemClicked activity;
    private Context context;
    private Activity thisClass;



    public StringAdapter(Activity thisClass, Context context, ArrayList<String> list){
        this.strings = list;
        this.context = context;
        this.thisClass = thisClass;
    }

    public String getString(int i) {
        return strings.get(i);
    }

    public interface ItemClicked{
        void onItemClicked(int index);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvString;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            tvString = itemView.findViewById(R.id.tvString);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }

        public String getTvStringToString() {
            return tvString.getText().toString();
        }
    }
    @NonNull
    @Override
    public StringAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_string, parent,false);
        return new StringAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StringAdapter.ViewHolder holder, int position) {
        String string = strings.get(position);
        holder.itemView.setTag(string);
        holder.tvString.setText(string);
    }

    @Override
    public int getItemCount() {
        return strings.size();
    }

}
