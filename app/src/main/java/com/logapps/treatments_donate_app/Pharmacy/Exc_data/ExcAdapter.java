package com.logapps.treatments_donate_app.Pharmacy.Exc_data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.logapps.treatments_donate_app.Person.replace_data.Replace_adapter;
import com.logapps.treatments_donate_app.Person.replace_data.Replace_class;
import com.logapps.treatments_donate_app.Person.replace_data.Replacements_user;
import com.logapps.treatments_donate_app.Person.replace_data.UserClick;
import com.logapps.treatments_donate_app.R;
import com.logapps.treatments_donate_app.donate.ph_donates.Ph_DonatesActivity;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ExcAdapter extends RecyclerView.Adapter<ExcAdapter.ViewHolder> {

    private Excessive_Activity mContext;
    private Ph_DonatesActivity pContext;
    public List<ExcClass> data = Collections.emptyList();
    ExcClass current;
    public String TAG = "taaaaaaaag";
    private UserClick lOnClickListener;


    public ExcAdapter(UserClick listener) {
        lOnClickListener = listener;
    }

    public void setUserDataa(List<ExcClass> rec, Ph_DonatesActivity ph_donatesActivity){
        data = rec ;
        pContext = ph_donatesActivity ;
        notifyDataSetChanged();
    }


    public void setUsersData(List<ExcClass> recipesIn, Excessive_Activity excessive_activity ) {
        data = recipesIn;
        mContext = excessive_activity;
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.exc_raw;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem, parent, false);

        return new ExcAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final ExcAdapter.ViewHolder holder1 = (ExcAdapter.ViewHolder) holder ;
        current = data.get(position);

        holder1.name.setText(current.getName());
        holder1.t_name.setText(current.getAddress());
        holder1.prize.setText(current.getPrice());
        holder1.date.setText(current.getDate());
//        Picasso.with(mContext.getApplicationContext()).load(current.getImage()).into(holder.t_image);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        CardView cardView ;
        TextView name , t_name , prize , date;
        ImageView imageView , t_image ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = (CardView)itemView.findViewById(R.id.card);
            name = (TextView)itemView.findViewById(R.id.card_name);
            date = (TextView)itemView.findViewById(R.id.card_t_date);
            t_name = (TextView)itemView.findViewById(R.id.card_t_name);
            prize = (TextView)itemView.findViewById(R.id.card_t_prize);
            imageView = (ImageView) itemView.findViewById(R.id.card_img);
            t_image = (ImageView)itemView.findViewById(R.id.card_t_image);
        }
    }
}
