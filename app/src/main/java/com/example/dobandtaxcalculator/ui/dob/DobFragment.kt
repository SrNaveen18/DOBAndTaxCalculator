package com.example.dobandtaxcalculator.ui.dob

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.dobandtaxcalculator.BR
import com.example.dobandtaxcalculator.R
import com.example.dobandtaxcalculator.base.BaseFragment
import com.example.dobandtaxcalculator.databinding.DobFragmentBinding
import kotlinx.android.synthetic.main.tax_fragment.*
import java.io.File

class DobFragment : BaseFragment<DobFragmentBinding, DobViewModel>() {
    override fun getViewModel(): DobViewModel? =
        ViewModelProvider(this).get(DobViewModel::class.java)

    override fun getBindingVariable(): Int = BR.dobViewModel

    override fun getContentView(): Int = R.layout.dob_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getViewModel()?.getImageList()?.observe(viewLifecycleOwner, {
            it?.let {
                if (it.isNotEmpty()) {
                    Glide.with(requireActivity())
                        .load(Uri.fromFile(File(it[it.size - 1].imagePath)))
                        .into(imageView)
                }
            }
        })

        getViewModel()?.dateOfBirth?.observe(viewLifecycleOwner,  {
            getViewModel()?.calculateAge(it)
        })

        getViewModel()?.ageItems?.observe(viewLifecycleOwner,{
            it?.let {
                val years = if(it.years == 1) getString(R.string.lbl_year) else getString(R.string.lbl_years)
                val months = if(it.months == 1) getString(R.string.lbl_month) else getString(R.string.lbl_months)
                val days = if(it.days == 1) getString(R.string.lbl_day) else getString(R.string.lbl_days)
               getViewModel()?.age?.value =  getViewModel()?.createAgeString(it,years,months,days)
            }
        })
    }
}