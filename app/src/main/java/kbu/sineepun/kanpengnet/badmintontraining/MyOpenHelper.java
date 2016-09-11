package kbu.sineepun.kanpengnet.badmintontraining;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by masterUNG on 9/5/2016 AD.
 */
public class MyOpenHelper extends SQLiteOpenHelper{

    public static final String database_name = "badminton.db";
    private static final int database_version = 1;
    private static final String create_bad_table = "create table testTABLE (" +
            "_id integer primary key," +
            "Hub1 text," +
            "Hub2 text," +
            "Hub3 text," +
            "Hub4 text," +
            "Hub5 text);";
    private static final String add_first_data = "insert into testTABLE VALUES (NULL, '0', '0', '0', '0', '0');";

    public MyOpenHelper(Context context) {
        super(context, database_name, null, database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(create_bad_table);
        sqLiteDatabase.execSQL(add_first_data);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}   // Main Class
