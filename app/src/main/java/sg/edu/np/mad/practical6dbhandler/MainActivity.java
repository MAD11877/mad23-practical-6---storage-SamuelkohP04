package sg.edu.np.mad.practical6dbhandler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    final String TITLE = "List Activity";
    User user;
    Button followButton;
    Button messageButton;
    TextView name;
    TextView desc;
    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar
        //________________________________________________________
        makeToolbar();

        //Creating user object in the profile page
        //_________________________________________________________
        followButton = findViewById(R.id.btn);
        messageButton = findViewById(R.id.btn2);
        name = findViewById(R.id.textView);
        desc = findViewById(R.id.textView5);

        Intent receiving = getIntent();
        if (receiving.hasExtra("Name") && receiving.hasExtra("Desc")) {
            String n = receiving.getStringExtra("Name");
            String d = receiving.getStringExtra("Desc");
            int i = receiving.getIntExtra("Id",0);
            boolean f = receiving.getBooleanExtra("Followed",false);
            name.setText(n);
            desc.setText(d);
            user = new User(n,d,i,f);
        }

        if (user.isFollowed()) {
            followButton.setText(getString(R.string.unfollow));
        } else {
            followButton.setText(getString(R.string.follow));
        }


        //Follow button will toggle the user's follow status when pressed
        //________________________________________________________________
        followButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setFollowed(!user.isFollowed());

                Log.v(TITLE, String.valueOf(user.isFollowed()));
                if (user.isFollowed()) {
                    followButton.setText(getString(R.string.unfollow));
                    toastMessage("Followed");
                } else {
                    followButton.setText(getString(R.string.follow));
                    toastMessage("Unfollowed");
                }
            }
        });

        messageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MessageGroup.class);
                startActivity(intent);
            }
        });

        dbHandler = new MyDBHandler(this, null, null, 1);
        dbHandler.updateUser(user);
        logAllUsers();


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v(TITLE, "On Start!");
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Remove the duplicate view assignments from here
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v(TITLE, "On Stop!");
        dbHandler = new MyDBHandler(this, null, null, 1);
        dbHandler.updateUser(user);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(TITLE, "On Pause!");
        dbHandler = new MyDBHandler(this, null, null, 1);
        dbHandler.updateUser(user);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(TITLE, "On Destroy");
        dbHandler = new MyDBHandler(this, null, null, 1);
        dbHandler.updateUser(user);
    }

    public void logAllUsers() {
        ArrayList<User> users = dbHandler.getUsers();

        for (User user : users) {
            Log.v("UserData", "Name: " + user.getName());
            Log.v("UserData", "Description: " + user.getDescription());
            Log.v("UserData", "ID: " + user.getId());
            Log.v("UserData", "Followed: " + user.isFollowed());
            Log.v("UserData", "------------------------");
        }
    }
    // Toast Message method
    //_________________________________________________________________
    private void toastMessage(String text){
        Toast.makeText (getApplicationContext (),
                text, Toast.LENGTH_SHORT).show();
    }

    private void makeToolbar(){
        //Toolbar
        //________________________________________________________
        Toolbar toolbar = findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);
        // Set the title text color to white
        toolbar.setTitleTextColor(Color.WHITE);
    }
}
