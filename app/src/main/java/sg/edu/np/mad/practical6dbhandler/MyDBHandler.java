package sg.edu.np.mad.practical6dbhandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MyDBHandler extends SQLiteOpenHelper {
    static String title = "MyDBHandler";
    String currentUser;
    public static int DATABASE_VERSION = 2;
    //start with version 1, or version 0 also can.
    public static String DATABASE_NAME = "Users.db";
    public static String USER = "Users";
    //account is the table we are using.
    public static String COLUMN_NAME = "Name";
    //a column for username
    public static String COLUMN_DESC = "Description";
    //and a column for description
    public static String COLUMN_ID = "Id";
    //a column for username
    public static String COLUMN_FOLLOWED = "Followed";
    //and a column for description
    private int savedDatabaseVersion;

    // S.No   UserName    Description   Id  Followed
    // 1      ...         ...           ... ...
    // 2      ...         ...
    // 3


    public MyDBHandler(Context context, String name,
                       SQLiteDatabase.CursorFactory factory,
                       int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        savedDatabaseVersion = version;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //onCreate just means we want to create the database, that's it.
        String CREATE_ACCOUNT_TABLE = "CREATE TABLE " + USER + "(" + COLUMN_NAME +
                " TEXT, " + COLUMN_DESC + " TEXT," +  COLUMN_ID + " TEXT," +  COLUMN_FOLLOWED + " TEXT)";

        //CREATE TABLE USER (
        //  COLUMN_NAME TEXT,
        //  COLUMN_DESC TEXT,
        //  COLUMN_ID TEXT,
        //  COLUMN_FOLLOWED TEXT
        //);

        Log.i(title, CREATE_ACCOUNT_TABLE);
        db.execSQL(CREATE_ACCOUNT_TABLE);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + USER);
        onCreate(db);
    }

    /*
    public int getDatabaseVersion() {
        SQLiteDatabase db = this.getReadableDatabase();
        int version = db.getVersion();
        db.close();
        return version;
    }
    public int getSavedDatabaseVersion() {
        return savedDatabaseVersion;
    }
    */

    public void addUserList(ArrayList<User> users) {
        SQLiteDatabase db = this.getWritableDatabase();

        try {
            // Clear existing user entries in the database
            db.delete(USER, null, null);

            // Insert the new users into the database
            for (User user : users) {
                ContentValues values = new ContentValues();
                values.put(COLUMN_NAME, user.getName());
                values.put(COLUMN_DESC, user.getDescription());
                values.put(COLUMN_ID, user.getId());
                values.put(COLUMN_FOLLOWED, user.isFollowed() ? 1 : 0);

                db.insert(USER, null, values);
                Log.i(title, "Inserted/created user" + values.toString());
            }
        } catch (SQLiteException e) {
            Log.e(title, "Error inserting users: " + e.getMessage());
        } finally {
            db.close();
        }
    }

    public ArrayList<User> getUsers() {
        ArrayList<User> userList = new ArrayList<>();
        // Assuming you have a SQLiteDatabase object named "database"
        SQLiteDatabase database = getReadableDatabase();

        // Assuming you have a table named "users" with appropriate column names
        Cursor cursor = database.query("Users", null, null,
                null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Assuming you have appropriate column indices for each column
                String name = cursor.getString(0);
                String description = cursor.getString(1);
                int id = cursor.getInt(2);
                boolean followed = (cursor.getInt(3) == 1);

                User user = new User(name, description, id, followed);
                userList.add(user);
            } while (cursor.moveToNext());

            cursor.close();
        }

        return userList;
    }

    public void updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, user.getName());
            values.put(COLUMN_DESC, user.getDescription());
            values.put(COLUMN_ID, user.getId());
            values.put(COLUMN_FOLLOWED, user.isFollowed() ? 1 : 0);
            Log.v("pfpage", String.valueOf(user.getId()));

            // Update the user in the database, where the ID of the user matches the DB.
            int rowsAffected = db.update(USER, values, COLUMN_ID + " = ?", new String[]{String.valueOf(user.getId())});
            Log.v("j", "Rows affected: " + rowsAffected);

        } catch (SQLiteException e) {
            Log.e("j", "Error updating user: " + e.getMessage());

        } finally {
            db.close();
        }
    }


/*
    public User findUser(String username){
        String query = "SELECT * FROM " + USER + " WHERE " +
                COLUMN_USERNAME + "=\'" + username + "\'";
        Log.i(title, "Query: " + query);

        //SELECT * FROM ACCOUNTS
        //WHERE COLUMN_USERNAME = 'username';

        User queryResult = new User();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){
            queryResult.setUsername(cursor.getString(0));
            queryResult.setPassword(cursor.getString(1));
            currentUser = queryResult.getUsername();
            currentPass = queryResult.getPassword();
            Log.i(title, "Queried Username: " + queryResult.getUsername());
            Log.i(title, "Queried Password: " + queryResult.getPassword());
            cursor.close();
        }
        else{
            queryResult = null;
        }
        db.close();
        return queryResult;
    }

 */


}
