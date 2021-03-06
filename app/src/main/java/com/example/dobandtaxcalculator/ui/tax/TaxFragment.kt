package com.example.dobandtaxcalculator.ui.tax

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.dobandtaxcalculator.BR
import com.example.dobandtaxcalculator.R
import com.example.dobandtaxcalculator.base.BaseFragment
import com.example.dobandtaxcalculator.databinding.TaxFragmentBinding
import kotlinx.android.synthetic.main.tax_fragment.*
import java.io.File

class TaxFragment : BaseFragment<TaxFragmentBinding, TaxViewModel>() {
    override fun getViewModel(): TaxViewModel? =
        ViewModelProvider(this).get(TaxViewModel::class.java)

    override fun getBindingVariable(): Int = BR.taxViewModel

    override fun getContentView(): Int = R.layout.tax_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getViewModel()?.getImageList()?.observe(viewLifecycleOwner, {
            it?.let {
                if (it.isNotEmpty()) {
                    loadImage(Uri.fromFile(File(it[it.size - 1].imagePath)),imageView)
                    loadImage(Uri.fromFile(File(it[it.size - 1].imagePath)),fadedImageView)
                }
            }
        })
    }

    private fun loadImage(fromFile: Uri, imageView: AppCompatImageView) {
        Glide.with(requireActivity())
            .load(fromFile)
            .into(imageView)
    }
}