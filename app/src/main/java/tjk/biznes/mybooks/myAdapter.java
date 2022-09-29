package tjk.biznes.mybooks;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class myAdapter extends FirebaseRecyclerAdapter <model, myAdapter.myViewHolder> {

    public myAdapter(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull model model) {
        holder.nameChapter.setText(model.getName_chapter());
        holder.descChapter.setText(model.getDesc_chapter());
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        TextView nameChapter, descChapter;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            nameChapter = itemView.findViewById(R.id.nameChapter);
            descChapter = itemView.findViewById(R.id.descChapter);
            descChapter.setSelected(true);
        }
    }

}
