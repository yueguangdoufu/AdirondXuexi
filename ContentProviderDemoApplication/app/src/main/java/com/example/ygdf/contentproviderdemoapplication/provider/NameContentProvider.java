package com.example.ygdf.contentproviderdemoapplication.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.ygdf.contentproviderdemoapplication.helper.DBOpenHelper;

public class NameContentProvider extends ContentProvider {

    private static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
    private DBOpenHelper dbOpenHelper;

    static {
        matcher.addURI("com.example.ygdf.contentproviderdemoapplication.provider","test",1);
    }

    @Override
    public boolean onCreate() {
        dbOpenHelper = new DBOpenHelper(this.getContext(),"test",null,1);
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

        switch (matcher.match(uri)){
            case 1:
                SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
                long rowid = db.insert("test",null,values);
                if(rowid > 0){
                    Uri nameUri = ContentUris.withAppendedId(uri,rowid);
                    getContext().getContentResolver().notifyChange(nameUri,null);
                    return nameUri;
                }

        }

        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
