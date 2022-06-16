package com.example.newsfetcher.ui

import android.app.DatePickerDialog
import android.content.Context
import android.icu.util.Calendar
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import com.example.newsfetcher.R
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

        etDateFrom.setOnClickListener {
            showDatePicker(it, etDateFrom)
        }

        etDateTo.setOnClickListener {
            showDatePicker(it, etDateTo)
        }
    }

    fun showDatePicker(view: View, editText : EditText) {

        val formatDate = SimpleDateFormat("yyyy-MM-dd")
        val mCalendar = Calendar.getInstance()
        val dateRange = mCalendar

        val mDialog = DatePickerDialog(view.context, android.R.style.Theme_Material_Dialog_MinWidth, { _, mYear, mMonth, mDay ->
            mCalendar[Calendar.YEAR] = mYear
            mCalendar[Calendar.MONTH] = mMonth
            mCalendar[Calendar.DAY_OF_MONTH] = mDay
            val date = formatDate.format(mCalendar.time)
            editText.setText(date)
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
            mListener.onItemClick(p0.text.toString(), dateFrom = etDateFrom.text.toString(), dateTo = etDateTo.text.toString())
            dismiss()
        }
    }
}