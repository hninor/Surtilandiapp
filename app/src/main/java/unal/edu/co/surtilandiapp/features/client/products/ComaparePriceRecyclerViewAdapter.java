package unal.edu.co.surtilandiapp.features.client.products;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import unal.edu.co.surtilandiapp.R;


public class ComaparePriceRecyclerViewAdapter extends RecyclerView.Adapter<ComaparePriceRecyclerViewAdapter.ViewHolder> {
    private final List<ProductStorePrice> mValues;

    public class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        @BindView(R.id.tvTienda)
        TextView tvTienda;
        @BindView(R.id.tvPrecio)
        TextView tvPrecio;


        public ProductStorePrice mItem;
        public View mView;

        public ViewHolder(View view) {
            super(view);
            this.mView = view;
            ButterKnife.bind(this, view);
        }

    }

    public ComaparePriceRecyclerViewAdapter(List<ProductStorePrice> items) {
        this.mValues = items;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comparar_precios, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mItem = this.mValues.get(position);
        holder.tvTienda.setText("Tienda: " + this.mValues.get(position).getTienda());
        holder.tvPrecio.setText("Precio: " + this.mValues.get(position).getPrecio());

    }

    public int getItemCount() {
        if (this.mValues != null) {
            return this.mValues.size();
        } else {
            return 0;
        }

    }
}
