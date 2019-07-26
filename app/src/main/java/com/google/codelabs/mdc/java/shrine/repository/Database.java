package com.google.codelabs.mdc.java.shrine.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class Database extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Database";
    public static final String ARTICLE_TABLE_NAME = "Article";
    public static final String ARTICLE_COLUMN_ID = "id";
    public static final String ARTICLE_COLUMN_TITLE = "title";
    public static final String ARTICLE_COLUMN_TEXT = "content";
    public static final String ARTICLE_COLUMN_IMAGE = "image";
    public static final String ARTICLE_COLUMN_AUTHOR = "author";
    public static final String ARTICLE_COLUMN_LOGO = "logo";
    public static final String ARICLE_COLUMN_DATE = "date";

    private static final String CREATE_BDD = "CREATE TABLE " + ARTICLE_TABLE_NAME + " ("
            + ARTICLE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ARTICLE_COLUMN_TITLE + " TEXT NOT NULL, "
            + ARTICLE_COLUMN_TEXT + ARTICLE_COLUMN_IMAGE + " TEXT NOT NULL, "
            + ARTICLE_COLUMN_AUTHOR +  " TEXT NOT NULL, " + ARTICLE_COLUMN_LOGO + " TEXT NOT NULL, " + ARICLE_COLUMN_DATE
            + " TEXT NOT NULL);";

    public Database(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
