package cl.vicentepc.miappprueba3.login;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.firebase.ui.auth.AuthUI;

import java.util.Arrays;

import cl.vicentepc.miappprueba3.R;
import cl.vicentepc.miappprueba3.views.main.MainActivity;

public class LoginActivity extends AppCompatActivity implements UserCallback{

    public static final int RC_SIGN_IN = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        new UserValidation(this).validateUser();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(RC_SIGN_IN == requestCode){
            if(RESULT_OK == resultCode){
                userLogged();
            } else {
                userSignUp();
            }
        }
    }

    public void userSignUp() {
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(Arrays.asList(
                                new AuthUI.IdpConfig.EmailBuilder().build(),
                                new AuthUI.IdpConfig.GoogleBuilder().build()))
                                //new AuthUI.IdpConfig.FacebookBuilder().build(),
                                //new AuthUI.IdpConfig.TwitterBuilder().build()))
                        .setTheme(R.style.LoginTheme)
                        .setLogo(R.mipmap.logo)
                        .build(),
                RC_SIGN_IN);
    }

    public void userLogged() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
