package unal.edu.co.surtilandiapp.features.shopkeeper.navigationdrawer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import unal.edu.co.surtilandiapp.R;
import unal.edu.co.surtilandiapp.features.shopkeeper.competitor.CompetitorAdapter;
import unal.edu.co.surtilandiapp.features.shopkeeper.competitor.Store;

public class CompetitorFragment extends Fragment {

    @BindView(R.id.vertical_courses_list)
    RecyclerView coursesRecyclerView;
    private CompetitorAdapter adapter;
    public CompetitorFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_competitors, container, false);
        ButterKnife.bind(this, rootView);
        List<Store> nuggetsList = new ArrayList<>();

        Store nugget = new Store();
        nugget.setName("Pacho");
        nugget.setRating(4.0);
        Store nugget2 = new Store();
        nugget2.setName("Robando");
        nugget.setRating(3.0);
        nuggetsList.add(nugget);
        nuggetsList.add(nugget2);
        // Setting RecyclerView
        coursesRecyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        coursesRecyclerView.setLayoutManager(llm);
        // nuggetsList is an ArrayList of Custom Objects, in this case  Store.class
        adapter = new CompetitorAdapter();
        adapter.setData(nuggetsList);
        coursesRecyclerView.setAdapter(adapter);
        return rootView;
    }

}