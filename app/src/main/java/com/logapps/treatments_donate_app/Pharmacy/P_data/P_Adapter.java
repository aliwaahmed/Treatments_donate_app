package com.logapps.treatments_donate_app.Pharmacy.P_data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.logapps.treatments_donate_app.Person.needs_data.Ineed;
import com.logapps.treatments_donate_app.Person.replace_data.UserClick;
import com.logapps.treatments_donate_app.Pharmacy.Ph_home_activity;
import com.logapps.treatments_donate_app.R;
import com.logapps.treatments_donate_app.donate.Donate_home_activity;
import com.logapps.treatments_donate_app.donate.data.All_needs_Adapter;
import com.logapps.treatments_donate_app.donate.data.All_needs_class;

import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class P_Adapter extends RecyclerView.Adapter<P_Adapter.ViewHolder> {

    private Ph_home_activity mContext ;
    public List<P_class> data = Collections.emptyList();
    P_class current;
    public String TAG = "taaaaaaaag";
    private UserClick lOnClickListener;


    public P_Adapter(Ph_home_activity phHomeActivity) {
        lOnClickListener = (UserClick) phHomeActivity;
    }

    public void setUsersData(List<P_class> recipesIn, Ph_home_activity phHomeActivity  ) {
        data = recipesIn;
        mContext = phHomeActivity;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        int layoutIdForListItem = R.layout.needs_raw;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem, parent, false);

        return new P_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final P_Adapter.ViewHolder holder1 = (P_Adapter.ViewHolder) holder ;
        current = data.get(position);

        holder1.name.setText(current.getName());
        holder1.t_name.setText(current.getDetails());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        CardView cardView ;
        TextView name , t_name ;
        ImageView imageView , t_image ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            cardView = (CardView)itemView.findViewById(R.id.card);
            name = (TextView)itemView.findViewById(R.id.card_name);
            t_name = (TextView)itemView.findViewById(R.id.card_t_name);
            imageView = (ImageView) itemView.findViewById(R.id.card_img);
            t_image = (ImageView)itemView.findViewById(R.id.card_t_image);

        }
    }
}
