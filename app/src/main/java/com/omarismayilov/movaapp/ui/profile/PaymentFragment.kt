package com.omarismayilov.movaapp.ui.profile

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.omarismayilov.movaapp.common.base.BaseFragment
import com.omarismayilov.movaapp.databinding.FragmentPaymentBinding


class PaymentFragment : BaseFragment<FragmentPaymentBinding>(FragmentPaymentBinding::inflate) {

    override fun observeEvents() {

    }

    override fun onCreateFinish() {

    }

    override fun setupListeners() {
        with(binding){
            btnContinue.setOnClickListener {
                val builder = AlertDialog.Builder(requireContext())
                val customLayout: View = layoutInflater.inflate(com.omarismayilov.movaapp.R.layout.dialog_congrat, null)
                builder.setView(customLayout)
                val dialog: AlertDialog = builder.create()
                dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dialog.show()
            }
            btnAddNew.setOnClickListener {
                findNavController().navigate(PaymentFragmentDirections.actionPaymentFragmentToAddCardFragment())
            }
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }


}