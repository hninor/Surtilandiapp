package unal.edu.co.surtilandiapp.features.store;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.ButterKnife;
import butterknife.OnClick;
import unal.edu.co.surtilandiapp.R;

public class MenuStoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_store);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @OnClick(R.id.btInformacion)
    public void goToInformacion() {
        // TODO submit data to server...
    }
    @OnClick(R.id.btUbicacion)
    public void goToUbicacion() {
        // TODO submit data to server...
    }
}
