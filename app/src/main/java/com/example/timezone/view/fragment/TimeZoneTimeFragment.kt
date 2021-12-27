package com.example.timezone.view.fragment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.timezone.R
import android.provider.Settings.System.DATE_FORMAT
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import android.provider.Settings.System.DATE_FORMAT
import android.util.Log
import android.widget.TextView
import com.example.timezone.helper.gone
import com.example.timezone.helper.visible
import kotlinx.android.synthetic.main.fragment_time_zone_time.*
import java.text.SimpleDateFormat
import java.util.*


class TimeZoneTimeFragment : Fragment() {
    private val DATE_FORMAT = "dd-M-yyyy hh:mm:ss a"
    private val DATE_FORMAT_2 = "hh:mm a"


    private val INDIA_TIME_ZONE = "Asia/Calcutta"
    private val COLORADO_TIME_ZONE = "America/Denver"
    private val SINGAPORE_TIME_ZONE = "Asia/Singapore"
    private val TAIWAN_TIME_ZONE = "Asia/Taipei"
    private val CZECH_REPUBLIC_TIME_ZONE = "Europe/Prague"
    private val HOUSTON_TIME_ZONE = "America/Chicago"

    private var timeZone: String? = null
    private var dateTimeString: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_time_zone_time, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        constraintLay2_tz.gone()
        okBtn.gone()

        selectTimeTv.setOnClickListener {
            selectTime()
        }

        selectTimeZoneTv.setOnClickListener {
            selectTimeZone()
        }

        okBtn.setOnClickListener {


            IndiaTTv_tz.text = convertZoneTime2(dateTimeString!!,timeZone!!,INDIA_TIME_ZONE)
            coloradoTTv_tz.text = convertZoneTime2(dateTimeString!!,timeZone!!,COLORADO_TIME_ZONE)
            singaporeTTv_tz.text = convertZoneTime2(dateTimeString!!,timeZone!!,SINGAPORE_TIME_ZONE)
            taiwanTTv_tz.text = convertZoneTime2(dateTimeString!!,timeZone!!,TAIWAN_TIME_ZONE)
            houstonTTv_tz.text = convertZoneTime2(dateTimeString!!,timeZone!!,HOUSTON_TIME_ZONE)
            czechRepublicTTv_tz.text = convertZoneTime2(dateTimeString!!,timeZone!!,CZECH_REPUBLIC_TIME_ZONE)

            constraintLay2_tz.visibility = View.VISIBLE

        }

    }

    private fun convertZoneTime2(dateTime:String, fromTimeZone:String, toTimeZone:String):String{

        val formatter = SimpleDateFormat(DATE_FORMAT)
        val tzFrom = TimeZone.getTimeZone(fromTimeZone)
        formatter.timeZone = tzFrom
        val date: Date = formatter.parse(dateTime)
        Log.e("TimeZone",tzFrom.displayName)

        val formatter2 = SimpleDateFormat(DATE_FORMAT_2)
        val tzTo = TimeZone.getTimeZone(toTimeZone)
        formatter2.timeZone = tzTo
        Log.e("TimeZone",tzTo.displayName)

        val calendar: Calendar = GregorianCalendar()
        calendar.time = date
        calendar.timeZone = tzTo

        val newTime = formatter2.format(calendar.getTime())

        return newTime
    }

    private fun selectTimeZone() {
        val options =
            arrayOf<CharSequence>("India", "Colorado", "Singapore", "Taiwan", "Czech Republic", "Houston")
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireActivity())
        builder.setTitle("Select time zone")
        builder.setItems(options, DialogInterface.OnClickListener { dialog, item ->
            when (options[item]) {
                "India" -> {
                    timeZone = INDIA_TIME_ZONE
                    selectTimeZoneTv.text = INDIA_TIME_ZONE
                    checkForOkBtn(dateTimeString,timeZone)

                }
                "Colorado" -> {
                    timeZone = COLORADO_TIME_ZONE
                    selectTimeZoneTv.text = COLORADO_TIME_ZONE
                    checkForOkBtn(dateTimeString,timeZone)

                }
                "Singapore" -> {
                    timeZone = SINGAPORE_TIME_ZONE
                    selectTimeZoneTv.text = SINGAPORE_TIME_ZONE
                    checkForOkBtn(dateTimeString,timeZone)

                }
                "Taiwan" -> {
                    timeZone = TAIWAN_TIME_ZONE
                    selectTimeZoneTv.text = TAIWAN_TIME_ZONE
                    checkForOkBtn(dateTimeString,timeZone)

                }
                "Czech Republic" -> {
                    timeZone = CZECH_REPUBLIC_TIME_ZONE
                    selectTimeZoneTv.text = CZECH_REPUBLIC_TIME_ZONE
                    checkForOkBtn(dateTimeString,timeZone)

                }
                "Houston" -> {
                    timeZone = HOUSTON_TIME_ZONE
                    selectTimeZoneTv.text = HOUSTON_TIME_ZONE

                    checkForOkBtn(dateTimeString,timeZone)
                }
            }
        })
        builder.show()
    }

    fun selectTime(){
        var am_pm = ""
        val cal = Calendar.getInstance()

        val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)

            if (cal.get(Calendar.AM_PM) == Calendar.AM)
                am_pm = "AM";
            else if (cal.get(Calendar.AM_PM) == Calendar.PM)
                am_pm = "PM";

            //val time2 = SimpleDateFormat("HH:mm:ss").format(cal.time)
            val strHrsToShow =
                if (cal.get(Calendar.HOUR) === 0) "12" else cal.get(Calendar.HOUR)
                    .toString() + ""

            val date = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())

            val time = strHrsToShow+":"+cal.get(Calendar.MINUTE)+":"+cal.get(Calendar.SECOND)+" "+am_pm

            dateTimeString = date+" "+time
            selectTimeTv.text = time+"   "

            checkForOkBtn(dateTimeString,timeZone)
        }

        selectTimeTv.setOnClickListener {
            TimePickerDialog(requireActivity(), timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), false).show()
        }
    }

    private fun checkForOkBtn(dateTime: String?, timeZoneString: String?){
        if(dateTime != null && timeZoneString != null){
            okBtn.visible()
        }else{
            okBtn.gone()
        }
    }
}