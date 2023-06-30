package sg.edu.np.mad.practical6dbhandler;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class customViewHolder extends RecyclerView.ViewHolder {
    TextView name;
    TextView desc;
    TextView id;
    TextView followed;
    public customViewHolder(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.name);
        desc = itemView.findViewById(R.id.desc);
        id = itemView.findViewById(R.id.id);
        followed = itemView.findViewById(R.id.followed);
    }
}
