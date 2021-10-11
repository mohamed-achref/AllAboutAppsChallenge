package at.allaboutapps.app.features.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import at.allaboutapps.app.R
import at.allaboutapps.app.base.BaseFragment
import at.allaboutapps.app.databinding.FragmentMainBinding
import at.allaboutapps.app.networking.model.Club
import at.allaboutapps.app.utils.Utils
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import java.util.stream.Collectors

class MainFragment : BaseFragment(R.layout.fragment_main), MainAdapter.OnClubListener {
    private val binding by viewBinding(FragmentMainBinding::bind)

    lateinit var viewModel: MainViewModel

    private var clubList = ArrayList<Club>()
    private var isFiltered = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setViewModel()
        setUpRecyclerView()
    }

    private fun setViewModel() {
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(
                MainViewModel::class.java
            )
    }

    private fun initRecyclerView(listClub: List<Club>) {
        binding.recyclerView.adapter = MainAdapter(listClub.toMutableList(), this)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerView.context,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun setUpRecyclerView() {
        activity?.let {
            viewModel.clubList.observe(it, { list ->
                clubList = list as ArrayList<Club>
                initRecyclerView(clubList)
            })

            viewModel.errorMessage.observe(it, { error ->
                Utils.showAlert(requireContext(), error)
            })
            viewModel.getAllClubs()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_filter, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_filter) {
            val list: List<Club>
            if (!isFiltered) {
                isFiltered = true

                list = viewModel.clubList.value?.stream()?.sorted { o1, o2 ->
                    o1.value.compareTo(o2.value)
                }?.collect(Collectors.toList()) as List<Club>

                viewModel.clubList.postValue(list)
            } else {
                isFiltered = false

                list = viewModel.clubList.value?.stream()?.sorted { o1, o2 ->
                    o1.name.compareTo(o2.name)
                }?.collect(Collectors.toList()) as List<Club>
            }
            viewModel.clubList.postValue(list)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClubClick(position: Int, view: View?) {
        val action = MainFragmentDirections.actionMainFragmentToClubFragmentFragment(
            keyClub = clubList[position],
            title = clubList[position].name
        )
        view?.findNavController()?.navigate(action)
    }

    override fun onDetach() {
        super.onDetach()
        viewModel.compositeDisposable.clear()
    }
}
