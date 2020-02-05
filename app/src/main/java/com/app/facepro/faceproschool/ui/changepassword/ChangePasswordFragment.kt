package com.app.facepro.faceproschool.ui.changepassword

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.app.facepro.faceproschool.R
import com.app.facepro.faceproschool.common.Result
import com.app.facepro.faceproschool.databinding.FragmentResetPasswordBinding
import com.app.facepro.faceproschool.ui.SharedViewModel
import com.app.facepro.faceproschool.ui.profile.ProfileViewModel
import com.thkglobe.app.facepro.common.MenuVisibility
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ChangePasswordFragment : Fragment() {
    private val profileViewModel: ProfileViewModel by viewModel()
    private val sharedViewModel: SharedViewModel by sharedViewModel()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        sharedViewModel.visibleToolbar(MenuVisibility.TOOL_BAR)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binder = FragmentResetPasswordBinding.inflate(inflater)
        binder.apply {
            vm = profileViewModel
            callback = resetButtonCallback
            lifecycleOwner = this@ChangePasswordFragment
        }
        return binder.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        profileViewModel.result.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Result.Success<*> -> {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.password_updated),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is Result.Error -> {
                    Toast.makeText(
                        requireContext(),
                        it.error,
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
        profileViewModel.resetPasswordPassword()
    }

    override
    fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                requireActivity().onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu?.findItem(R.id.menu_notification)?.isVisible = false
    }

}
