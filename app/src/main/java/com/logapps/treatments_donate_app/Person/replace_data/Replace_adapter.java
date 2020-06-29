package com.logapps.treatments_donate_app.Person.replace_data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.logapps.treatments_donate_app.R;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class Replace_adapter extends RecyclerView.Adapter<Replace_adapter.ViewHolder> {

    private Replacements_user mContext;
    public List<Replace_class> data = Collections.emptyList();
    Replace_class current;
    public String TAG = "taaaaaaaag";
    private UserClick lOnClickListener;


    public Replace_adapter(UserClick listener) {
        lOnClickListener = listener;
    }

    public void setUsersData(List<Replace_class> recipesIn, Replacements_user replacements_user ) {
        data = recipesIn;
        mContext = replacements_user;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        int layoutIdForListItem = R.layout.replacement_raw;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final ViewHolder holder1 = (ViewHolder) holder ;
        current = data.get(position);

        holder1.name.setText(current.getT_name());
        holder1.t_name.setText(current.getT_details());
        holder1.prize.setText(current.getPrize());
        Picasso.with(mContext.getContext()).load(current.getT_image()).into(holder.t_image);

    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView ;
        TextView name , t_name , prize;
        ImageView imageView , t_image ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = (CardView)itemView.findViewById(R.id.card);
            name = (TextView)itemView.findViewById(R.id.card_name);
            t_name = (TextView)itemView.findViewById(R.id.card_t_name);
            prize = (TextView)itemView.findViewById(R.id.card_t_prize);
            imageView = (ImageView) itemView.findViewById(R.id.card_img);
            t_image = (ImageView)itemView.findViewById(R.id.card_t_image);
        }
    }
}
