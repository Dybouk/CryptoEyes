package com.CryptoEyes.coin.market;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.CryptoEyes.coin.market.retrofit.Datum;


import java.util.List;

public class CryptoListAdapter extends RecyclerView.Adapter<CryptoListAdapter.ViewHolder> {

    private Context context;
    private List<Datum> mData;
    private ItemClickListener mClickListener;

    // ajout context pour picasso
    CryptoListAdapter(List<Datum> data) {
        this.context = context;
        this.mData = data;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.crypto_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    // APP crash avec picasso ou picasso, proguard false
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


       // Picasso.with(context)
         //       .load("https://res.cloudinary.com/dxi90ksom/image/upload/")
           //    .into(holder.imageView);


        Datum datum = mData.get(position);


        TextView name = holder.name;
        name.setText(datum.getName() + " (" + datum.getSymbol() + ")");

        TextView price = holder.price;
        price.setText("Price: €" + String.format("%,f", datum.getQuote().getUSD().getPrice()));

        TextView marketCap = holder.marketCap;
        marketCap.setText("Market Cap: $" + String.format("%,d", Math.round(datum.getQuote().getUSD().getMarketCap())));

        TextView volume24h = holder.volume24h;
        volume24h.setText("Volume/24h: $" + String.format("%,d", Math.round(datum.getQuote().getUSD().getVolume24h())));

        TextView textView1h = holder.textView1h;
        textView1h.setText(String.format("1h: %.2f", datum.getQuote().getUSD().getPercentChange1h()) + "%");

        TextView textView24h = holder.textView24h;
        textView24h.setText(String.format("24h: %.2f", datum.getQuote().getUSD().getPercentChange24h()) + "%");

        TextView textView7d = holder.textView7d;
        textView7d.setText(String.format("7d: %.2f", datum.getQuote().getUSD().getPercentChange7d()) + "%");

    }

    // return from List
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // ref storé dasn l'ordre

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        ImageView imageView;
        TextView name;
        TextView price;
        TextView marketCap;
        TextView volume24h;
        TextView textView1h;
        TextView textView24h;
        TextView textView7d;

        ViewHolder(View itemView) {
            // acces depuis viewholder
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);

            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            marketCap = itemView.findViewById(R.id.marketCap);
            volume24h = itemView.findViewById(R.id.volume24h);
            textView1h = itemView.findViewById(R.id.textView1h);
            textView24h = itemView.findViewById(R.id.textView24h);
            textView7d = itemView.findViewById(R.id.textView7d);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // position
    Datum getItem(int id) {
        return mData.get(id);
    }

    // click event
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // réponse au click event
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}