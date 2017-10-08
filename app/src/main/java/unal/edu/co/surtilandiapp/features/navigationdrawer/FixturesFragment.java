package unal.edu.co.surtilandiapp.features.navigationdrawer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import unal.edu.co.surtilandiapp.R;

/**
 * Created by f on 8/10/2017.
 */

public class FixturesFragment extends Fragment {


    public FixturesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_fixtures, container, false);

        return rootView;
    }

}
