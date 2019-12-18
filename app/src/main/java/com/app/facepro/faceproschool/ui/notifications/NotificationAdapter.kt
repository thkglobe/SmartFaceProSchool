package com.app.facepro.faceproschool.ui.notifications

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.facepro.faceproschool.R
import com.app.facepro.faceproschool.databinding.ItemNotificationBinding
import com.app.facepro.faceproschool.ui.notifications.model.ActivityData


class NotificationAdapter(private val notificatios: List<ActivityData>) :
    RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemNotificationBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_notification, parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return notificatios.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(notificatios[position])
    }


    class ViewHolder(private val binding: ItemNotificationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ActivityData) {
            binding.model = data
            binding.executePendingBindings()
        }
    }

}
