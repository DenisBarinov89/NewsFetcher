package com.example.newsfetcher.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.newsfetcher.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottom_sheet.*
import java.lang.RuntimeException

class ActionBottomDialogFragment : BottomSheetDialogFragment(), View.OnClickListener {

    private var mListener: ItemClickListener? = null

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


    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mListener = if (context is ItemClickListener) {
            context
        }
        else {
            throw RuntimeException(
                context.toString() + "Must Implement ItemClickListener"
            )
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }


    override fun onClick(p0: View?) {

        val checkElementType : Boolean = p0 is TextView
        if (checkElementType) {
            p0 as TextView
            mListener?.onItemClick(p0.text.toString(), dateFrom = etDateFrom.editableText.toString(), dateTo = etDateTo.editableText.toString())
            dismiss()
        } else {
            p0 as Button
            mListener?.onItemClick(p0.text.toString(), dateFrom = etDateFrom.editableText.toString(), dateTo = etDateTo.editableText.toString())
            dismiss()
        }
    }
}