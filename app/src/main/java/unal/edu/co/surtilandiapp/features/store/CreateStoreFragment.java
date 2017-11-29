package unal.edu.co.surtilandiapp.features.store;

import android.app.TimePickerDialog;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import unal.edu.co.surtilandiapp.R;
import unal.edu.co.surtilandiapp.core.data.business.ProductBussines;
import unal.edu.co.surtilandiapp.core.data.entities.LocationStore;
import unal.edu.co.surtilandiapp.core.data.entities.Store;
import unal.edu.co.surtilandiapp.core.util.MyModulePreference;
import unal.edu.co.surtilandiapp.features.shopkeeper.navigationdrawer.MenuShopKeeperActivity;


public class CreateStoreFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @BindView(R.id.etHorarioApertura)
    EditText etHorarioApertura;
    @BindView(R.id.etHorarioCierre)
    EditText etHorarioCierre;
    @BindView(R.id.etNombre)
    EditText etNombre;
    @BindView(R.id.etDescripcion)
    EditText etDescripcion;
    @BindView(R.id.etTelefono)
    EditText etTelefono;
    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.tvLatLog)
    TextView tvLatLog;
    @BindView(R.id.etDireccion)
    EditText etDireccion;

    private MenuShopKeeperActivity menuShopKeeperActivity;
    private Location mLocation;
    private String mStore;
    private String mUser;



    public CreateStoreFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateStoreFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateStoreFragment newInstance(String param1, String param2) {
        CreateStoreFragment fragment = new CreateStoreFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_store, container, false);
        ButterKnife.bind(this, view);
        traerExtra();
        if (mStore != null) {
            etNombre.setText(mStore);
            etNombre.setEnabled(false);
        }
        menuShopKeeperActivity = (MenuShopKeeperActivity) getActivity();
        mLocation = menuShopKeeperActivity.getmLastLocation();
        tvLatLog.setText("Longitud: " + mLocation.getLongitude() + ", Latitud: " + mLocation.getLatitude() );
        etHorarioApertura.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        etHorarioApertura.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        etHorarioCierre.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        etHorarioCierre.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = etNombre.getText().toString().trim();
                String description = etDescripcion.getText().toString().trim();
                String open = etHorarioApertura.getText().toString().trim();
                String close = etHorarioCierre.getText().toString().trim();
                String phone = etTelefono.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String direccion = etDireccion.getText().toString().trim();

                LocationStore location = new LocationStore();
                location.setLat(mLocation.getLatitude());
                location.setLng(mLocation.getLongitude());
                if (name.isEmpty()) {
                    etNombre.requestFocus();
                    mostrarMensaje(getString(R.string.nombre_requerido));
                } else if (direccion.isEmpty()){
                    etDireccion.requestFocus();
                    mostrarMensaje(getString(R.string.direccion_requerido));
                } else {
                    Store store = new Store(name, description, open, close, phone, email, location);
                    DatabaseReference storeQuery = FirebaseDatabase.getInstance().getReference(ProductBussines.STORES_REFERENCE);
                    storeQuery.child(name).setValue(store);
                    if (mStore == null) {
                        DatabaseReference userQuery = FirebaseDatabase.getInstance().getReference(ProductBussines.USERS_REFERENCE);
                        userQuery.child(mUser).child("tienda").setValue(name);
                    }


                    mostrarMensaje(getString(R.string.tienda_guardada));

                }

            }
        });


        return view;
    }

    private void traerExtra() {
        final MyModulePreference myModulePreference = new MyModulePreference(getActivity());
        mStore = myModulePreference.getString(MyModulePreference.STORE, null);
        mUser = myModulePreference.getString(MyModulePreference.USER, null);
    }

    public void mostrarMensaje(String mensaje) {
        Snackbar.make(etDescripcion, mensaje, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }




}
