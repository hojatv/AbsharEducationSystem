package ir.abshareducaion.abshareducationsystem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by hovaheb on 9/6/2016.
 */
public class LoginActivity extends AppCompatActivity {
    public static final String USER_NAME = "userName";
    public static final String PASSWORD = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onLoginClickHandler(View view) {
        if (view.getId() == R.id.btnLoggin) {
            Intent intent = new Intent(this, MainActivity.class);
            EditText userName = (EditText) findViewById(R.id.tfUserName);
            EditText password = (EditText) findViewById(R.id.tfPassword);
            if (userName.getText().toString().equals("kermit") && password.getText().toString().equals("kermit")) {
                intent.putExtra(USER_NAME, userName.getText().toString());
                intent.putExtra(PASSWORD, password.getText().toString());
                startActivity(intent);
            }else{
                Toast.makeText(this,"Please enter your login credentials",Toast.LENGTH_LONG).show();
            }
        }

    }
}
