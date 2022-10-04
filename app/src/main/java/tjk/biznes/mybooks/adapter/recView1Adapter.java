package tjk.biznes.mybooks.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import tjk.biznes.mybooks.EatThatFrog;
import tjk.biznes.mybooks.R;

public class recView1Adapter extends FirebaseRecyclerAdapter<model, recView1Adapter.myViewHolder> {

    public recView1Adapter(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull model model) {
        holder.nameBook.setText(model.name_book);
        holder.priceBook.setText(model.price_book);
        holder.imgBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
//                activity.getSupportFragmentManager().beginTransaction().replace(R.id, new EatThatFrog()).addToBackStack(null).commit();

            }
        });
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_view1, parent, false);
        return new myViewHolder(view);
    }

    public class myViewHolder extends RecyclerView.ViewHolder{

        TextView nameBook, priceBook;
        ImageView imgBook;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            nameBook = itemView.findViewById(R.id.nameBook);
            priceBook = itemView.findViewById(R.id.priceBook);
            imgBook = itemView.findViewById(R.id.imgBook);

            nameBook.setSelected(true);
        }
    }

}
