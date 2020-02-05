package com.app.facepro.faceproschool.ui.profile

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.app.facepro.faceproschool.R
import com.app.facepro.faceproschool.common.CircleTransform
import com.app.facepro.faceproschool.common.Result
import com.app.facepro.faceproschool.databinding.FragmentProfileBinding
import com.app.facepro.faceproschool.ui.LoginActivity
import com.nguyenhoanglam.imagepicker.model.Config
import com.nguyenhoanglam.imagepicker.ui.imagepicker.ImagePicker
import com.squareup.picasso.Picasso
import org.koin.android.viewmodel.ext.android.viewModel
import com.nguyenhoanglam.imagepicker.model.Image
import java.io.File


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
        if(!profileViewModel.profilePicture.isNullOrEmpty()) {
            Picasso.get().load(profileViewModel.profilePicture).resize(250, 250)
                .transform(CircleTransform())
                .into(binder.profileImage)
        }
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
        ImagePicker.with(this)                         //  Initialize ImagePicker with activity or fragment context
            .setToolbarColor("#212121")         //  Toolbar color
            .setStatusBarColor("#000000")       //  StatusBar color (works with SDK >= 21  )
            .setToolbarTextColor("#FFFFFF")     //  Toolbar text color (Title and Done button)
            .setToolbarIconColor("#FFFFFF")     //  Toolbar icon color (Back and Camera button)
            .setProgressBarColor("#4CAF50")     //  ProgressBar color
            .setBackgroundColor("#212121")      //  Background color
            .setCameraOnly(false)               //  Camera mode
            .setMultipleMode(false)              //  Select multiple images or single image
            .setFolderMode(true)                //  Folder mode
            .setShowCamera(true)                //  Show camera button
            .setFolderTitle("Albums")           //  Folder title (works with FolderMode = true)
            .setImageTitle("Galleries")         //  Image title (works with FolderMode = false)
            .setDoneTitle("Done")               //  Done button title
            .setLimitMessage("You have reached selection limit")    // Selection limit message
            .setMaxSize(1)                     //  Max images can be selected
            .setSavePath("facepro")         //  Image capture folder name //  Selected images
            .setAlwaysShowDoneButton(true)      //  Set always show done button in multiple mode
            .setRequestCode(100)                //  Set request code, default Config.RC_PICK_IMAGES
            .setKeepScreenOn(true)              //  Keep screen on when selecting images
            .start()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Config.RC_PICK_IMAGES && resultCode == RESULT_OK && data != null) {
              val images = data.extras?.getParcelableArrayList<Image>(Config.EXTRA_IMAGES)
            val path = images?.get(0)?.path
            val file = File(path)
            profileViewModel.profilePictureUpload(file)
            Picasso.get().load(file).resize(250, 250)
                    .transform(CircleTransform())
                    .into(binder.profileImage)
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

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu?.findItem(R.id.menu_logout)?.isVisible = isLogoutVisible
    }
}
