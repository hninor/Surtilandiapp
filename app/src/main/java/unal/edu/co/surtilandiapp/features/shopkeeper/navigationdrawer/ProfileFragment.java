package unal.edu.co.surtilandiapp.features.shopkeeper.navigationdrawer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import unal.edu.co.surtilandiapp.R;

/**
 * Created by f on 8/10/2017.
 */

public class ProfileFragment extends Fragment implements View.OnClickListener {
    View rootView;
    Button cancelButton;
    public ProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.v("JORGE", "por aca pase");
        rootView = inflater.inflate(R.layout.fragment_table, container, false);
        cancelButton = (Button) rootView.findViewById(R.id.perfil_btn_cancelar);
        cancelButton.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View view) {
        Log.v("JORGE", "Acabo de hacer click");
    }
}