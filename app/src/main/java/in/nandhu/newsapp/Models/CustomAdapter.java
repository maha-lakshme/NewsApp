package in.nandhu.newsapp.Models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import in.nandhu.newsapp.R;

public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder> {

    private Context context;
    private List<NewsHeadlines> newsHeadlines;
    private SelectListener listener;

    public CustomAdapter(Context context, List<NewsHeadlines> newsHeadlines,SelectListener listener) {
        this.context = context;
        this.newsHeadlines = newsHeadlines;
        this.listener=listener;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.headline_list_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.txt_title.setText(newsHeadlines.get(position).getTitle());
        holder.txt_title.setText(newsHeadlines.get(position).getSource().getName());
        if(newsHeadlines.get(position).getUrlToImage()!=null){
            Picasso.get().load(newsHeadlines.get(position).getUrlToImage()).into(holder.img_headline);
}
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onNewsClicked(newsHeadlines.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return newsHeadlines.size();
    }
}
