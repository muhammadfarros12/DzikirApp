package com.farroos.doadandzikir

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.farroos.doadandzikir.adapter.DzikirDoaAdapter

class DzikirPagiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setContentView(R.layout.activity_dzikir_pagi)

        val rvDzikirPagi: RecyclerView = findViewById(R.id.rv_dzikir_pagi)
        rvDzikirPagi.layoutManager = LinearLayoutManager(this)
        rvDzikirPagi.adapter = DzikirDoaAdapter(DataDzikirDoa.listDzikirPagi)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}