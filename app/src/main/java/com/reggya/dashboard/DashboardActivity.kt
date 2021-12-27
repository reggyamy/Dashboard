package com.reggya.dashboard

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.PercentFormatter
import com.reggya.dashboard.data.ApiResponseType
import com.reggya.dashboard.data.model.DataItem
import com.reggya.dashboard.databinding.ActivityDashboardBinding
import com.reggya.dashboard.ui.HomeViewModel
import com.reggya.dashboard.ui.ViewModelFactory


class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding
    private var value : ArrayList<Entry>? = ArrayList()
    private var year : String = "2020"
    private var years = arrayOf("2020","2019","2018","2017","2016","2015","2014","2013","2012","2011")
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter_ = ArrayAdapter(this, android.R.layout.simple_spinner_item, years)
        adapter_.setDropDownViewResource(android.R.layout.simple_spinner_item)

        binding.apply {
            spinner1.adapter = adapter_
            spinner1.prompt = years[0]
            spinner1.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent:AdapterView<*>, view: View, position: Int, id: Long){
                    (parent.getChildAt(0) as TextView).setTextColor(Color.WHITE)
                    year = parent.getItemAtPosition(position).toString()
                    setViewModel(year)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    setViewModel("2020")
                }

            }
            spinner2.adapter = adapter_
            spinner2.prompt = years[0]
            spinner2.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent:AdapterView<*>, view: View, position: Int, id: Long){
                    (parent.getChildAt(0) as TextView).setTextColor(Color.WHITE)
                    year = parent.getItemAtPosition(position).toString()
                    setViewModel(year)
                }

                override fun onNothingSelected(parent: AdapterView<*>){
                    setViewModel("2020")
                }
            }
        }

        setUpPieChart()

    }

    private fun setViewModel(year: String) {
        viewModel = ViewModelProvider(this, ViewModelFactory
            .getInstance(this))[HomeViewModel::class.java]
        viewModel.getData(year).observe(this, {
            if (it.type == ApiResponseType.SUCCESS){
                setDataToLineChart(it.data)
                setDataToBarChart(it.data)
            }
        })
    }

    private fun setUpPieChart() {
        binding.pieChart.apply {
            val value = arrayOf(33.11, 49.51, 17.29)
            val color = arrayOf("#01AAC1", "#FFC108", "#435EBE")
            val colors : ArrayList<Int> = ArrayList()

            setUsePercentValues(true)
            isDrawHoleEnabled = false
            isRotationEnabled = true
            description.isEnabled = false
            legend.isEnabled = false

            val dataSet = PieDataSet(
                value.mapIndexed { _, dataItem ->
                    PieEntry(dataItem.toFloat())
                },""
            )

            for (i in 0..2){
                colors.add(Color.parseColor(color[i]))
            }

            data = PieData(dataSet)
            data.setValueFormatter(PercentFormatter(binding.pieChart))
            data.setValueTextColor(Color.WHITE)
            data.setValueTextSize(12f)
            dataSet.colors = colors
            invalidate()
        }
    }

    private fun setDataToBarChart(data: List<DataItem?>?) {
        binding.barChart.apply {
            xAxis.setDrawGridLines(false)
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.textColor = R.color.black

            axisLeft.textColor = R.color.black
            axisRight.isEnabled = false
            description.isEnabled = false
            xAxis.labelCount = 34
            xAxis.gridLineWidth = 1f
            xAxis.setDrawLabels(true)
            xAxis.labelRotationAngle = 90f
            xAxis.valueFormatter = IndexAxisValueFormatter(data?.mapIndexed { index, dataItem ->
                dataItem?.provinsi
            })

            val kemiskinan = BarDataSet(
                data?.mapIndexed { index, dataItem ->
                    val dataKemiskinan = dataItem?.jumlahPendudukMiskin
                    dataKemiskinan?.let {
                        BarEntry(index.toFloat(),
                            it.toFloat())
                    }
                }, "Jumlah Kemiskinan"
            ).apply {
                setUpBarChart(this, R.color.dark_blue)
            }

            binding.barChart.data = BarData(kemiskinan)
            binding.barChart.invalidate()

        }
    }

    private fun setDataToLineChart(data: List<DataItem?>?) {
        with(binding.lineChart) {
            animateX(1000)
            legend.textColor = R.color.black
            xAxis.setDrawGridLines(false)
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.textColor = R.color.black

            axisLeft.textColor = R.color.black
            axisRight.isEnabled = false
            description.isEnabled = false
            xAxis.labelCount = 34
            xAxis.gridLineWidth = 1f
            xAxis.setDrawLabels(true)
            xAxis.labelRotationAngle = 90f
            xAxis.valueFormatter = IndexAxisValueFormatter(data?.mapIndexed { index, dataItem ->
                dataItem?.provinsi
            })
        }

        val kemiskinan = LineDataSet(
            data?.mapIndexed { index, dataItem ->
                val dataKemiskinan = dataItem?.jumlahPendudukMiskin
                dataKemiskinan?.let {
                    Entry(index.toFloat(),
                        it.toFloat())
                }
            }, "Jumlah Kemiskinan"
        ).apply {
            setUpLineChart(this, R.color.dark_blue)
        }

        val bansos = LineDataSet(
            data?.mapIndexed { index, dataItem ->
            val dataBansos = dataItem?.jumlahBansos
                dataBansos?.let {
                    Entry(index.toFloat(), it.toFloat())
                }
            }, "Dana Bansos"
        ).apply {
            setUpLineChart(this, R.color.yellow)
        }

        val pengangguran = LineDataSet(
            data?.mapIndexed { index, dataItem ->
                val dataBansos = dataItem?.jumlahPengangguran
                dataBansos?.let {
                    Entry(index.toFloat(), (it/1000).toFloat())
                }
            }, "Jumlah Pengangguran"
        ).apply {
            setUpLineChart(this, R.color.teal_700)
        }

        binding.lineChart.data = LineData(kemiskinan, pengangguran, bansos)
        binding.lineChart.invalidate()
    }

    private fun setUpBarChart(barDataSet: BarDataSet, colorRes: Int) {
        barDataSet.apply {
            color = resources.getInteger(colorRes)
        }
    }

    private fun setUpLineChart(lineDataSet: LineDataSet, colorRes: Int) {
        lineDataSet.apply {
            color = resources.getInteger(colorRes)
            lineWidth = 3f
            setDrawCircleHole(false)
            setDrawCircles(true)
            setCircleColor(resources.getInteger(colorRes))

        }
    }

}


