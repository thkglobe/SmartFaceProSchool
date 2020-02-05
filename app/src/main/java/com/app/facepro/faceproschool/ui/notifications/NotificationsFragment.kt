package com.app.facepro.faceproschool.ui.notifications

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.facepro.faceproschool.R
import com.app.facepro.faceproschool.common.Result
import com.app.facepro.faceproschool.databinding.FragmentNotificationsBinding
import com.app.facepro.faceproschool.ui.SharedViewModel
import com.app.facepro.faceproschool.ui.notifications.model.NotificationResponse
import com.thkglobe.app.facepro.common.MenuVisibility
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class NotificationsFragment : Fragment() {

    private val viewModel: NotificationsViewModel by viewModel()
    private val sharedViewModel: SharedViewModel by sharedViewModel()
    private lateinit var binder: FragmentNotificationsBinding
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
        binder =
            DataBindingUtil.inflate(inflater, R.layout.fragment_notifications, container, false)
        binder.apply {
            viewModel = viewModel
            lifecycleOwner = this@NotificationsFragment
        }
        return binder.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getNotificationData()
        viewModel.result.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Result.Progress -> {

                }
                is Result.Success<*> -> {
                    val response = it.response as NotificationResponse
                    if (response.notification_list.isNotEmpty()) {
                        val adapter = NotificationAdapter(response.notification_list)
                        val layoutManager = LinearLayoutManager(requireContext())
                        binder?.notificationRecyclerView?.let { recyclerView ->
                            recyclerView.layoutManager = layoutManager
                            recyclerView.itemAnimator = DefaultItemAnimator()
                            recyclerView.adapter = adapter
                        }
                    }
                }
                is Result.Error -> {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.no_notification),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is Result.Failure -> {
                    Toast.makeText(requireContext(), it.exceptionMsg, Toast.LENGTH_SHORT).show()
                }
            }
        })
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

    override fun onDestroyView() {
        super.onDestroyView()
        sharedViewModel.visibleToolbar(MenuVisibility.BOTH)
    }
}
