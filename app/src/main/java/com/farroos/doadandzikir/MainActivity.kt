package com.farroos.doadandzikir

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.farroos.doadandzikir.adapter.ArtikelAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var dotSlider: Array<ImageView?>

    private val listArtikel: ArrayList<Artikel> = arrayListOf()

    private val slidingCallback = object : ViewPager2.OnPageChangeCallback(){

        override fun onPageSelected(position: Int) {
            for (i in 0 until listArtikel.size){
                dotSlider[i]?.setImageDrawable(
                    ContextCompat.getDrawable(applicationContext, R.drawable.dot_inactive)
                )
            }
            dotSlider[position]?.setImageDrawable(
                ContextCompat.getDrawable(applicationContext, R.drawable.dot_active)
            )
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initData()
        initView()
        setupViewPager()

    }

    private fun setupViewPager() {
        val llSliderDots: LinearLayout = findViewById(R.id.ll_slider_dots)

        dotSlider = arrayOfNulls(listArtikel.size)

        for (i in 0 until listArtikel.size){
            dotSlider[i] = ImageView(this)
            dotSlider[i]?.setImageDrawable(
                ContextCompat.getDrawable(
                    applicationContext, R.drawable.dot_inactive
                )
            )

            // menentukan height dan width indikator
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            // margin
            params.setMargins(9, 0, 8, 0)
            // gravity
            params.gravity = Gravity.CENTER
            // aturan indcator ditampilkan
            llSliderDots.addView(dotSlider[i], params)
        }

        dotSlider[0]?.setImageDrawable(
            ContextCompat.getDrawable(
                applicationContext, R.drawable.dot_active
            )
        )

    }

    private fun initData() {
        val titleArtikel = resources.getStringArray(R.array.arr_title_artikel)
        val descArtikel = resources.getStringArray(R.array.arr_desc_artikel)
        val imgArtikel = resources.obtainTypedArray(R.array.arr_img_artikel)

        for(data in titleArtikel.indices){
            val artikel = Artikel(
                titleArtikel[data],
                descArtikel[data],
                imgArtikel.getResourceId(data, 0)
            )
            listArtikel.add(artikel)
        }
        imgArtikel.recycle()
    }

    private fun initView() {
        val llDzikirDoaSholat: LinearLayout = findViewById(R.id.ll_dzikir_doa_sholat)
        llDzikirDoaSholat.setOnClickListener {
            startActivity(Intent(this, QauliyahShalatActivity::class.java))
        }

        val llDzikirSetiapSaat = findViewById<LinearLayout>(R.id.ll_dzikir_setiap_saat)
        llDzikirSetiapSaat.setOnClickListener {
            startActivity(Intent(this, SetiapSaatDzikirActivity::class.java))
        }

        val llDzikirDoaHarian: LinearLayout = findViewById(R.id.ll_dzikir_doa_harian)
        llDzikirDoaHarian.setOnClickListener {
            startActivity(Intent(this, HarianDzikirDoaActivity::class.java))
        }

        val llDzikirPagiPetang: LinearLayout = findViewById(R.id.ll_dzikir_pagi_petang)
        llDzikirPagiPetang.setOnClickListener {
            startActivity(Intent(this, PagiPetangDzikirActivity::class.java))
        }

        val vpArtikel: ViewPager2 = findViewById(R.id.vp_artikel)
        vpArtikel.adapter = ArtikelAdapter(listArtikel)
        vpArtikel.registerOnPageChangeCallback(slidingCallback)

    }

}