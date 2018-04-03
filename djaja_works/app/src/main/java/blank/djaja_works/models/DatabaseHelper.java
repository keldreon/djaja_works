package blank.djaja_works.models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{
    private static final String TAG = DatabaseHelper.class.getSimpleName();
    private static final String DATABASE_NAME = "Djaya_backend.db";
    private static final int DATABASE_VERSION = 1;
    //Wilayah User
    private static final String TABLE_NAME = "user";
    private static final String COL1 = "ID_USER";
    private static final String COL2 = "USERNAME";
    private static final String COL3 = "EMAIL";
    private static final String COL4 = "JENIS_KELAMIN";
    private static final String COL5 = "NO_KTP";
    private static final String COL6 = "NO_REKENING";
    private static final String COL7 = "STATUS";

    private String createTable = "CREATE TABLE " + TABLE_NAME + " (" + COL1 + " INTEGER PRIMARY KEY, " + COL2 + " TEXT, " + COL3 + " TEXT, " + COL4 + " TEXT, " + COL5 + " TEXT, " + COL6 + " TEXT, " + COL7 + " TEXT" + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
