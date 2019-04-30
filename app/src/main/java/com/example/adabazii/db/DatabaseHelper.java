package com.example.adabazii.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String WORDS_TABLE = "WORDS";
    public static final String COLUMN_ID = "_ID";

    public static final String[] categories = new String[] {"animals", "places", "sports", "films", "foods", "jobs", "kings", "publics", "things"};

    public static final String[][][] content = new String[][][] {
            {
                    {"زنبور", "شتر", "طاووس", "شیر دریایی"},
                    {"موش کور", "وال", "خفاش", "کوسه"},
                    {"سوسمار نر", "میمون سیرک", "قمری", "قاصدک"},
            },
            {
                    {"جنگل", "اداره", "سوپرمارکت", "مدرسه"},
                    {"بیمارستان", "پارک آبی", "سرویس بهداشتی", "کارگاه"},
                    {"تالاب گاوخونی", "تجریش", "کلمبیا", "کارخانه بازیافت"},
            },
            {
                    {"کشتی", "وزنه برداری", "ایروبیک", "پنالتی"},
                    {"تنیس دستی", "تیراندازی با کمان", "پرس سینه", "فوتبال دستی"},
                    {"پاور لیفتینگ", "کمیته انتظامی", "", "پرواز با بالن"},
            },
            {
                    {"کد داوینچی", "قیصر", "چشم در برابر چشم", "پا تو کفش من نکن"},
                    {"موتور سواری", "بوکس", "بالن سواری", "دو پینگ"},
                    {"پاراالمپیک", "راند دهم", "اسکواش", "اسکی رو برف"},
            },
            {
                    {"گوجه سبز", "چیپس فلفلی", "سالاد", "چیپس"},
                    {"اشکنه", "سالاد شیرازی", "الویه", "ته چین"},
                    {"تخم مرغ آبپز", "مرغ سوخاری", "کتلت سیب زمینی", "انبه"},
            },
            {
                    {"آشپز", "برقکار", "جراح مغر و اعصاب", "پزشک"},
                    {"مدرس", "طراح لباس", "عینک ساز", "طراح داخلی"},
                    {"مغازه دار", "گزارشگر", "کوزه گر", "تکنسین اتاق عمل"},
            },
            {
                    {"دهقان فداکار", "مستربین", "پینوکیو", "مشتی مندلی"},
                    {"فرزاد فرزین", "امیرکبیر", "زیدان", "لپونارد داوینجی"},
                    {"ابوالقاسم فردوسی", "گوریل انگوری", "احسان علیخانی", "جک اسپارو"},
            },
            {
                    {"غمگین", "تابلو", "آچار", "آب خوردن"},
                    {"شگفت زده", "ورشکسته", "یقه پیراهن", "شکمو"},
                    {"بحران کم آبی", "آموزش از راه دور", "عذاب وجدان", "آبرفت"},
            },
            {
                    {"ایزوگام", "نقشه", "چوراب", "شلنگ"},
                    {"قلم", "زنبیل", "آقتابه", "گلدون پر از گل"},
                    {"قاب عکس", "آچار فرانسه", "ضبط صوت", "صندلی بادی"},
            },
    };

    private static final String DB_NAME = "adabazi.db";

    private static final int DB_VERSION = 1;

    private static final String DB_CREATE =
            "CREATE TABLE " + WORDS_TABLE + " ( " +
                    COLUMN_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, WORD TEXT, CATEGORY TEXT, SCORE INTEGER)";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CREATE);

        int categoryIndex = 0;
        int score = 2;

        for(String category : categories) {
            score = 2;
            for(String[] scoredCategory : content[categoryIndex]) {

                for(String word : scoredCategory) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("WORD", word);
                    contentValues.put("CATEGORY", category);
                    contentValues.put("SCORE", score);
                    db.insert(DatabaseHelper.WORDS_TABLE, null, contentValues);
                }
                score += 2;

            }
            categoryIndex++;
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
