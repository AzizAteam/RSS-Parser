package com.google.codelabs.mdc.java.shrine.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.codelabs.mdc.java.shrine.R;
import com.prof.rssparser.Article;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.MyViewHolder> {



    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView category;
        TextView title;
        ImageView image;
        TextView pubDate;

        MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.row_title);
            image = (ImageView) view.findViewById(R.id.row_image);
            pubDate = (TextView) view.findViewById(R.id.row_pubDate);
            category = (TextView) view.findViewById(R.id.row_categories);
        }
    }
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row,parent, false);
        return new MyViewHolder(v);
    }


    private List<Article> articles;

    private Context mContext;
    private WebView articleView;

    public ArticleAdapter(List<Article> list, Context context) {
        this.articles = list;
        this.mContext = context;
    }

    public List<Article> getArticleList() {
        return articles;
    }


    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder viewHolder, int position) {

        Article currentArticle = articles.get(position);
        Log.e("article", currentArticle.getTitle());
        String pubDateString;
        try {
            String sourceDateString = currentArticle.getPubDate();

            SimpleDateFormat sourceSdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
            Date date = sourceSdf.parse(sourceDateString);

            SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
            pubDateString = sdf.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
            pubDateString = currentArticle.getPubDate();
        }

        viewHolder.title.setText(currentArticle.getTitle());

        Picasso.get()
                .load(currentArticle.getImage())
                .placeholder(R.drawable.placeholder)
                .into(viewHolder.image);

        viewHolder.pubDate.setText(pubDateString);

        StringBuilder categories = new StringBuilder();
        for (int i = 0; i < currentArticle.getCategories().size(); i++) {
            if (i == currentArticle.getCategories().size() - 1) {
                categories.append(currentArticle.getCategories().get(i));
            } else {
                categories.append(currentArticle.getCategories().get(i)).append(", ");
            }
        }

        viewHolder.category.setText(categories.toString());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("SetJavaScriptEnabled")
            @Override
            public void onClick(View view) {
                //show article content inside a dialog
                articleView = new WebView(mContext);

                articleView.getSettings().setLoadWithOverviewMode(true);

                String title = articles.get(viewHolder.getAdapterPosition()).getTitle();
                String content = articles.get(viewHolder.getAdapterPosition()).getContent();

                articleView.getSettings().setJavaScriptEnabled(true);
                articleView.setHorizontalScrollBarEnabled(false);
                articleView.setWebChromeClient(new WebChromeClient());
                articleView.loadDataWithBaseURL(null, "<style>img{display: inline; height: auto; max-width: 100%;} " +

                        "</style>\n" + "<style>iframe{ height: auto; width: auto;}" + "</style>\n" + content, null, "utf-8", null);

                AlertDialog alertDialog = new AlertDialog.Builder(mContext,android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen).create();
                alertDialog.setTitle(title);
                alertDialog.setView(articleView);
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();

                ((TextView) alertDialog.findViewById(android.R.id.message)).setMovementMethod(LinkMovementMethod.getInstance());
            }
        });
    }

    @Override
    public int getItemCount() {
        return articles == null ? 0 : articles.size();
    }
}