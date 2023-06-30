package sg.edu.np.mad.practical6dbhandler;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class customAdapter extends RecyclerView.Adapter<customViewHolder> {
    private ArrayList<User> list_object;
    //set global variables
    private OnItemClickListener itemClickListener;
    //private Context context;

    public void addAll(ArrayList<User> fetchedUsers) {
        list_object.addAll(fetchedUsers);
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick (User user);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        itemClickListener = listener;
    }

    public customAdapter(ArrayList<User> input) {
        list_object = input;
    }


    @NonNull
    @Override
    public customViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        if (viewType == 0) {
            View view = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.customlayout, parent, false);
            customViewHolder holder = new customViewHolder(view);
            return holder;
        }
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.customlayout, parent, false);
        customViewHolder holder = new customViewHolder(view);
        return holder;

    }

    public void onBindViewHolder(customViewHolder holder, int position) {
        User obj = list_object.get(position);
        holder.name.setText(obj.getName());
        holder.desc.setText(obj.getDescription());

        //Setting ID text with a placeholder
        //______________________________________________________________________
        String idPrefix = "Id-";
        String idText = String.valueOf(obj.getId());
        Log.v("id",String.valueOf(idPrefix));
        holder.id.setText(idText);


        // Set the followed text using resource string with a placeholder
        //______________________________________________________________________
        String followedText = "Followed-" + (obj.isFollowed() ? "true" : "false");
        holder.followed.setText(followedText);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Call the onItemClick method of the listener when the user is clicked
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(obj);
                    Log.v("Hi","Hi");
                }
            }
        });
    }
    //get item count
    public int getItemCount(){
        return list_object.size();
    }
    public void clear() {
        list_object.clear();
    }
}
