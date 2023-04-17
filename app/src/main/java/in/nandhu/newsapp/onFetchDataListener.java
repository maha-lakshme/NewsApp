package in.nandhu.newsapp;

import java.util.List;

import in.nandhu.newsapp.Models.NewsHeadlines;

public interface onFetchDataListener<NewsApiResponse>{
    void onFetchData(List<NewsHeadlines> list, String message);
    void onError(String message);
}
