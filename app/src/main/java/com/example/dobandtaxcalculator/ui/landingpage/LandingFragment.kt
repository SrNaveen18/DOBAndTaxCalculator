package com.example.dobandtaxcalculator.ui.landingpage

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.dobandtaxcalculator.BR
import com.example.dobandtaxcalculator.R
import com.example.dobandtaxcalculator.base.BaseFragment
import com.example.dobandtaxcalculator.databinding.LandingFragmentBinding
import com.example.dobandtaxcalculator.extension.isNetConnected
import kotlinx.android.synthetic.main.landing_fragment.*

class LandingFragment : BaseFragment<LandingFragmentBinding, LandingViewModel>() {

    override fun getViewModel(): LandingViewModel? =
        ViewModelProvider(this).get(LandingViewModel::class.java)

    override fun getBindingVariable(): Int = BR.landingVM

    override fun getContentView(): Int = R.layout.landing_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkInternetAvailable()
        btnTax.setOnClickListener {
            val action = LandingFragmentDirections.actionLandingFragmentToTaxFragment2()
            findNavController().navigate(action)
        }

        btnDob.setOnClickListener {
            val action = LandingFragmentDirections.actionLandingFragmentToDobFragment()
            findNavController().navigate(action)
        }
    }


    private fun checkInternetAvailable(): Boolean {
        val hasInternet = requireActivity().isNetConnected()
        return if (!hasInternet) {
            showMessageAlert(getString(R.string.error_no_internet))
            false
        } else {
            true
        }
    }

    private fun showMessageAlert(message: String) {
        val dialog = AlertDialog.Builder(requireActivity())
        dialog.setMessage(message)
        dialog.setCancelable(false)
        dialog.setPositiveButton("Close") { dialog, _ ->
            requireActivity().finish()
            dialog?.dismiss()
        }
        dialog.create().show()
    }
}