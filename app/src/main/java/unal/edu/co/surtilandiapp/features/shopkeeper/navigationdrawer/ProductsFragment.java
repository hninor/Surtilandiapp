package unal.edu.co.surtilandiapp.features.shopkeeper.navigationdrawer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import unal.edu.co.surtilandiapp.R;
import unal.edu.co.surtilandiapp.features.shopkeeper.add.AddProductActivity;
import unal.edu.co.surtilandiapp.features.shopkeeper.products.Nugget;
import unal.edu.co.surtilandiapp.features.shopkeeper.products.ProductsRVAdapter;

/**
 * Created by f on 8/10/2017.
 */

public class ProductsFragment extends Fragment {

    @BindView(R.id.vertical_courses_list)
    RecyclerView coursesRecyclerView;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    private ProductsRVAdapter adapter;


    public ProductsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_products, container, false);
        ButterKnife.bind(this, rootView);

        List<Nugget> nuggetsList = new ArrayList<>();

        Nugget nugget = new Nugget();
        nugget.setTitle("Enlatados");
        nugget.setTags(new ArrayList<String>(Arrays.asList("Buenos Aires", "Córdoba", "La Plata")));
        Nugget nugget2 = new Nugget();
        nugget2.setTitle("Lacteos");
        nugget2.setTags(new ArrayList<String>(Arrays.asList("Leche", "Queso", "Parmesano")));
        nuggetsList.add(nugget);
        nuggetsList.add(nugget2);
        // Setting RecyclerView
        coursesRecyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        coursesRecyclerView.setLayoutManager(llm);
        // nuggetsList is an ArrayList of Custom Objects, in this case  Nugget.class
        adapter = new ProductsRVAdapter(getActivity(), nuggetsList);
        coursesRecyclerView.setAdapter(adapter);
        return rootView;
    }

    @OnClick(R.id.fab)
    public void submit(View view) {
        Intent intent = new Intent(getActivity(), AddProductActivity.class);
        startActivity(intent);
    }

}
