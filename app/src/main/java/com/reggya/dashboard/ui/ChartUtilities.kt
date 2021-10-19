//package com.reggya.dashboard.ui
//
//import com.github.mikephil.charting.animation.Easing
//import com.github.mikephil.charting.components.AxisBase
//import com.github.mikephil.charting.components.XAxis
//import com.github.mikephil.charting.data.Entry
//import com.github.mikephil.charting.data.LineData
//import com.github.mikephil.charting.data.LineDataSet
//import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
//import com.reggya.dashboard.Attributes
//import com.reggya.dashboard.databinding.ActivityMainBinding
//
//object ChartUtilities {
//
//    private lateinit var binding: ActivityMainBinding
//    private fun setUpLineChart() {
//
//        private fun fillData(list: List<ForecastListItem>, isMetric: Boolean): Triple<ArrayList<Entry>, ArrayList<Double>, ArrayList<String>> {
//            val values = ArrayList<Entry>()
//            val yValues = ArrayList<Double>()
//            val xAxisValues = arrayListOf<String>()
//            list.forEachIndexed { index, forecastItem ->
//                xAxisValues.add(forecastItem.getHourOfDay())
//                var temperature = forecastItem.getTemperature()!!
//                if (!isMetric)
//                    temperature = WeatherUtils.celsiusToFahrenheit(temperature)
//                yValues.add(temperature)
//                values.add(Entry(index.toFloat(), temperature.toFloat()))
//            }
//            return Triple(values, yValues, xAxisValues)
//        }
//
//
////        hide grid lines
//        binding.apply {
//            lineChart.axisLeft.setDrawGridLines(false)
//            val xAxis: XAxis = lineChart.xAxis
//            xAxis.setDrawGridLines(false)
//            xAxis.setDrawAxisLine(false)
//
//            //remove right y-axis
//            lineChart.axisRight.isEnabled = false
//
//            //remove legend
//            lineChart.legend.isEnabled = false
//
//
//            //remove description label
//            lineChart.description.isEnabled = false
//
//
//            //add animation
//            lineChart.animateX(1000, Easing.EaseInSine)
//
//            // to draw label on xAxis
//            xAxis.position = XAxis.XAxisPosition.BOTTOM_INSIDE
//            xAxis.valueFormatter = MyAxisFormatter()
//            xAxis.setDrawLabels(true)
//            xAxis.granularity = 1f
//            xAxis.labelRotationAngle = +90f}
//
//    }
//
//
//    inner class MyAxisFormatter : IndexAxisValueFormatter() {
//
//        override fun getAxisLabel(value: Float, axis: AxisBase?): String {
//            val index = value.toInt()
//            return if (index < data.size) ({
//                data[index].provinsi
//            }).toString() else {
//                ""
//            }
//        }
//    }
//
//    private fun setDataToLineChart(position: Int?) {
//        //now draw bar chart with dynamic data
//        val entries: ArrayList<Entry> = ArrayList()
//
//        data = getDataList(position)
//
//        //you can replace this data object with  your custom object
//        for (i in data.indices) {
//
//            val score = data[i]
//            entries.add(Entry(i.toFloat(), score.kasusSembub!!.toFloat()))
//        }
//
//        val lineDataSet = LineDataSet(entries, "")
//
//        val data = LineData(lineDataSet)
//        binding.lineChart.data = data
//
//        binding.lineChart.invalidate()
//    }
//
//    // simulate api call
//    // we are initialising it directly
//    private fun getDataList(position: Int): ArrayList<Attributes> {
//        val newData = ArrayList<Attributes>()[position]
//        data.add(Attributes(newData.id, newData.kodeProvinsi, newData.kasusMeninggal, newData.kasusPositif, newData.provinsi, newData.kasusSembub))
//
//        return data
//    }
//}