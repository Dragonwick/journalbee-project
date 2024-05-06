package edu.utsa.activitiesandviews;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class SQLiteManager extends SQLiteOpenHelper {
    private static SQLiteManager sqLitemanager;
    private static final DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");

    private static final String DATABASE_NAME = "JournalDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Journal";
    private static final String COUNTER = "Counter";

    private static final String ID_FIELD = "postID";
    private static final String TITLE_FILED = "title";
    private static final String DESC_FIELD = "desc";
    private static final String DELETED_FILED = "deleted";

    public SQLiteManager(Context context) { super(context, DATABASE_NAME, null, DATABASE_VERSION); }

    public static SQLiteManager instanceOfDatabase(Context context ) {
        if (sqLitemanager ==null)
            sqLitemanager = new SQLiteManager(context);
        return sqLitemanager;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        StringBuilder sql;
        sql = new StringBuilder()
                .append("CREATE TABLE ")
                .append(TABLE_NAME)
                .append("(")
                .append(COUNTER)
                .append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(ID_FIELD)
                .append(" INT, ")
                .append(TITLE_FILED)
                .append(" TEXT, ")
                .append(DESC_FIELD)
                .append(" TEXT, ")
                .append(DELETED_FILED)
                .append(" TEXT) ");
        sqLiteDatabase.execSQL(sql.toString());
    }

    @Override
    //adding new versions
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        /*
        switch (oldVersion){
            case 1:
                sqLiteDatabase.execSQL("ALTER TABLE " + TABLE_NAME + "ADD COLUMN " + NEW_COLUMN + " TEXT");
            case 2:
                sqLiteDatabase.execSQL("ALTER TABLE " + TABLE_NAME + "ADD COLUMN " + NEW_COLUMN + " TEXT");
        }
        */
    }

    public void addJournalToDatabase(Journal journal) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_FIELD, journal.getId());
        contentValues.put(TITLE_FILED, journal.getTitle());
        contentValues.put(DESC_FIELD, journal.getDescription());
        contentValues.put(DELETED_FILED, getStringFromDate(journal.getDeleted()));

        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }

    // populates array when load up for the first time
    public void populateJournalListArray() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        try (Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null)) {
            if (result.getCount()!= 0) {
                while (result.moveToNext()) {
                    int postId = result.getInt(1);
                    String title = result.getString(2);
                    String desc = result.getString(3);
                    String stringDeleted = result.getString(4);
                    Date deleted = getDateFromString(stringDeleted);
                    Journal journal = new Journal(postId,title,desc,deleted);
                    Journal.journalArrayList.add(journal);
                }
            }
        }
    }

    public void updateJournalInDB(Journal journal) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_FIELD, journal.getId());
        contentValues.put(TITLE_FILED, journal.getTitle());
        contentValues.put(DESC_FIELD, journal.getDescription());
        contentValues.put(DELETED_FILED, getStringFromDate(journal.getDeleted()));

        sqLiteDatabase.update(TABLE_NAME, contentValues, ID_FIELD + " =? ", new String[]{String.valueOf(journal.getId())});
    }

    private String getStringFromDate(Date date) {
        if (date == null)
            return null;
        return dateFormat.format(date);
    }

    private Date getDateFromString(String string) {
        try {
            return dateFormat.parse(string);
        } catch (ParseException | NullPointerException e) {
            return null;
        }
    }
}