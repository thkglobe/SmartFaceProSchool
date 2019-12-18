package com.app.facepro.faceproschool.ui.reset

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
import com.app.facepro.faceproschool.databinding.FragmentResetPasswordBinding
import com.app.facepro.faceproschool.ui.profile.ProfileViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ResetPasswordFragment : Fragment() {
    private val profileViewModel: ProfileViewModel by viewModel()
    private val args:ResetPasswordFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binder = FragmentResetPasswordBinding.inflate(inflater)
        binder.apply {
            vm=profileViewModel
            callback=resetButtonCallback
            lifecycleOwner=this@ResetPasswordFragment
        }
        return binder.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        profileViewModel.result.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Result.Success<*> -> {
                    findNavController().navigate(R.id.action_resetPasswordFragment_to_navigation_login)
                }
                is Result.Error -> {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.password_update_failed),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is Result.Failure -> {
                    Toast.makeText(requireContext(), it.exceptionMsg, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
    private val resetButtonCallback = {
        profileViewModel.updatePassword(args.userId)
    }
}
