package com.example.coderspeak.mvppattern.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.coderspeak.mvppattern.R;
import com.example.coderspeak.mvppattern.activities.detail.DetailActivity;
import com.example.coderspeak.mvppattern.db.data.ShoppingList;
import com.example.coderspeak.mvppattern.fragments.Archieved.ArchievedMVP;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class ArchievedRecyclerAdapter extends RecyclerView.Adapter<ArchievedRecyclerAdapter.FragmentViewHolder> {
    private List<ShoppingList> shoppingList;
    private Realm realm;
    private Context c;

    private ArchievedMVP.Presenter presenter;
    boolean isArchieved;

    public ArchievedRecyclerAdapter(Context c, List<ShoppingList> shoppingList, Realm realm, boolean isArchieved, ArchievedMVP.Presenter presenter) {
        this.shoppingList = shoppingList;
        this.realm = realm;
        this.c = c;
        this.isArchieved = isArchieved;
        this.presenter = presenter;
    }

    class FragmentViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.currentTitle)
        TextView currentTitle;

        @BindView(R.id.isArchievedBtn)
        CheckBox isArchievedBtn;

        @BindView(R.id.goToDetails)
        TextView goToDetails;

        private FragmentViewHolder(View itemView) {
            super(itemView);
            this.setIsRecyclable(false);
            ButterKnife.bind(this, itemView);
        }
    }

    @NonNull
    @Override
    public FragmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_fragment_row, parent, false);
        return new FragmentViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final FragmentViewHolder holder, final int position) {

        holder.currentTitle.setText(shoppingList.get(position).getTitle());

        if (shoppingList.get(position).isArchieved()) {
            holder.isArchievedBtn.setChecked(true);
        } else {
            holder.isArchievedBtn.setChecked(false);
        }

        holder.goToDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(c, DetailActivity.class);
                intent.putExtra("id", shoppingList.get(position).getId());
                intent.putExtra("isArchieved", isArchieved);
                c.startActivity(intent);
            }
        });

        holder.isArchievedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.setAdapterData(holder.isArchievedBtn.isChecked(), shoppingList.get(position).getId());
                holder.isArchievedBtn.setChecked(holder.isArchievedBtn.isChecked());
            }
        });
    }

    @Override
    public int getItemCount() {
        return shoppingList.size();
    }
}
