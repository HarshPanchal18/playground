package com.example.playground.activities.flexiblefragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import com.example.playground.R
import kotlinx.android.synthetic.main.fragment_category.*
import kotlinx.android.synthetic.main.fragment_detail_category.*

class CategoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_detail_category.setOnClickListener {
            val mDetailCategoryFragment=DetailCategoryFragment()
            val mBundle=Bundle()
            mBundle.putString(DetailCategoryFragment.EXTRA_NAME,"LifeStyle")
            val description="This category will contain lifestyle products"

            mDetailCategoryFragment.arguments=mBundle
            mDetailCategoryFragment.description=description

            val mFragmentManager=parentFragmentManager
            mFragmentManager.commit {
                addToBackStack(null)
                replace(R.id.frame_container,mDetailCategoryFragment,DetailCategoryFragment::class.java.simpleName)
            }
        }
    }
}
