package com.logapps.treatments_donate_app.Pharmacy.P_data;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.logapps.treatments_donate_app.Person.needs_data.Ineed;
import com.logapps.treatments_donate_app.Person.replace_data.UserClick;
import com.logapps.treatments_donate_app.Pharmacy.Ph_Details_activity;
import com.logapps.treatments_donate_app.Pharmacy.Ph_home_activity;
import com.logapps.treatments_donate_app.R;
import com.logapps.treatments_donate_app.donate.Donate_home_activity;
import com.logapps.treatments_donate_app.donate.data.All_needs_Adapter;
import com.logapps.treatments_donate_app.donate.data.All_needs_class;
import com.logapps.treatments_donate_app.donate.ph_donates.Ph_DonatesAdapter;
import com.logapps.treatments_donate_app.donate.ph_donates.Ph_Donates_Details_Activity;

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
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        final P_Adapter.ViewHolder holder1 = (P_Adapter.ViewHolder) holder ;
        current = data.get(position);

        holder1.name.setText(current.getName());
        holder1.t_name.setText(current.getDetails());

//        private String Name ;
//        private String Details ;
//        private String Img ;
//        private String Address ;
//        private String Phone_number ;

//        details:
//        "Panadol "
//        donate_address:
//        "Cairo "
//        donate_call:
//        "01011933204"
//        donate_prize:
//        "100"
//        em:
//        "X"
//        name:
//        "m1"
//        profile_image:
//        "https://firebasestorage.googleapis.com/v0/b/tre..."
//        t_image:
//        "https://firebasestorage.googleapis.com/v0/b/tre..."


        ((P_Adapter.ViewHolder)holder).cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //name , addreess , img , price , phone
                Intent i = new Intent(mContext.getApplicationContext(), Ph_Details_activity.class);
                i.putExtra("details" , data.get(position).getName());
                i.putExtra("donate_address" , data.get(position).getAddress());
                i.putExtra("donate_call" , data.get(position).getPhone_number());
                i.putExtra("t_image" , data.get(position).getImg());
                i.putExtra("em" , data.get(position).getEf_material());
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
