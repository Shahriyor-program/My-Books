package tjk.biznes.mybooks;

import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Audio> audioList;

//    public Adapter(RichDadPoorDad context, List<MediaStore.Audio> audioList) {
//        this.context = context;
//        this.audioList = audioList;
//    }

    public Adapter(RichDadPoorDad context, List<Audio> audioList) {
        this.context = context;
        this.audioList = audioList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ((ViewHolder)holder).tvTitle.setText(audioList.get(position).getTitle());
        ((ViewHolder)holder).descTitle.setText(audioList.get(position).getDescTitle());
        ((ViewHolder)holder).cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailAudioActivity.class);
                intent.putExtra("title", audioList.get(holder.getAdapterPosition()).getTitle());
                intent.putExtra("descTitle", audioList.get(holder.getAdapterPosition()).getDescTitle());
                intent.putExtra("mp3", audioList.get(holder.getAdapterPosition()).getMp3());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (this.audioList !=null){
            return this.audioList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public CardView cardView;
        public TextView tvTitle, descTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card);
            tvTitle = itemView.findViewById(R.id.titleAudio);
            descTitle = itemView.findViewById(R.id.descTitle);
            tvTitle.setEllipsize(TextUtils.TruncateAt.MARQUEE);
            tvTitle.setSelected(true);
        }
    }
}
