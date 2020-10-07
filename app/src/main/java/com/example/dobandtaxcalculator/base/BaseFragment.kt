package com.example.dobandtaxcalculator.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.databinding.ViewDataBinding

abstract class BaseFragment<out V : ViewDataBinding, out T : BaseViewModel> : Fragment() {

    private var mDataBinding: V? = null
    private var mViewModel: T? = null
    abstract fun getViewModel(): T?

    abstract fun getBindingVariable(): Int

    abstract fun getContentView(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mDataBinding = DataBindingUtil.inflate(inflater, getContentView(), container, false)
        return mDataBinding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        performDataBinding()
    }

    private fun performDataBinding() {
        getViewModel()?.let { viewModel ->
            mViewModel = viewModel
            mDataBinding?.setVariable(getBindingVariable(), mViewModel)
            mDataBinding?.lifecycleOwner = this
            mDataBinding?.executePendingBindings()
        }
    }
}