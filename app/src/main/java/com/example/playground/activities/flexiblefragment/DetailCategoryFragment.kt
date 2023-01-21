package com.example.playground.activities.flexiblefragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.playground.R
import kotlinx.android.synthetic.main.fragment_detail_category.*

class DetailCategoryFragment : Fragment() {

    var description: String? = null

    companion object {
        var EXTRA_NAME = "extra_name"
        var EXTRA_DESCRIPTION = "extra_description"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_show_dialog.setOnClickListener {
            val mOptionDialogFragment=OptionDialogFragment()
            val mFragmentManager=childFragmentManager
            mOptionDialogFragment.show(mFragmentManager,OptionDialogFragment::class.java.simpleName)
        }

        btn_profile.setOnClickListener {
            startActivity(Intent(activity,ProfileActivity::class.java))
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        savedInstanceState.let {
            val descFromBundle = savedInstanceState?.getString(EXTRA_DESCRIPTION)
            description = descFromBundle
        }

        arguments?.let {
            val categoryName=arguments?.getString(EXTRA_NAME)
            tv_category_name.text=categoryName
            tv_category_description.text=description
        }
    }

    internal var optionDialogListener:OptionDialogFragment.OnOptionDialogListener =
        object:OptionDialogFragment.OnOptionDialogListener {
            override fun onOptionChosen(text:String?){
                Toast.makeText(activity,text,Toast.LENGTH_SHORT).show()
            }
        }
}
