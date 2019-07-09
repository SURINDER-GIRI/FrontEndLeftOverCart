package com.example.ui_left_final;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class upComingEventAdapter extends RecyclerView.Adapter<upComingEventAdapter.ViewHolder>{

    private ArrayList <String> eventName = new ArrayList<>();
    private ArrayList<String> eventId = new ArrayList<>();
    private Context  mcontext;
    private String recogonise;
    public upComingEventAdapter(Context context,ArrayList<String> eventName, ArrayList<String> eventId,String recogonise ) {
        this.eventName = eventName;
        this.eventId = eventId;
        this.recogonise = recogonise;
        mcontext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.upcoming_events,parent,false);
       ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.Name.setText(eventName.get(position));

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (recogonise == "volunteer") {
                    ((Volunteer_HomePage) mcontext).goToDescription(eventId.get(position));
                }

                else {


                    ((FoodDonor_HomePage) mcontext).goToDescription(eventId.get(position));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return eventName.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView Name;
        Button btn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.description);
            btn = itemView.findViewById(R.id.btnDes);

        }
    }
}
