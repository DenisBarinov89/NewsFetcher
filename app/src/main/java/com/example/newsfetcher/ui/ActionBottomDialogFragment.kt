package com.example.newsfetcher.ui

import android.app.DatePickerDialog
import android.content.Context
import android.widget.ArrayAdapter
import android.icu.util.Calendar
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.newsfetcher.R
import com.example.newsfetcher.SORT_LIST
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottom_sheet.*
import java.lang.RuntimeException
import java.text.SimpleDateFormat
import java.util.*

class ActionBottomDialogFragment(private var mListener:ItemClickListener) : BottomSheetDialogFragment(), View.OnClickListener {

        override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvDateAscending.setOnClickListener(this)
        tvPopularity.setOnClickListener(this)
        tvTitleAscending.setOnClickListener(this)
        btnGetFilterResult.setOnClickListener(this)
        tvDateFrom.setOnClickListener {
            showDatePicker(it, tvDateFrom)
        }
        tvDateTo.setOnClickListener {
            showDatePicker(it, tvDateTo)
        }


    }

    private fun showDatePicker(view: View, tvDate : TextView) {
        val formatDate = SimpleDateFormat("yyyy-MM-dd")
        val mCalendar = Calendar.getInstance()
        val dateRange = mCalendar

        val mDialog = DatePickerDialog(view.context, android.R.style.Theme_Light_Panel, { _, mYear, mMonth, mDay ->
            mCalendar[Calendar.YEAR] = mYear
            mCalendar[Calendar.MONTH] = mMonth
            mCalendar[Calendar.DAY_OF_MONTH] = mDay
            val date = formatDate.format(mCalendar.time)
            tvDate.text = date
        }, mCalendar[Calendar.YEAR], mCalendar[Calendar.MONTH], mCalendar[Calendar.DAY_OF_MONTH])

        dateRange.set(mCalendar.get(Calendar.YEAR),mCalendar.get(Calendar.MONTH),mCalendar.get(Calendar.DAY_OF_MONTH) - 30)
        mDialog.datePicker.minDate = mCalendar.timeInMillis

        dateRange.set(mCalendar.get(Calendar.YEAR),mCalendar.get(Calendar.MONTH),mCalendar.get(Calendar.DAY_OF_MONTH) + 30)
        mDialog.datePicker.maxDate = mCalendar.timeInMillis

        mDialog.show()
    }

    override fun onClick(p0: View?) {
        val checkElementType : Boolean = p0 is Button
        if (!checkElementType) {
            p0 as TextView
            mListener.onItemClick(p0.text.toString(), null, null)
            dismiss()
        } else {
            p0 as Button
            mListener.onItemClick(p0.text.toString(), dateFrom = tvDateFrom.text.toString(), dateTo = tvDateTo.text.toString())
            dismiss()
        }
    }
}