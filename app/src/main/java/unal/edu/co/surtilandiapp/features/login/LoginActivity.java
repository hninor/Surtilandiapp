package unal.edu.co.surtilandiapp.features.login;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import unal.edu.co.surtilandiapp.R;
import unal.edu.co.surtilandiapp.features.client.mainmenu.MenuClientActivity;
import unal.edu.co.surtilandiapp.features.shopkeeper.navigationdrawer.MenuShopKeeperActivity;
import unal.edu.co.surtilandiapp.features.register.ForgetActivity;
import unal.edu.co.surtilandiapp.features.register.RegisterActivity;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "FirebaseLogin";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    //TextView txtForget = (TextView)findViewById(R.id.text_forget);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
        //display the home_screen during 3 seconds,
        new CountDownTimer(3000,1000){
            @Override
            public void onTick(long millisUntilFinished){}

            @Override
            public void onFinish(){
                //set the new Content of your activity
                LoginActivity.this.setContentView(R.layout.activity_login);
            }
        }.start();

    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


    //Called when the user clicks the Register button
    public void redirectRegister(View view)
    {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }


    //Called when the user clicks the Forget Password Text View
    public void redirectForget(View view)
    {
        Intent intent = new Intent(this, ForgetActivity.class);
        startActivity(intent);
    }

    public void redirectMain(View view)
    {
        //Intent intent = new Intent(this, MenuShopKeeperActivity.class);
        Intent intent = new Intent(this, MenuClientActivity.class);
        startActivity(intent);
    }
}
