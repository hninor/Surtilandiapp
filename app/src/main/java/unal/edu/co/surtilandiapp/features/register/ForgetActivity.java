package unal.edu.co.surtilandiapp.features.register;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import unal.edu.co.surtilandiapp.R;

/**
 * Created by f on 8/10/2017.
 */

public class ForgetActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);

    }

    ////Call when press "<< Regresar"
    public void redirectBack(View view)
    {
        finish();
    }

}
