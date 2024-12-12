package com.hal_domae.kadai08_ih

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context): SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    // 初期設定
    companion object{
        private const val DB_NAME = "diary.sqlite"
        private const val DB_VERSION = 1
    }

    // データベースを作る
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.let{
            // CREATE文実行
            it.execSQL("CREATE TABLE diary_items(diary_date TEXT PRIMARY KEY, diary_text TEXT)")

            // テスト用のデータ
            it.execSQL("INSERT INTO diary_items VALUES('2024/01/01', 'テスト1')")
            it.execSQL("INSERT INTO diary_items VALUES('2024/02/01', 'テスト2')")
        }
    }

    // データ更新
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }
}