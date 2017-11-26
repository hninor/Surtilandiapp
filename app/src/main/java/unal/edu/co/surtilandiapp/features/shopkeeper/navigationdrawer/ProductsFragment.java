package unal.edu.co.surtilandiapp.features.shopkeeper.navigationdrawer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import unal.edu.co.surtilandiapp.R;
import unal.edu.co.surtilandiapp.core.data.business.ProductBussines;
import unal.edu.co.surtilandiapp.core.data.entities.ProductStore;
import unal.edu.co.surtilandiapp.features.shopkeeper.add.AddProductActivity;
import unal.edu.co.surtilandiapp.features.shopkeeper.products.CategoryProduct;
import unal.edu.co.surtilandiapp.features.shopkeeper.products.ProductsRVAdapter;

import static android.content.ContentValues.TAG;

/**
 * Created by f on 8/10/2017.
 */

public class ProductsFragment extends Fragment {

    @BindView(R.id.vertical_courses_list)
    RecyclerView coursesRecyclerView;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    private String mStore;
    private List<ProductStore> mProductStoreList;
    private ProductsRVAdapter adapter;


    public ProductsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_products, container, false);
        ButterKnife.bind(this, rootView);
        mStore = "one";
        consultarProductos();

        return rootView;
    }

    private void consultarProductos() {
        mProductStoreList = new ArrayList<>();
        DatabaseReference userQuery = FirebaseDatabase.getInstance().getReference(ProductBussines.PRODUCTS_STORE_REFERENCE);
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    mProductStoreList.add(postSnapshot.getValue(ProductStore.class));

                }
                mostrarLista();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        userQuery.child(mStore).addListenerForSingleValueEvent(postListener);
    }

    private void mostrarLista() {

        List<CategoryProduct> nuggetsList = new ArrayList<>();
        CategoryProduct categoryProduct = new CategoryProduct();
        categoryProduct.setTitle("Enlatados");
        categoryProduct.setTags(mProductStoreList);
        CategoryProduct categoryProduct2 = new CategoryProduct();
        categoryProduct2.setTitle("Lacteos");
        categoryProduct2.setTags(mProductStoreList);
        nuggetsList.add(categoryProduct);
        nuggetsList.add(categoryProduct2);
        // Setting RecyclerView
        coursesRecyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        coursesRecyclerView.setLayoutManager(llm);
        // nuggetsList is an ArrayList of Custom Objects, in this case  CategoryProduct.class
        adapter = new ProductsRVAdapter(getActivity(), nuggetsList);
        coursesRecyclerView.setAdapter(adapter);
    }

    @OnClick(R.id.fab)
    public void submit(View view) {
        Intent intent = new Intent(getActivity(), AddProductActivity.class);
        startActivity(intent);
    }

}
