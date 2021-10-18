package com.ayotola.uiassessment

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ayotola.uiassessment.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.lang.String

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var rv: RecyclerView
    private lateinit var bottomNav: BottomNavigationView
    lateinit var cardBtn: CardView
    private lateinit var rvAdapter: MyAdapter
    private lateinit var data : MutableList<CardItems>
    private var progr = 0
    private lateinit var progrexLevel: ProgressBar
    private lateinit var totalFee: TextView
    private lateinit var lastMoney: TextView
    private var position = 0
    private var total = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // getting the recyclerview by its id
        rv = binding.recyclerView
        cardBtn = binding.cardview2
        progrexLevel = binding.progressBar
        totalFee = binding.totalFee
        lastMoney = binding.lastFee
        bottomNav = binding.bottomNavigationView

        // this creates a vertical layout Manager
        val mLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv.layoutManager = mLayoutManager

        val mainData = listOf(
                CardItems(R.drawable.ic_baseline_agriculture_24, "Florist", "Planting of flowers", 400.00),
                CardItems(R.drawable.ic_baseline_carpenter_24, "Carpenter", "Repairing furnitures", 600.00),
                CardItems(R.drawable.ic_baseline_celebration_24, "Mummy", "Birthday celebration", 400.00),
                CardItems(R.drawable.ic_baseline_takeout_dining_24, "Boo", "Date", 3500.00),
                CardItems(R.drawable.ic_baseline_agriculture_24, "Sister", "Planting of flowers", 600.00),
                CardItems(R.drawable.ic_baseline_carpenter_24, "Hubby", "Repairing furnitures", 600.00),
                CardItems(R.drawable.ic_baseline_celebration_24, "Hubby", "Birthday celebration", 400.00),
                CardItems(R.drawable.ic_baseline_takeout_dining_24, "Tega", "Date", 3500.00),
        )

        data = mutableListOf()

        // initialize the adapter, and pass the required argument
        rvAdapter = MyAdapter(this)

        // attach adapter to the recycler view
        rv.adapter = rvAdapter

        updateProgressBar()

        cardBtn.setOnClickListener {
            if (position < mainData.size && total <= 10000) {
                data.add(0,mainData[position])
                getPercentageIncrease(mainData[position].money)
                updateAccountBalance(mainData[position].money)
                position++
            }

            rvAdapter.addMoreList(data)
            rvAdapter.notifyDataSetChanged()

        }

        bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {

                R.id.homeFragment -> {

                }
                R.id.walletFragment -> {
                    val intent2 = Intent(this@MainActivity, MainActivity::class.java)
                    startActivity(intent2)
                }

                R.id.cardFragment -> {
                    val intent1 = Intent(this@MainActivity, MainActivity::class.java)
                    startActivity(intent1)
                }
                R.id.settingsFragment -> {
                    val intent3 = Intent(this@MainActivity, MainActivity::class.java)
                    startActivity(intent3)
                }
            }
            false
        }
    }

    private fun updateProgressBar() {
        progrexLevel.progress = progr
//        totalFee.text = String.valueOf(progr)
    }

    private fun updateAccountBalance(amount: Double){
        total += amount
        totalFee.text = resources.getString(R.string.total, total.toString())
        Toast.makeText(this,"$total",Toast.LENGTH_SHORT).show()
    }

    private fun getPercentageIncrease(amount: Double) {
        val percentage = (amount/10000)*100
        progr += percentage.toInt()
            updateProgressBar()
    }
}