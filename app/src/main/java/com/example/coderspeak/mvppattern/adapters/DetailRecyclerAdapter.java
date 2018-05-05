package com.example.coderspeak.mvppattern.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coderspeak.mvppattern.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmList;

public class DetailRecyclerAdapter extends RecyclerView.Adapter<DetailRecyclerAdapter.DetailViewHolder> {
    private RealmList<String> shoppingListIngredients;
    private Realm realm;
    private boolean isArchieved;

    public DetailRecyclerAdapter(RealmList<String> shoppingListIngredients, Realm realm, boolean isArchieved) {
        this.shoppingListIngredients = shoppingListIngredients;
        this.realm = realm;
        this.isArchieved = isArchieved;
    }

    class DetailViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.detailListTitle)
        TextView detailTitle;

        @BindView(R.id.cancelItem)
        Button cancelItem;

        public DetailViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @NonNull
    @Override
    public DetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_row, parent, false);

        return new DetailViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailViewHolder holder, final int position) {

        if (!isArchieved) {
            holder.cancelItem.setClickable(true);
            holder.detailTitle.setText(shoppingListIngredients.get(position));
            holder.cancelItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    realm.beginTransaction();
                    shoppingListIngredients.remove(position);
                    notifyDataSetChanged();
                    realm.commitTransaction();
                }
            });
        } else {
            holder.cancelItem.setVisibility(View.GONE);
            holder.detailTitle.setText(shoppingListIngredients.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return shoppingListIngredients.size();
    }
}
