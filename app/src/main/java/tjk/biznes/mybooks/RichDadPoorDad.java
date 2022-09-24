package tjk.biznes.mybooks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import tjk.biznes.mybooks.databinding.RichDadPoorDadBinding;

public class RichDadPoorDad extends AppCompatActivity {
    private RichDadPoorDadBinding binding;
    private myAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = RichDadPoorDadBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.recycler.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("book_rich"), model.class)
                        .build();

        myAdapter = new myAdapter(options);
        binding.recycler.setAdapter(myAdapter);

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RichDadPoorDad.this, MainActivity.class));
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        myAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        myAdapter.stopListening();
    }
}