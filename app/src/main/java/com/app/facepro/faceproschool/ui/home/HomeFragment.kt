package com.app.facepro.faceproschool.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.app.facepro.faceproschool.R
import com.app.facepro.faceproschool.common.Result
import com.app.facepro.faceproschool.databinding.FragmentHomeBinding
import com.app.facepro.faceproschool.ui.SharedViewModel
import com.app.facepro.faceproschool.ui.home.model.HomeResponse
import com.thkglobe.app.facepro.common.MenuVisibility
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private  val homeViewModel: HomeViewModel by viewModel()
    private val sharedViewModel: SharedViewModel by sharedViewModel()
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        sharedViewModel.visibleToolbar(MenuVisibility.BOTH)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if ( requireActivity().intent?.extras!=null) {
            findNavController().navigate(R.id.navigation_notifications)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binder = FragmentHomeBinding.inflate(inflater)
        binder.apply {
            this.vm = homeViewModel
            lifecycleOwner = this@HomeFragment
        }
        return binder.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        homeViewModel.countUpdateItem.observe(viewLifecycleOwner, Observer {count->
                    sharedViewModel.notificationCount(""+count)
        })
        homeViewModel.fetchHomeData()
    }

}
