package sg.edu.np.mad.practical6dbhandler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signup extends AppCompatActivity {
    // Actually all these code does not work but at least got try
    //__________________________________________
    String title = "Main Activity 2";
    /*
    private String GLOBAL_PREF = "MyPrefs";
    private String MY_USERNAME = "MyUserName";
    private String MY_PASSWORD = "MyPassword";
    SharedPreferences sharedPreferences;
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    MyDBHandler myDBHandler = new MyDBHandler(this, null, null, 1);
    @Override
    protected void onStart(){
        super.onStart();
        Log.i(title,"Starting Acct Creation");

        EditText etUsername = findViewById(R.id.editTextText3);
        EditText etPassword = findViewById(R.id.editTextText4);
        Button createButton = findViewById(R.id.button2);
        Button cancelButton = findViewById(R.id.button3);

        createButton.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View view){
                /*
                sharedPreferences = getSharedPreferences(GLOBAL_PREF,MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(MY_USERNAME, etUsername.getText().toString());
                editor.putString(MY_PASSWORD, etPassword.getText().toString());
                editor.commit();

                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
                */

                String dbData = etUsername.getText().toString();
                if (dbData == null) {
                    String dbUserName = etUsername.getText().toString();
                    String dbPassword = etPassword.getText().toString();
                    UserData dbUserData = new UserData(dbUserName, dbPassword);
                    //myDBHandler.addUser(dbUserData);
                    Intent intent = new Intent(Signup.this, MainActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(Signup.this, "Username already exists!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Signup.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}