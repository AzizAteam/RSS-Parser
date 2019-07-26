package com.google.codelabs.mdc.java.shrine.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.codelabs.mdc.java.shrine.R;
import com.google.codelabs.mdc.java.shrine.adapters.ArticleAdapter;
import com.prof.rssparser.Article;
import com.prof.rssparser.OnTaskCompleted;
import com.prof.rssparser.Parser;

import java.util.ArrayList;
import java.util.List;

public class ProductGridFragment extends Fragment {
    private MutableLiveData<List<Article>> articleListLive = null;
    private String urlString = "https://www.androidauthority.com/feed";
    private RecyclerView mRecyclerView;
    private ArticleAdapter mAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ArrayList<Article> listArticle = new ArrayList<Article>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getArticleList().observe(getActivity(), new Observer<List<Article>>() {
            @Override
            public void onChanged(@Nullable List<Article> articles) {
                if (articles != null) {
                    mAdapter = new ArticleAdapter(articles, getContext());
                    mRecyclerView.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                }
            }
        });

        fetchFeed();
        // Inflate the layout for this fragment with the ProductGrid theme
        View view = inflater.inflate(R.layout.shr_product_grid_fragment, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.news_list);
        mAdapter = new ArticleAdapter(listArticle,getContext());
        mRecyclerView.setAdapter( mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);

        return view;
    }

    public void fetchFeed() {

        Parser parser = new Parser();
        parser.onFinish(new OnTaskCompleted() {
            @Override
            public void onTaskCompleted(List<Article> list) {
                listArticle  = (ArrayList<Article>) list;
            }

            //what to do in case of error
            @Override
            public void onError(Exception e) {
                setArticleList(new ArrayList<Article>());
                e.printStackTrace();
                snackbar.postValue("An error has occurred. Please check your internet connection then try again");
            }

        });
        parser.execute(urlString);
    }
    private MutableLiveData<String> snackbar = new MutableLiveData<>();

    public MutableLiveData<List<Article>> getArticleList() {
        if (articleListLive == null) {
            articleListLive = new MutableLiveData<>();
        }
        return articleListLive;
    }
    private void setArticleList(List<Article> articleList) {
        this.articleListLive.postValue(articleList);
    }

    private void configureSwipeRefresh() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mAdapter.getArticleList().clear();
                mSwipeRefreshLayout.setRefreshing(true);
                fetchFeed();
                mAdapter.notifyDataSetChanged();
            }
        });
    }

}