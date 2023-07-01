package sg.edu.np.mad.practical6dbhandler;

import com.google.gson.annotations.SerializedName;


public class UserData {

    @SerializedName("password")
    String password;

    @SerializedName("username")
    String username;

    public UserData(String username, String password){}

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }

}