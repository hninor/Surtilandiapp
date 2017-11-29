package unal.edu.co.surtilandiapp.features.shopkeeper.add;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import unal.edu.co.surtilandiapp.R;
import unal.edu.co.surtilandiapp.core.data.business.ProductBussines;
import unal.edu.co.surtilandiapp.core.data.entities.Category;
import unal.edu.co.surtilandiapp.core.data.entities.ProductStore;
import unal.edu.co.surtilandiapp.core.util.MyModulePreference;

public class AddProductActivity extends AppCompatActivity {

    private static final String TAG = "Product Task";
    @BindView(R.id.spCategoria)
    Spinner spCategoria;
    @BindView(R.id.spProducto)
    Spinner spProducto;
    @BindView(R.id.etPrecio)
    EditText etPrecio;
    @BindView(R.id.etDescripcionProducto)
    EditText etDescripcionProducto;
    @BindView(R.id.switchDisponible)
    Switch switchDisponible;

    private ValueEventListener mValueEventListener;
    private List<Category> mCategories;
    private String mStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        traerExtra();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String categoria = spCategoria.getSelectedItem().toString();
                String producto = spProducto.getSelectedItem().toString();
                String precio = etPrecio.getText().toString().trim();
                String descripcionProducto = etDescripcionProducto.getText().toString().trim();
                boolean disponible = switchDisponible.isChecked();

                ProductStore productStore = new ProductStore(disponible, precio, producto, categoria, new Date().getTime());
                DatabaseReference productsStoreQuery = FirebaseDatabase.getInstance().getReference(ProductBussines.PRODUCTS_STORE_REFERENCE);
                productsStoreQuery.child(mStore).child(producto).setValue(productStore);
                Snackbar.make(view, "Product saved", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        loadCategories();

    }

    private void traerExtra() {
        final MyModulePreference myModulePreference = new MyModulePreference(getApplicationContext());
        mStore = myModulePreference.getString(MyModulePreference.STORE, null);
    }

    private void loadSpinner() {
        final List<String> categorias = new ArrayList<>();
        for (Category category : mCategories) {
            categorias.add(category.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, categorias);
        spCategoria.setAdapter(adapter);


        spCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Category category = mCategories.get(i);
                Set<String> products = category.getProducts().keySet();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddProductActivity.this, android.R.layout.simple_spinner_dropdown_item, new ArrayList<String>(products));
                spProducto.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
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
                loadSpinner();
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

}
