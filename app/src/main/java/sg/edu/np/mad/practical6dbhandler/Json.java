package sg.edu.np.mad.practical6dbhandler;

import com.google.gson.annotations.SerializedName;


public class Json {

    @SerializedName("jack")
    User jack;


    public void setJack(User jack) {
        this.jack = jack;
    }
    public User getJack() {
        return jack;
    }

}