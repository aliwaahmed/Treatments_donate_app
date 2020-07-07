package com.logapps.treatments_donate_app.Person.Accepted;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.logapps.treatments_donate_app.Person.needs_data.Ineed;
import com.logapps.treatments_donate_app.Person.replace_data.UserClick;
import com.logapps.treatments_donate_app.R;
import com.logapps.treatments_donate_app.donate.Donate_home_activity;
import com.logapps.treatments_donate_app.donate.data.All_needs_Adapter;
import com.logapps.treatments_donate_app.donate.data.All_needs_class;
import com.logapps.treatments_donate_app.donate.history_data.HistoryAdapter;

import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class AcceptedAdapter extends RecyclerView.Adapter<AcceptedAdapter.ViewHolder> {

    private Accepted mContext ;
    public List<Accepted_Class> data = Collections.emptyList();
    Accepted_Class current;
    public String TAG = "taaaaaaaag";
    private UserClick lOnClickListener;
    FirebaseUser currentUser ;
    DatabaseReference databaseReference ;


    public AcceptedAdapter(Accepted listener) {
        lOnClickListener = listener;
    }


    public void setUsersData(List<Accepted_Class> recipesIn, Accepted accepted  ) {
        data = recipesIn;
        mContext = accepted;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public AcceptedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();

        int layoutIdForListItem = R.layout.accepted_raw;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem, parent, false);

        return new AcceptedAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AcceptedAdapter.ViewHolder holder, int position) {

        final AcceptedAdapter.ViewHolder holder1 = (AcceptedAdapter.ViewHolder) holder ;
        current = data.get(position);

        holder.name.setText(current.getTreat_name());
        holder.date.setText(current.getDate());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView ;
        TextView name , date ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = (CardView)itemView.findViewById(R.id.card);
            name = (TextView)itemView.findViewById(R.id.card_name);
            date = (TextView)itemView.findViewById(R.id.card_date);
        }
    }
}
