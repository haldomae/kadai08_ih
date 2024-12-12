package com.hal_domae.kadai08_ih

import android.os.Bundle
import android.widget.SimpleAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hal_domae.kadai08_ih.databinding.ActivityMainBinding
import com.hal_domae.kadai08_ih.recyclerview.ListAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var dbHelper: DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // データベースから取り出したデータを入れる
        val data = mutableListOf<Map<String, String>>()

        // データベースを用意
        dbHelper = DatabaseHelper(this@MainActivity)

        // データベースからデータを取り出す
        dbHelper.readableDatabase.use { db ->
            // queryメソッドの引数
            // 1 : テーブル名
            // 2 : 取得するカラム
            // 3 : 検索条件
            // 4 : 検索条件に使う値
            // 5 : グループ化
            // 6 : グループ化に関する条件
            // 7 : 並び順
            // 8 : 取得する件数
            val cursor = db.query("diary_items", null, null, null, null,null, "diary_date DESC", null)

            // データを取り出す時にはCursorオブジェクトを使う
            // Cursorオブジェクトはどの行を指しているか
            cursor.use {
                // moveToNextで位置を動かす
                while (it.moveToNext()){
                    data.add(mapOf("date" to it.getString(0), "text" to it.getString(1)))
                }
            }
        }

        val sampleData = mutableListOf(
            mapOf("date" to "2024/01/01", "text" to "ここにテキストが入ります"),
            mapOf("date" to "2024/02/01", "text" to "ここにテキストが入ります"),
            mapOf("date" to "2024/03/01", "text" to "ここにテキストが入ります"),
            mapOf("date" to "2024/04/01", "text" to "ここにテキストが入ります"),
            mapOf("date" to "2024/05/01", "text" to "ここにテキストが入ります"),
            mapOf("date" to "2024/06/01", "text" to "ここにテキストが入ります"),
            mapOf("date" to "2024/07/01", "text" to "ここにテキストが入ります"),
            mapOf("date" to "2024/08/01", "text" to "ここにテキストが入ります"),
        )

//        binding.diaryList.adapter = SimpleAdapter(
//            this,
//            sampleData,
//            R.layout.list_item,
//            arrayOf("date", "text"),
//            intArrayOf(R.id.date, R.id.text)
//        )
        // RecyclerViewの設定
        // LayoutManagerでリストの表示形式を決める
        binding.diaryList.layoutManager = LinearLayoutManager(this)
        binding.diaryList.adapter = ListAdapter(data)

        // 区切り線を引く
        val dividerItemDecoration = DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL)
        binding.diaryList.addItemDecoration(dividerItemDecoration)
    }
}