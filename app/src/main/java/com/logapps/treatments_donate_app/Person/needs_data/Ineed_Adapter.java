package com.logapps.treatments_donate_app.Person.needs_data;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.logapps.treatments_donate_app.Person.replace_data.Replace_adapter;
import com.logapps.treatments_donate_app.Person.replace_data.UserClick;
import com.logapps.treatments_donate_app.R;
import com.logapps.treatments_donate_app.donate.Donate_home_activity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


public class Ineed_Adapter extends RecyclerView.Adapter<Ineed_Adapter.ViewHolder> {

    private Ineed mContext;
    public List<Ineed_class> data = Collections.emptyList();
    Ineed_class current;
    public String TAG = "taaaaaaaag";
    private UserClick lOnClickListener;
    FirebaseUser currentUser ;
    DatabaseReference databaseReference ;

    public Ineed_Adapter(Ineed listener){
        lOnClickListener = listener;
    }

    public Ineed_Adapter(Donate_home_activity donate_home_activity) {
        lOnClickListener = donate_home_activity;
    }


    public void setUsersData(List<Ineed_class> recipesIn, Ineed ineed  ) {
        data = recipesIn;
        mContext = ineed;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        int layoutIdForListItem = R.layout.needs_raw;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem, parent, false);

        return new Ineed_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        //final Ineed_Adapter.ViewHolder holder1 = (Ineed_Adapter.ViewHolder) holder ;
        current = data.get(position);

        holder.name.setText(current.getNeed_name());
        holder.t_name.setText(current.getNeed_details());

        databaseReference= FirebaseDatabase.getInstance().getReference();
        final String uid = databaseReference.getKey();

        ((ViewHolder)holder).cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(mContext.getContext(), Needs_detailsActivity.class);
                i.putExtra("name" , data.get(position).getNeed_name());
                i.putExtra("details" , data.get(position).getNeed_details());
                i.putExtra("donate_call" , data.get(position).getPhone_number());
                i.putExtra("donate_address" , data.get(position).getAddress());
                i.putExtra("id" , data.get(position).getId());
                i.putExtra("t_image" , data.get(position).getNeed_image());
                i.putExtra("t_image" , data.get(position).getNeed_image());

                mContext.startActivity(i);
            }
        });

    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CardView cardView ;
        TextView name , t_name ;
        ImageView imageView , t_image ;
        private FirebaseUser mCurrentUser ;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = (CardView)itemView.findViewById(R.id.card);
            name = (TextView)itemView.findViewById(R.id.card_name);
            t_name = (TextView)itemView.findViewById(R.id.card_t_name);
            imageView = (ImageView) itemView.findViewById(R.id.card_img);
            t_image = (ImageView)itemView.findViewById(R.id.card_t_image);

            imageView.setVisibility(View.GONE);

            mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
            final String current_uid = mCurrentUser.getUid();


            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            int clickedPosition = getAdapterPosition();
            lOnClickListener.asd(data.get(clickedPosition));
        }
    }
}
