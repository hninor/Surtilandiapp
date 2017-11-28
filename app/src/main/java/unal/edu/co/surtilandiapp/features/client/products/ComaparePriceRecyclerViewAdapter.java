package unal.edu.co.surtilandiapp.features.client.products;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;
import unal.edu.co.surtilandiapp.R;
import unal.edu.co.surtilandiapp.core.data.entities.ProductStore;


public class ComaparePriceRecyclerViewAdapter extends RecyclerView.Adapter<ComaparePriceRecyclerViewAdapter.ViewHolder> {
    private final List<ProductStore> mValues;

    public class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
       // @BindView(R.id.tvDepartamento)
        public TextView tvDepartamento;


        public ProductStore mItem;
        public View mView;

        public ViewHolder(View view) {
            super(view);
            this.mView = view;
            ButterKnife.bind(this, view);
        }

    }

    public ComaparePriceRecyclerViewAdapter(List<ProductStore> items) {
        this.mValues = items;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comparar_precios, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mItem = this.mValues.get(position);
        //holder.tvDepartamento.setText((this.mValues.get(position)).getDepartamento());

    }

    public int getItemCount() {
        if (this.mValues != null) {
            return this.mValues.size();
        } else {
            return 0;
        }

    }
}
