package unal.edu.co.surtilandiapp.features.register;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import unal.edu.co.surtilandiapp.R;
import unal.edu.co.surtilandiapp.features.login.LoginActivity;

/**
 * Created by f on 8/10/2017.
 */

public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    //Call when press "Regresar"
    public void redirectBack(View v)
    {
        finish();
    }

}
