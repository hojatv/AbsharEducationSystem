package ir.abshareducaion.abshareducationsystem;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by hovaheb on 9/6/2016.
 */
public class LoginActivity extends AppCompatActivity {
    public static final String USER_NAME = "userName";
    public static final String PASSWORD = "password";

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.login_activity);
    }
    public void onLoginClickHandler(View view){
        if(view.getId() == R.id.btnLoggin){
            Intent intent = new Intent(this,MainActivity.class);
            EditText userName = (EditText) findViewById(R.id.tfUserName);
            EditText password = (EditText) findViewById(R.id.tfPassword);
            intent.putExtra(USER_NAME,userName.getText());
            intent.putExtra(PASSWORD,password.getText());
            startActivity(intent);
        }

    }
}
