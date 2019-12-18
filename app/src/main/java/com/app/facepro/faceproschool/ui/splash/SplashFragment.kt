package com.app.facepro.faceproschool.ui.splash

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.app.facepro.faceproschool.R
import com.app.facepro.faceproschool.common.PreferenceConstants.Companion.APP_LOGIN
import com.app.facepro.faceproschool.common.PreferenceManager
import com.app.facepro.faceproschool.databinding.FragmentSpalshBinding
import com.app.facepro.faceproschool.ui.MainActivity
import com.app.facepro.faceproschool.ui.SharedViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.thkglobe.app.facepro.common.MenuVisibility
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.sharedViewModel

class SplashFragment : Fragment() {
    private val preferenceManager by inject<PreferenceManager>()
    private val sharedViewModel: SharedViewModel by sharedViewModel()
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        sharedViewModel.visibleToolbar(MenuVisibility.NO_MENU)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentSpalshBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_spalsh, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    return@OnCompleteListener
                }

                // Get new Instance ID token
                val token = task.result?.token
                GlobalScope.launch(context = Dispatchers.Main) {
                    delay(1000)
                    if (preferenceManager.getFromPreference(APP_LOGIN, false)) {
                        requireActivity().apply {
                           startActivity(MainActivity.getIntent(this))
                            finish()
                        }
                    } else {
                        val navLogin =
                            SplashFragmentDirections.actionSplashFragmentToNavigationLogin(token)
                        findNavController().navigate(navLogin)
                    }
                }

            })

    }
}
