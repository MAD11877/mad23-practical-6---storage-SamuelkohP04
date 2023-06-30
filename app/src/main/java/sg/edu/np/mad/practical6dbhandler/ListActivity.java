package sg.edu.np.mad.practical6dbhandler;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    private ArrayList<User> users;
    private customAdapter cuAdapter;
    private MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);;
    private boolean isInitialized = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        makeToolbar();
        //int currentVersion = dbHandler.getDatabaseVersion();
        //int savedVersion = dbHandler.getSavedDatabaseVersion();


        // Initialize users only if it hasn't been done before
        if (!isInitialized) {
            UserInit userInit = new UserInit();
            users = userInit.initializeUsers();

            dbHandler.addUserList(users);
            isInitialized = true;
        }

        //ArrayList<User> fetchedUsers = dbHandler.getUsers();
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        for (User user:users){
            Log.v("J",String.valueOf(user));
        }
        cuAdapter = new customAdapter(users);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(cuAdapter);


        cuAdapter.setOnItemClickListener(new customAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(User user){
                Log.v("hi", user.getName());
                //User user = myListObj.get(i);
                showAlert("Profile", user);
            }
        });

        Log.v("Hi", "On create");
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Retrieve the latest data from the database
        ArrayList<User> fetchedUsers = dbHandler.getUsers();
        // Clear the previous data in the adapter
        cuAdapter.clear();
        // Add the updated users to the adapter
        cuAdapter.addAll(fetchedUsers);
        // Notify the adapter that the data has changed
        cuAdapter.notifyDataSetChanged();

        //ArrayList<User> fetchedUsers = dbHandler.getUsers();
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        cuAdapter = new customAdapter(users);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(cuAdapter);

        cuAdapter.setOnItemClickListener(new customAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(User user){
                Log.v("hi", user.getName());
                //User user = myListObj.get(i);
                showAlert("Profile", user);
            }
        });
        Log.v("Hi", "On resume");
    }

    private void showAlert(String title, User user) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(user.getName())
                .setPositiveButton("View", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Do something when the "OK" button is clicked
                        Intent intent = new Intent(ListActivity.this, MainActivity.class);
                        intent.putExtra("Name", user.getName());
                        intent.putExtra("Desc", user.getDescription());
                        intent.putExtra("Id", user.getId());
                        intent.putExtra("Followed", user.isFollowed());
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Do something when the "Cancel" button is clicked
                    }
                })
                .show();
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