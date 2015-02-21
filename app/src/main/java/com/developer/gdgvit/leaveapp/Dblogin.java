package com.developer.gdgvit.leaveapp;

/**
 * Created by SELVAM on 22-Feb-15.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Dblogin {
    static String newpass;
    public static final String KEY_ROWID="_id";
    public static final String KEY_PASSWORD="password";


    private static final String DATABASE_NAME="Leaveappdb";
    private static final String DATABASE_TABLE="user";

    private static final int DATABASE_VERSION=1;
    private DbHelper ourhelper;
    private final Context ourcontext;
    private SQLiteDatabase ourdatabase;
    private class DbHelper extends SQLiteOpenHelper
    {

        public DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);

            // TODO Auto-generated constructor stub
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO Auto-generated method stub

            db.execSQL("CREATE TABLE "+DATABASE_TABLE+" ("+KEY_PASSWORD+" TEXT NOT NULL PRIMARY KEY);");

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub
            db.execSQL("DROP TABLE IF EXISTS"+ DATABASE_TABLE);

            onCreate(db);
            createEntry(newpass);
        }


    }
    public Dblogin(Context c)
    {
        ourcontext=c;
        ourhelper=new DbHelper(ourcontext);

    }
    public Dblogin open() throws SQLException
    {
        ourhelper=new DbHelper(ourcontext);
        ourdatabase=ourhelper.getWritableDatabase();
        return this;
    }
    public Dblogin close()
    {
        ourhelper.close();
        return this;
    }
    public long createEntry(String pass) {
        // TODO Auto-generated method stub
        ContentValues cv=new ContentValues();

        cv.put(KEY_PASSWORD, pass);

        return ourdatabase.insert(DATABASE_TABLE, null, cv);

    }
    public String evaluate( long pass)throws SQLException {
        // TODO Auto-generated method stub
		/*String[] columns=new String[]{
				KEY_PASSWORD
		};*/

        Cursor c=ourdatabase.query(DATABASE_TABLE, new String[]{KEY_PASSWORD}, KEY_PASSWORD+"="+pass,null, null, null, null);
        c.moveToFirst();
        int ipass=c.getColumnIndex(KEY_PASSWORD);
        String check=c.getString(ipass);

        return check;

    }

    public String[] retrieve() {
        // TODO Auto-generated method stub
        Cursor c=ourdatabase.query(DATABASE_TABLE, new String[]{KEY_PASSWORD},null, null, null, null, null);
        int count=0,i;
        int irow=c.getColumnIndex(KEY_PASSWORD);
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
            count++;
        String[] str=new String[count];
        for(i=0,c.moveToFirst();i<count;c.moveToNext(),i++)
            str[i]=c.getString(irow);
        return str;
    }
}

