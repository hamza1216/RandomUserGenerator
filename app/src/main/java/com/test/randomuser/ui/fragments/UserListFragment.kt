package com.test.randomuser.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kaopiz.kprogresshud.KProgressHUD
import com.test.randomuser.R
import com.test.randomuser.data.model.User
import com.test.randomuser.databinding.FragmentUsersBinding
import com.test.randomuser.ui.UserDetailActivity
import com.test.randomuser.ui.adapter.UserRecyclerAdapter
import com.test.randomuser.viewmodel.UsersViewModel

/**
 * A fragment representing a list of clients.
 */
class UserListFragment : Fragment() {

    private lateinit var adapter: UserRecyclerAdapter

    private lateinit var binding: FragmentUsersBinding

    private var hud: KProgressHUD? = null

    lateinit var viewModel: UsersViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentUsersBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(UsersViewModel::class.java)
        viewModel.loading.observe(viewLifecycleOwner, isViewLoadingObserver)
        viewModel.message.observe(viewLifecycleOwner, onMessageErrorObserver)
        viewModel.users.observe(viewLifecycleOwner, renderUsers)

        loadUsers()

        adapter = UserRecyclerAdapter(binding.root.context, viewModel.users.value ?: emptyList())
        binding.list.adapter = adapter
        adapter.setOnItemClickListener(object : UserRecyclerAdapter.OnClickListener {
            override fun onItemClick(v: View, position: Int) {
                val item = adapter.getItem(position)
                UserDetailActivity.startActivity(activity, item)
            }
        })

        return binding.root
    }

    fun loadUsers() {
        viewModel.fetchUsers(10)
    }

    private val renderUsers = Observer<List<User>> {
        adapter.update(it)
    }

    private val isViewLoadingObserver = Observer<Boolean> {
        if (it) {
            hud = KProgressHUD.create(activity)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(false)
                .setAnimationSpeed(2)

            hud?.show()
        } else {
            hud?.dismiss()
        }
    }

    private val onMessageErrorObserver = Observer<String> {
        val toast = Toast.makeText(activity, it, Toast.LENGTH_LONG)
        toast.show()
    }

    companion object {

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance() =
            UserListFragment().apply {

            }
    }
}