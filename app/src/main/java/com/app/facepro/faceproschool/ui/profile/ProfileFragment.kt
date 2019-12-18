package com.app.facepro.faceproschool.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.app.facepro.faceproschool.databinding.FragmentProfileBinding
import org.koin.android.viewmodel.ext.android.viewModel
import android.content.Intent
import android.app.Activity
import android.view.*
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.app.facepro.faceproschool.R
import com.app.facepro.faceproschool.common.CircleTransform
import com.app.facepro.faceproschool.common.Result
import com.app.facepro.faceproschool.ui.LoginActivity
import com.squareup.picasso.Picasso
import com.thkglobe.app.facepro.common.MenuVisibility


class ProfileFragment : Fragment() {
    private val profileViewModel: ProfileViewModel by viewModel()
    private lateinit var binder: FragmentProfileBinding
    private var isLogoutVisible: Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binder = FragmentProfileBinding.inflate(inflater)
        binder.apply {
            vm = profileViewModel
            onClickListener = profileOnClickListener
            lifecycleOwner = this@ProfileFragment
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
                        getString(R.string.profile_updated_succssfully),
                        Toast.LENGTH_SHORT
                    ).show()

                }
                is Result.Error -> {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.profile_update_failed),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is Result.Failure -> {
                    Toast.makeText(requireContext(), it.exceptionMsg, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private val profileOnClickListener = object : ProfileClickListener {
        override fun changePasswordOnClick() {
            findNavController().navigate(R.id.action_navigation_profile_to_changePasswordFragment)
        }

        override fun submitOnClick() {
            profileViewModel.updateProfileData()
        }

        override fun editOnClick() {
            profileViewModel.isEditProfile.value = true
        }

        override fun profileImageOnClick() {
            pickImage()
        }
    }

    fun pickImage() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent, PICK_PHOTO_FOR_AVATAR)
    }

    companion object {
        private const val PICK_PHOTO_FOR_AVATAR = 23
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_PHOTO_FOR_AVATAR && resultCode == Activity.RESULT_OK) {
            if (data != null && data.data != null) {
                Picasso.get().load(data.data!!).resize(250, 250)
                    .transform(CircleTransform())
                    .into(binder.profileImage)
            }
        }
    }

    override
    fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_logout -> {
                requireActivity().apply {
                    profileViewModel.clearPreferenceData()
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        isLogoutVisible = false
        requireActivity().invalidateOptionsMenu()
    }

    override fun onPrepareOptionsMenu(menu: Menu?) {
        menu?.findItem(R.id.menu_logout)?.isVisible = isLogoutVisible
    }
}
