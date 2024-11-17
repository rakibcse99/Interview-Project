package com.rakibcse99.gozayaanui.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback

import androidx.fragment.app.Fragment

import androidx.viewbinding.ViewBinding

import com.faisal.quc.core.listener.LoaderController

import com.rakibcse99.gozayaanui.R


abstract class BaseFragment<Vb:ViewBinding> : Fragment() , LoaderController {


    private  var loaderDialog: Dialog?=null
   // private var loaderDialog: AlertDialog? = null
    private var _binding: Vb? = null
    val binding: Vb get() = _binding!!
         // setter

    abstract fun getViewBinding(inflater: LayoutInflater): Vb
    protected open fun setupObserver() {}
    protected open fun initializeData() {}
    protected open fun callInitialApi() {}

     protected open fun setListener() {}
     protected open fun setUpRecycleView() {}
    protected open fun backPressButtonPressed() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                backPressButtonPressed()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = getViewBinding(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeData()

        setUpRecycleView()

        setListener()

        setupObserver()

        callInitialApi()


    }








    override fun showLoader() {

        loaderDialog = context?.let { Dialog(it) }

        loaderDialog?.requestWindowFeature(1)
        loaderDialog?.setContentView(R.layout.layout_loader)
        loaderDialog?.setCanceledOnTouchOutside(false)
        loaderDialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        loaderDialog?.show()

        loaderDialog?.findViewById<View>(android.R.id.content)?.postDelayed({
            loaderDialog?.dismiss()
        }, 1000)


    }

    override fun hideLoader() {
        if (loaderDialog == null) {
            return
        }
        loaderDialog?.hide()
        loaderDialog?.dismiss()
        loaderDialog?.cancel()
        loaderDialog=null
    }






}