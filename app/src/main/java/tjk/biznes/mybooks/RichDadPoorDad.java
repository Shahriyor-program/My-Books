package tjk.biznes.mybooks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class RichDadPoorDad extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Adapter adapter;
    private List<Audio> audioList;
    private Audio audio;
    private CardView btnRead;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rich_dad_poor_dad);

        recyclerView = findViewById(R.id.recycler);
        btnRead = findViewById(R.id.btnRead);

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(RichDadPoorDad.this, ReadBookActivity.class));
//                finish();
            }
        });

        GridLayoutManager manager = new GridLayoutManager(RichDadPoorDad.this,1);
        recyclerView.setLayoutManager(manager);

        audioList = new ArrayList<>();

        audio = new Audio(R.string.txtCh_1, R.string.ch_1 ,R.raw.foreword);
        audioList.add(audio);
        audio = new Audio(R.string.txtCh_2, R.string.ch_2 ,R.raw.chapter_1);
        audioList.add(audio);

        adapter = new Adapter(RichDadPoorDad.this, audioList);
        recyclerView.setAdapter(adapter);
    }



}