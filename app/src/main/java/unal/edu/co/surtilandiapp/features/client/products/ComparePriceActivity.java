package unal.edu.co.surtilandiapp.features.client.products;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import unal.edu.co.surtilandiapp.R;
import unal.edu.co.surtilandiapp.core.data.business.ProductBussines;

import static android.content.ContentValues.TAG;
import static unal.edu.co.surtilandiapp.features.client.products.ProductsClientFragment.PRODUCTO;


public class ComparePriceActivity extends AppCompatActivity {


    @BindView(R.id.rvProductos)
    RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ComaparePriceRecyclerViewAdapter mAdapter;
    private String mProducto;
    private List<ProductStorePrice> mProductStorePriceList;
    private ValueEventListener mValueEventListener;
    private List<String> mStores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_price);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
        traerExtra();

        consultarTiendas();


    }

    private void consultarTiendas() {
        mStores = new ArrayList<>();
        DatabaseReference userQuery = FirebaseDatabase.getInstance().getReference(ProductBussines.STORES_REFERENCE);
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    mStores.add(postSnapshot.getKey());
                }
                consultarPorTiendas();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        userQuery.addListenerForSingleValueEvent(valueEventListener);
    }

    private void consultarPorTiendas() {
        mProductStorePriceList = new ArrayList<>();
        for (String store : mStores) {
            consultarProductos(store);
        }


    }

    private void traerExtra() {
        mProducto = getIntent().getStringExtra(PRODUCTO);
    }


    private void consultarProductos(final String store) {

        DatabaseReference userQuery = FirebaseDatabase.getInstance().getReference(ProductBussines.PRODUCTS_STORE_REFERENCE);
        mValueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ProductStorePrice productStore = dataSnapshot.getValue(ProductStorePrice.class);

                if (productStore != null) {
                    productStore.setTienda(store);
                    mProductStorePriceList.add(productStore);
                }
                // use this setting to improve performance if you know that changes
                // in content do not change the layout size of the RecyclerView
                mRecyclerView.setHasFixedSize(true);

                // use a linear layout manager
                //mLayoutManager = new LinearLayoutManager(ComparePriceActivity.this, LinearLayoutManager.HORIZONTAL, false);
                mLayoutManager = new LinearLayoutManager(ComparePriceActivity.this);
                mRecyclerView.setLayoutManager(mLayoutManager);

                // specify an adapter (see also next example)
                mAdapter = new ComaparePriceRecyclerViewAdapter(mProductStorePriceList);
                mRecyclerView.setAdapter(mAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        userQuery.child(store).child(mProducto).addListenerForSingleValueEvent(mValueEventListener);
    }

    private void procesar() {
    }
}
