package com.example.test_task_chi.screens.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test_task_chi.databinding.ItemUserBinding
import com.example.test_task_chi.screens.entity.User

class UserAdapter : RecyclerView.Adapter<UserAdapter.VH>() {
    private val usersList: MutableList<User> = mutableListOf()
    var onItemClick: ((User) -> Unit)? = null
    var onSwitcherChange: ((Long, Boolean) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val user = usersList[position]
        with(holder.binding) {
            tvUserName.text = user.name
            tvAge.text = user.age.toString()
            switcher.isChecked = user.isStudent

            root.setOnClickListener { onItemClick?.invoke(user) }

            switcher.setOnCheckedChangeListener { _, isChecked ->
                onSwitcherChange?.invoke(
                    user.id,
                    isChecked
                )
            }
        }
    }

    override fun getItemCount(): Int = usersList.size

    fun updateItems(newUserList: List<User>) {
        usersList.clear()
        usersList.addAll(newUserList)
        notifyDataSetChanged()
    }

    class VH(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)
}