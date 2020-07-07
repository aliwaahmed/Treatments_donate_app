package com.logapps.treatments_donate_app.Person.Search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.logapps.treatments_donate_app.Person.needs_data.Ineed;
import com.logapps.treatments_donate_app.Person.needs_data.Ineed_Adapter;
import com.logapps.treatments_donate_app.Person.needs_data.Ineed_class;
import com.logapps.treatments_donate_app.Person.replace_data.UserClick;
import com.logapps.treatments_donate_app.R;

import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class Search_Adapter extends RecyclerView.Adapter<Search_Adapter.ViewHolder> {

    private Search_activity mContext;
    public List<Search_class> data = Collections.emptyList();
    Search_class current;
    public String TAG = "taaaaaaaag";
    private UserClick lOnClickListener;

    public Search_Adapter(Search_activity listener){
        lOnClickListener = listener;
    }


    public void setUsersData(List<Search_class> recipesIn, Search_activity search_activity ) {
        data = recipesIn;
        mContext = search_activity;
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        int layoutIdForListItem = R.layout.search_raw;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem, parent, false);

        return new Search_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Search_Adapter.ViewHolder holder1 = (Search_Adapter.ViewHolder) holder ;
        current = data.get(position);

        holder1.name.setText(current.getName());
        holder1.t_name.setText(current.getTreatment_name());
        holder1.price.setText(current.getPrice());
        holder1.em.setText(current.getEm());

    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {


        CardView cardView ;
        TextView name , t_name  , price , em;
        ImageView imageView , t_image ;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = (CardView)itemView.findViewById(R.id.card);
            name = (TextView)itemView.findViewById(R.id.card_name);
            t_name = (TextView)itemView.findViewById(R.id.card_t_name);
            price = (TextView)itemView.findViewById(R.id.card_t_price);
            em = (TextView)itemView.findViewById(R.id.card_em);
            imageView = (ImageView) itemView.findViewById(R.id.card_img);
            t_image = (ImageView)itemView.findViewById(R.id.card_t_image);


        }
    }
}
