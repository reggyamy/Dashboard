package com.reggya.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.ColorRes
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.reggya.dashboard.data.ApiResponseType
import com.reggya.dashboard.databinding.ActivityDashboardBinding
import com.reggya.dashboard.databinding.ActivityMainBinding
import com.reggya.dashboard.ui.HomeViewModel
import com.reggya.dashboard.ui.ViewModelFactory

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding
    private var label : ArrayList<CovidResponseItem> = ArrayList()
    private var variable : ArrayList<String> = ArrayList()
    private var value : ArrayList<Entry>? = ArrayList()
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        setViewModel()
        initLineChart()

    }

//    private fun setViewModel() {
//        viewModel = ViewModelProvider(this, ViewModelFactory
//            .getInstance(this))[HomeViewModel::class.java]
//        viewModel.getData.observe(this, {
//            if (it.type == ApiResponseType.SUCCESS){
//                setDataToLineChart(it.data)
//
//            }
//
//        })
//    }

    private fun initLineChart() {
        binding.lineChart.apply {
            axisLeft.setDrawGridLines(false)
            xAxis.setDrawGridLines(false)
            xAxis.setDrawAxisLine(false)

            axisRight.isEnabled = false
            legend.isEnabled = true
            description.isEnabled = false

            animateX(1000, Easing.EaseInSine)
            xAxis.position = XAxis.XAxisPosition.BOTTOM_INSIDE
            xAxis.valueFormatter = object : IndexAxisValueFormatter(){
                override fun getAxisLabel(value: Float, axis: AxisBase?): String? {
                    val index = value.toInt()
                    return if (index < label.size){
                        label[index].attributes.provinsi
                    }else{
                        ""
                    }
                }
            }
            xAxis.setDrawLabels(true)
            xAxis.granularity = 1f
            xAxis.labelRotationAngle = +90f
        }
    }

    private fun setDataToLineChart(data: List<CovidResponseItem>?) {

        val recovery = LineDataSet(
            data?.mapIndexed { index, covidResponseItem ->
                val recovery_ = covidResponseItem.attributes.kasusSembub
                recovery_?.let {
                    Entry(index.toFloat(),
                        it.toFloat())
                }
            }, "Recovery"
        ).apply {
            setUpLineChart(this, R.color.black)
        }

        val death = LineDataSet(
            data?.mapIndexed { index, covidResponseItem ->
                val recovery_ = covidResponseItem.attributes.kasusMeninggal
                recovery_?.let {
                    Entry(index.toFloat(),
                        it.toFloat())
                }
            }, "Recovery"
        ).apply {
            setUpLineChart(this, R.color.dark_teal)
        }

        binding.lineChart.data = LineData(recovery, death)
        binding.lineChart.invalidate()
    }

    private fun setUpLineChart(lineDataSet: LineDataSet, @ColorRes colorRes: Int) {
        lineDataSet.apply {
            color = getColor(colorRes)
            lineWidth = 3f
            setDrawCircleHole(false)
            setCircleColor(getColor(colorRes))
            valueTextColor = R.color.black

        }
    }

}


