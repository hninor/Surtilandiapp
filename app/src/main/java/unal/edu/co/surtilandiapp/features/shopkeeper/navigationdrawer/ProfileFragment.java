package unal.edu.co.surtilandiapp.features.shopkeeper.navigationdrawer;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import unal.edu.co.surtilandiapp.R;
import unal.edu.co.surtilandiapp.core.data.business.ProductBussines;
import unal.edu.co.surtilandiapp.core.data.entities.UserProfile;

/**
 * Created by f on 8/10/2017.
 */

public class ProfileFragment extends Fragment implements View.OnClickListener {
    View rootView;
    Button cancelButton;
    Button saveButton;

    @BindView(R.id.perfil_nombre)
    EditText perfil_nombre;


    @BindView(R.id.perfil_email)
    EditText perfil_email;

    @BindView(R.id.perfil_celular)
    EditText perfil_celular;

    public ProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_table, container, false);
        cancelButton = (Button) rootView.findViewById(R.id.perfil_btn_cancelar);
        cancelButton.setOnClickListener(this);

        saveButton = (Button)rootView.findViewById(R.id.perfil_btn_guardar);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = perfil_nombre.getText().toString().trim();
                String phone = perfil_celular.getText().toString().trim();
                String email = perfil_email.getText().toString().trim();
                 UserProfile profile = new UserProfile(email,phone,name);
                DatabaseReference storeQuery = FirebaseDatabase.getInstance().getReference(ProductBussines.USER_PERFIL);
                storeQuery.child(name).setValue(profile);
                mostrarMensaje(getString(R.string.tienda_guardada));

            }
        });

        return rootView;
    }

    public void mostrarMensaje(String mensaje) {
        Snackbar.make(perfil_nombre, mensaje, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void onClick(View v) {

    }
}