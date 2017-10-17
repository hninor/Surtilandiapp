package unal.edu.co.surtilandiapp.features.shopkeeper.competitor;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import unal.edu.co.surtilandiapp.R;

/**
 * Created by USER on 16/10/2017.
 */

public class CompetitorAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Store> mDataList;
    private int mRowIndex = -1;

    public CompetitorAdapter() {
    }

    public void setData(List<Store> data) {
        if (mDataList != data) {
            mDataList = data;
            notifyDataSetChanged();
        }
    }

    public void setRowIndex(int index) {
        mRowIndex = index;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvNombre)
        TextView tvNombre;
        @BindView(R.id.tvCalificacion)
        TextView tvCalificacion;


        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.competitor_item, parent, false);
        ItemViewHolder holder = new ItemViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder rawHolder, int position) {
        ItemViewHolder holder = (ItemViewHolder) rawHolder;
        holder.tvNombre.setText(mDataList.get(position).getName());
        holder.tvCalificacion.setText(String.valueOf(mDataList.get(position).getRating()));
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }
}
