package unal.edu.co.surtilandiapp.features.login;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import unal.edu.co.surtilandiapp.R;
import unal.edu.co.surtilandiapp.features.client.mainmenu.MenuClientActivity;
import unal.edu.co.surtilandiapp.features.shopkeeper.navigationdrawer.MenuShopKeeperActivity;
import unal.edu.co.surtilandiapp.features.register.ForgetActivity;
import unal.edu.co.surtilandiapp.features.register.RegisterActivity;

public class LoginActivity extends AppCompatActivity {

    //TextView txtForget = (TextView)findViewById(R.id.text_forget);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
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
