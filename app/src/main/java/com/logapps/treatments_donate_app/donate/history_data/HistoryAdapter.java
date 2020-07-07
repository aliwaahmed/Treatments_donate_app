package com.logapps.treatments_donate_app.donate.history_data;

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

import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {


    private DonateHistoryActivity mContext ;
    public List<History_class> data = Collections.emptyList();
    History_class current;
    public String TAG = "taaaaaaaag";
    private UserClick lOnClickListener;
    FirebaseUser currentUser ;
    DatabaseReference databaseReference ;



    public HistoryAdapter(DonateHistoryActivity historyActivity) {
        lOnClickListener = historyActivity;
    }


    public void setUsersData(List<History_class> recipesIn, DonateHistoryActivity historyActivity  ) {
        data = recipesIn;
        mContext = historyActivity;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();

        int layoutIdForListItem = R.layout.history_raw;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem, parent, false);

        return new HistoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final HistoryAdapter.ViewHolder holder1 = (HistoryAdapter.ViewHolder) holder ;
        current = data.get(position);

        holder.name.setText(current.getName());
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
