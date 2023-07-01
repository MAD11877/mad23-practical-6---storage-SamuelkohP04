package sg.edu.np.mad.practical6dbhandler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import androidx.fragment.app.Fragment;

public class MessageGroup extends AppCompatActivity {

    //Avoid as much global variables as possible
    //__________________________________________________________________________________________

    //private Button buttonGroup1;
    //private Button buttonGroup2;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_group);
        Intent intent = getIntent();
        makeToolbar();
        Button buttonGroup1 = findViewById(R.id.buttonGroup1);
        Button buttonGroup2 = findViewById(R.id.buttonGroup2);
        frameLayout = findViewById(R.id.frameLayout);

        buttonGroup1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                loadFragment(new Group1Fragment());
            }
        });

        buttonGroup2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                loadFragment(new Group2Fragment());
            }
        });
    }


    // Private method to load fragment
    //______________________________________________________________________________________________
    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
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