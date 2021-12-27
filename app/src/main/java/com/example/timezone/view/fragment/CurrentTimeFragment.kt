package com.example.timezone.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.timezone.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_current_time.*
import java.util.*

class CurrentTimeFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_current_time, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTime()

        compareTimeClickTv.setOnClickListener {
            findNavController().navigate(R.id.action_currentTimeFragment_to_timeZoneTimeFragment)
        }
    }
    private fun setTime(){
        indiaTTv.text = getCurrentTime("GMT+05:30")
        coloradoTTv.text = getCurrentTime("GMT-7")
        singaporeTTv.text = getCurrentTime("GMT+8")
        taiwanTTv.text = getCurrentTime("GMT+8")
        czechRepublicTTv.text = getCurrentTime("GMT+1")
        houstonTTv.text = getCurrentTime("GMT-6")

    }
    private fun getCurrentTime(timeZone: String): String{
        val tz: TimeZone = TimeZone.getTimeZone(timeZone)
        val c: Calendar = Calendar.getInstance(tz)
        return String.format("%02d" , c.get(Calendar.HOUR_OF_DAY))+":"+
                String.format("%02d" , c.get(Calendar.MINUTE))
    }
}