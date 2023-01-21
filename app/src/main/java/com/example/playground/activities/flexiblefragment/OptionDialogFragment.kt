package com.example.playground.activities.flexiblefragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.playground.R
import kotlinx.android.synthetic.main.fragment_option_dialog.*

class OptionDialogFragment : DialogFragment() {

    private var optionDialogListener: OnOptionDialogListener? = null

    interface OnOptionDialogListener {
        fun onOptionChosen(text: String?)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_option_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_choose.setOnClickListener {
            val checkRadioButtonId=rg_options.checkedRadioButtonId
            if(checkRadioButtonId!=-1){
                var coach:String?=null
                when(checkRadioButtonId){
                    rb_saf.id ->coach=rb_saf.text.toString().trim()
                    rb_mou.id ->coach=rb_mou.text.toString().trim()
                    rb_lvg.id ->coach=rb_lvg.text.toString().trim()
                    rb_moyes.id ->coach=rb_moyes.text.toString().trim()
                }
                optionDialogListener?.onOptionChosen(coach)
                dialog?.dismiss()
            }
        }

        btn_close.setOnClickListener {
            dialog?.cancel()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val fragment = parentFragment

        if (fragment is DetailCategoryFragment) {
            this.optionDialogListener = fragment.optionDialogListener
        }
    }

    override fun onDetach() {
        super.onDetach()
        this.optionDialogListener = null
    }
}
