package unal.edu.co.surtilandiapp.features.client.products;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import unal.edu.co.surtilandiapp.R;
import unal.edu.co.surtilandiapp.core.data.business.ProductBussines;
import unal.edu.co.surtilandiapp.core.data.entities.Category;
import unal.edu.co.surtilandiapp.core.data.entities.Product;
import unal.edu.co.surtilandiapp.features.client.products.adapter.ProductsClientRVAdapter;
import unal.edu.co.surtilandiapp.features.shopkeeper.add.AddProductActivity;

import static android.content.ContentValues.TAG;

/**
 * Created by f on 8/10/2017.
 */

public class ProductsClientFragment extends Fragment {

    @BindView(R.id.vertical_courses_list)
    RecyclerView coursesRecyclerView;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    private List<Product> mProductStoreList;
    private ProductsClientRVAdapter mAdapter;
    private List<Category> mCategories;
    private ValueEventListener mValueEventListener;


    public ProductsClientFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_products, container, false);
        ButterKnife.bind(this, rootView);
        fab.setVisibility(View.GONE);
        loadCategories();

        return rootView;
    }

    public void loadCategories() {
        mCategories = new ArrayList<>();
        DatabaseReference categoriesQuery = FirebaseDatabase.getInstance().getReference(ProductBussines.CATEGORIES_REFERENCE);
        mValueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Category category = postSnapshot.getValue(Category.class);
                    mCategories.add(category);
                }
                procesar();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };

        categoriesQuery.addValueEventListener(mValueEventListener);

    }

    private void procesar() {
        HashMap<String, List<String>> hashMap = new HashMap<>();
        for (Category category : mCategories)
        {
            Set<String> products = category.getProducts().keySet();
            hashMap.put(category.getName(), new ArrayList<String>(products));
        }
        mostrarLista(hashMap);
    }


    private void mostrarLista(HashMap<String, List<String>> listHashMap) {
        List<CategoryProductClient> nuggetsList = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : listHashMap.entrySet()) {
            CategoryProductClient categoryProduct = new CategoryProductClient();
            categoryProduct.setTitle(entry.getKey());
            categoryProduct.setTags(entry.getValue());
            nuggetsList.add(categoryProduct);
        }
       // Setting RecyclerView
        coursesRecyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        coursesRecyclerView.setLayoutManager(llm);
        // nuggetsList is an ArrayList of Custom Objects, in this case  CategoryProductClient.class
        mAdapter = new ProductsClientRVAdapter(getActivity(), nuggetsList);
        coursesRecyclerView.setAdapter(mAdapter);
    }

    @OnClick(R.id.fab)
    public void submit(View view) {
        Intent intent = new Intent(getActivity(), AddProductActivity.class);
        startActivity(intent);
    }

}
