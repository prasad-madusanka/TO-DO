package mobileapplicationdevelopment.lynx.todo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseConnection extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "TODO.db";
    private static final String TABLE_TODO = "todoTable";

    public DatabaseConnection(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String todoTable =
                "CREATE TABLE "
                        + TABLE_TODO +
                        "(ID INTEGER PRIMARY KEY AUTOINCREMENT," + "DESCRIPTION TEXT," + " DATE TEXT)";
        db.execSQL(todoTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop = "DROP IF TABLE EXISTS ";
        db.execSQL(drop + TABLE_TODO);

    }

    protected boolean insert(String date, String todoDescription) throws Exception {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("DESCRIPTION", date);
        cv.put("DATE", todoDescription);

        long result = db.insert(TABLE_TODO, null, cv);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    protected Cursor read() throws Exception {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cur = db.rawQuery("SELECT * FROM " + TABLE_TODO, null);

        return cur;
    }

    protected void delete(int id) throws Exception {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_TODO + " WHERE ID = '" + id + "'");
    }
}
