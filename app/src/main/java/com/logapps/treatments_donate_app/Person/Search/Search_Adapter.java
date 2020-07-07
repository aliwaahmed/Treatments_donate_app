package com.logapps.treatments_donate_app.Person.Search;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.logapps.treatments_donate_app.Person.needs_data.Ineed;
import com.logapps.treatments_donate_app.Person.needs_data.Ineed_Adapter;
import com.logapps.treatments_donate_app.Person.needs_data.Ineed_class;
import com.logapps.treatments_donate_app.Person.replace_data.UserClick;
import com.logapps.treatments_donate_app.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class Search_Adapter extends RecyclerView.Adapter<Search_Adapter.ViewHolder>
        implements Filterable {

    private Search_activity mContext;
    public List<Search_class> data = Collections.emptyList();
    public List<Search_class> data_temp = Collections.emptyList();

    Search_class current;
    public String TAG = "taaaaaaaag";
    private UserClick lOnClickListener;
    private List<Search_class>filteredData = null;
    public Search_Adapter(Search_activity listener){
        lOnClickListener = listener;
    }


    public void setUsersData(List<Search_class> recipesIn, Search_activity search_activity ) {
        data = recipesIn;
        data_temp=recipesIn;
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
        Picasso.with(mContext.getApplicationContext()).load(current.getImage()).into(holder.t_image);

    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public Filter getFilter() {
        return new ItemFilter();
    }

 class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint == null || constraint.length() == 0) {
                results.values = data_temp;
                results.count = data_temp.size();
            }
            else {
                String filterString = constraint.toString().toLowerCase();


                final List<Search_class> list = data;

                int count = list.size();
                final ArrayList<Search_class> nlist = new ArrayList<Search_class>(count);

                String filterableString;

                for (int i = 0; i < count; i++) {
                    filterableString = list.get(i).getEm();
                    if (filterableString.toLowerCase().contains(filterString)) {
                        nlist.add(list.get(i));
                        Log.e("a", list.get(i).getEm().toString());
                    }
                }


                results.values = nlist;
                results.count = nlist.size();
            }
            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            filteredData = (ArrayList<Search_class>) results.values;

            data=filteredData;
            notifyDataSetChanged();
            if(filteredData.size()==0)
            {
                data=data_temp;
                notifyDataSetChanged();
            }

        }

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
            t_image = (ImageView)itemView.findViewById(R.id.search_img);


        }
    }
}
