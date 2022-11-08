package com.kazuya.if5b.contact.loaders;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.kazuya.if5b.contact.databases.UserDatabase;
import com.kazuya.if5b.contact.entities.User;

import java.util.List;

public class GetDataLoader<userDatabase> extends AsyncTaskLoader<List<User>> {
    private UserDatabase db;
    public GetDataLoader(@NonNull Context context) {
        super(context);
        db = UserDatabase.getInstance(context);
    }

    @Nullable
    @Override
    public List<User> loadInBackground() {
        return db.userDao().getAllUsers();
    }
}
