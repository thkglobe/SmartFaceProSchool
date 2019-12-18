package com.app.facepro.faceproschool.ui.otp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.app.facepro.faceproschool.R
import com.app.facepro.faceproschool.common.Result
import com.app.facepro.faceproschool.databinding.FragmentOtpBinding
import com.app.facepro.faceproschool.ui.MainActivity
import com.app.facepro.faceproschool.ui.reset.ResetPasswordFragmentDirections
import org.koin.android.viewmodel.ext.android.viewModel

class OTPLoginFragment : Fragment() {
    private val otpViewModel:OtpViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binder = FragmentOtpBinding.inflate(inflater)
        binder.apply {
            this.vm = otpViewModel
            this.onClickListener = listener
            lifecycleOwner = this@OTPLoginFragment
        }

        return binder.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        otpViewModel.otpValidateResult.observe(viewLifecycleOwner, Observer { isValidate->
            if(isValidate){
                val userIdNav =
                    OTPLoginFragmentDirections.actionOTPLoginFragmentToResetPasswordFragment(
                        otpViewModel.phoneNumber.value
                    )
                findNavController().navigate(userIdNav)
            }else{
                Toast.makeText(requireContext(),"Wrong OTP", Toast.LENGTH_SHORT).show()

            }
        })
        otpViewModel.otpSendResult.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Result.Error -> {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.error_send_to_phone),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is Result.Failure -> {
                    Toast.makeText(requireContext(), it.exceptionMsg, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private val listener = object : OtpClickListener {
        override fun validateOTPOnClick() {
            otpViewModel.validateOtp()
        }
        override fun sendOTPOnClick() {
            otpViewModel.sendOtp()
        }

    }
}
