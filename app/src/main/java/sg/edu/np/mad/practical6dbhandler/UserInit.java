package sg.edu.np.mad.practical6dbhandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserInit {
    public ArrayList<User> initializeUsers() {
        ArrayList<User> users = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            User user = new User("Name-" + generateName(), "Description-"+generateDesc(),
                    i+1, generateFollowed());
            //users[i] = user;
            users.add(user);
        }
        return users;

    }

    public int generateName(){
        Random rand = new Random();
        int myNumber = rand.nextInt(2147483647);
        return myNumber;
    }

    public int generateDesc(){
        Random rand = new Random();
        int myNumber = rand.nextInt(2147483647);
        return myNumber;
    }

    public boolean generateFollowed() {
        Random rand = new Random();
        boolean myBoolean = rand.nextBoolean();
        return myBoolean;
    }
}
