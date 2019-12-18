package com.app.facepro.faceproschool.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.app.facepro.faceproschool.R
import com.app.facepro.faceproschool.common.Result
import com.app.facepro.faceproschool.databinding.FragmentLoginBinding
import com.app.facepro.faceproschool.ui.MainActivity
import org.koin.android.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {
    private val loginViewModel: LoginViewModel by viewModel()
    val args: LoginFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentLoginBinding = FragmentLoginBinding.inflate(inflater)
        binding.callback = onLoginClick
        binding.viewmodel = loginViewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        loginViewModel.loginResult.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Result.Success<*> -> {
                    requireActivity().apply {
                        startActivity(MainActivity.getIntent(this))
                        finish()
                    }
                }
                is Result.Error -> {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.login_failed),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is Result.Failure -> {
                    Toast.makeText(requireContext(), it.exceptionMsg, Toast.LENGTH_SHORT).show()
                }
            }
        })

    }

    private val onLoginClick = object : LoginClickListener {
        override fun onForgetPassword() {
            findNavController().navigate(R.id.action_navigation_login_to_OTPLoginFragment)
        }

        override fun onLoginClick() {
            loginViewModel.login(args.firebaseToken)
        }

        override fun onRegisterClick() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onOTPLoginClick() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }


}
