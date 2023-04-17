package in.nandhu.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import in.nandhu.newsapp.Models.NewsHeadlines;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    NewsHeadlines newsHeadlines;
   TextView txt_title,txt_author,txt_time,txt_detail,txt_content;
    ImageView img_news;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        txt_title=findViewById(R.id.text_detail_title);
        txt_author=findViewById(R.id.text_detail_author);
        txt_time=findViewById(R.id.text_details_time);
        txt_detail=findViewById(R.id.text_detail_detail);
        txt_content=findViewById(R.id.text_detail_content);
        img_news=findViewById(R.id.img_detail);

        newsHeadlines= (NewsHeadlines) getIntent().getSerializableExtra("data");

        txt_title.setText(newsHeadlines.getTitle());
        txt_author.setText(newsHeadlines.getAuthor());
        txt_time.setText(newsHeadlines.getPublishedAt());
        txt_detail.setText(newsHeadlines.getDescription());
        txt_content.setText(newsHeadlines.getContent());
        Picasso.get().load(newsHeadlines.getUrlToImage()).into(img_news);

        Log.e("**** TITLE ****",newsHeadlines.getTitle());
        Log.e("**** AUTHOR ****",newsHeadlines.getAuthor());
        Log.e("**** PUBLISH ****",newsHeadlines.getPublishedAt());
        Log.e("**** DISCRIPTION ****",newsHeadlines.getDescription());
        Log.e("**** CONTENT ****",newsHeadlines.getContent());

    }
}