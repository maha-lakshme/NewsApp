package in.nandhu.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import in.nandhu.newsapp.Models.CustomAdapter;
import in.nandhu.newsapp.Models.NewsApiResponse;
import in.nandhu.newsapp.Models.NewsHeadlines;
import in.nandhu.newsapp.Models.RequestManager;
import in.nandhu.newsapp.Models.SelectListener;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SelectListener, View.OnClickListener {

    RecyclerView recyclerView;
    CustomAdapter adapter;
    ProgressDialog progressDialog;
    SearchView searchView;
    Button b1,b2,b3,b4,b5,b6,b7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchView=findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                RequestManager requestManager = new RequestManager(MainActivity.this);
                requestManager.getNewsHeadlines(listener,"general",query);
                progressDialog.setTitle("Fetching news articles  "+ query);
                progressDialog.show();
                 return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Fetching articles...");
        progressDialog.show();

        b1=findViewById(R.id.btn_1);
        b1.setOnClickListener(this);
        b2=findViewById(R.id.btn_2);
        b2.setOnClickListener(this);
        b3=findViewById(R.id.btn_3);
        b3.setOnClickListener(this);
        b4=findViewById(R.id.btn_4);
        b4.setOnClickListener(this);
        b5=findViewById(R.id.btn_5);
        b5.setOnClickListener(this);
        b6=findViewById(R.id.btn_6);
        b6.setOnClickListener(this);
        b7=findViewById(R.id.btn_7);
        b7.setOnClickListener(this);

        RequestManager requestManager = new RequestManager(this);
        requestManager.getNewsHeadlines(listener,"general",null);
    }
    private final onFetchDataListener<NewsApiResponse> listener = new onFetchDataListener<NewsApiResponse>() {
        @Override
        public void onFetchData(List<NewsHeadlines> list, String message) {
            if(list.isEmpty()){
                Toast.makeText(MainActivity.this,"No Data Found",Toast.LENGTH_LONG);
                Log.e("No Result Found","List empty");
            }else {
                showNews(list);
                progressDialog.dismiss();
            }

        }

        @Override
        public void onError(String message) {

            Toast.makeText(MainActivity.this,"Error Occured",Toast.LENGTH_SHORT);
        }
    };

    private void showNews(List<NewsHeadlines> list) {
         recyclerView= findViewById(R.id.recycler_main);
         recyclerView.setHasFixedSize(true);
         recyclerView.setLayoutManager(new GridLayoutManager(this,1));
         adapter =  new CustomAdapter(this,list,this);
         recyclerView.setAdapter(adapter);
    }


    @Override
    public void onNewsClicked(NewsHeadlines newsHeadlines) {

        startActivity(new Intent(MainActivity.this,DetailsActivity.class).putExtra("data",newsHeadlines));
    }

    @Override
    public void onClick(View v) {

        Button button= (Button)v;
        String category  = button.getText().toString();

        progressDialog.setTitle("Fetching news articles "+category);
        progressDialog.show();

        RequestManager requestManager = new RequestManager(this);
        requestManager.getNewsHeadlines(listener,category,null);


    }
}