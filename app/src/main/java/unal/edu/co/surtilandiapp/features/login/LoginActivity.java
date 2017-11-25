package unal.edu.co.surtilandiapp.features.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import unal.edu.co.surtilandiapp.R;
import unal.edu.co.surtilandiapp.core.data.business.ProductBussines;
import unal.edu.co.surtilandiapp.features.client.mainmenu.MenuClientActivity;
import unal.edu.co.surtilandiapp.features.register.ForgetActivity;
import unal.edu.co.surtilandiapp.features.register.RegisterActivity;
import unal.edu.co.surtilandiapp.features.shopkeeper.navigationdrawer.MenuShopKeeperActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.login_button)
    Button login_button;
    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.etPassword)
    EditText etPassword;

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
        new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                //set the new Content of your activity
                LoginActivity.this.setContentView(R.layout.activity_login);
                ButterKnife.bind(LoginActivity.this);
                login_button.setOnClickListener(LoginActivity.this);
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
    public void redirectRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }


    //Called when the user clicks the Forget Password Text View
    public void redirectForget(View view) {
        Intent intent = new Intent(this, ForgetActivity.class);
        startActivity(intent);
    }

    public void redirectMain(String rol) {
        Intent intent;
        if (rol.equals("Tendero")) {
            intent = new Intent(this, MenuShopKeeperActivity.class);
        } else {
            intent = new Intent(this, MenuClientActivity.class);
        }

        startActivity(intent);
    }

    public void signInWithEmailAndPassword(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        String email = user.getEmail().split("@")[0];
        DatabaseReference userQuery = FirebaseDatabase.getInstance().getReference(ProductBussines.USERS_REFERENCE);
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String rol = dataSnapshot.getValue(String.class);
                redirectMain(rol);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        userQuery.child(email).child("rol").addListenerForSingleValueEvent(postListener);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.login_button:
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                if (email.isEmpty()) {
                    etEmail.requestFocus();
                    etEmail.setError(getString(R.string.requerido));
                } else if (password.isEmpty()) {
                    etPassword.requestFocus();
                    etPassword.setError(getString(R.string.requerido));
                } else {
                    signInWithEmailAndPassword(email, password);
                }
                break;
            default:
                break;
        }

    }
}
