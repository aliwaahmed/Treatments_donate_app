package com.logapps.treatments_donate_app.donate.ph_donates;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;
import com.logapps.treatments_donate_app.Person.replace_data.UserClick;
import com.logapps.treatments_donate_app.Pharmacy.Exc_data.ExcAdapter;
import com.logapps.treatments_donate_app.Pharmacy.Exc_data.ExcClass;
import com.logapps.treatments_donate_app.Pharmacy.Exc_data.Excessive_Activity;
import com.logapps.treatments_donate_app.R;
import com.logapps.treatments_donate_app.donate.data.All_needs_Adapter;
import com.logapps.treatments_donate_app.donate.data.Donate_DetailsActivity;

import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class Ph_DonatesAdapter extends RecyclerView.Adapter<Ph_DonatesAdapter.ViewHolder> {

    private Ph_DonatesActivity mContext;
    public List<Ph_Donates_class> data = Collections.emptyList();
    Ph_Donates_class current;
    public String TAG = "taaaaaaaag";
    private UserClick lOnClickListener;


    public Ph_DonatesAdapter(UserClick listener) {
        lOnClickListener = listener;
    }

    public void setUserDataa(List<Ph_Donates_class> rec, Ph_DonatesActivity ph_donatesActivity){
        data = rec ;
        mContext = ph_donatesActivity ;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.ph_donates_raw;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem, parent, false);

        return new Ph_DonatesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        final Ph_DonatesAdapter.ViewHolder holder1 = (Ph_DonatesAdapter.ViewHolder) holder ;
        current = data.get(position);

        holder1.name.setText(current.getName());
        holder1.t_name.setText(current.getAddress());
        holder1.prize.setText(current.getPrice());
        holder1.name2.setText(current.getDetails());
        holder1.date.setText(current.getDate());


        ((Ph_DonatesAdapter.ViewHolder)holder).cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //name , addreess , img , price , phone
                Intent i = new Intent(mContext.getApplicationContext(), Ph_Donates_Details_Activity.class);
                i.putExtra("details" , data.get(position).getName());
                i.putExtra("donate_prize" , data.get(position).getPrice());
                i.putExtra("donate_address" , data.get(position).getAddress());
                i.putExtra("donate_call" , data.get(position).getPhone());
                i.putExtra("exc_image" , data.get(position).getImage());
                i.putExtra("date" , data.get(position).getDate());
                mContext.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView ;
        TextView name , t_name , prize , date , name2;
        ImageView imageView , t_image ;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = (CardView)itemView.findViewById(R.id.card);
            name = (TextView)itemView.findViewById(R.id.card_name);
            name2 = (TextView)itemView.findViewById(R.id.card_t_name2);
            date = (TextView)itemView.findViewById(R.id.card_t_date);
            t_name = (TextView)itemView.findViewById(R.id.card_t_name);
            prize = (TextView)itemView.findViewById(R.id.card_t_prize);
            imageView = (ImageView) itemView.findViewById(R.id.card_img);
            t_image = (ImageView)itemView.findViewById(R.id.card_t_image);
        }
    }
}
