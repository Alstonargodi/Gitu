package com.example.githubuser.presentation.fragment.githubrepository

import android.content.Context
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser.databinding.FragmentRepoBinding
import com.example.githubuser.core.data.local.entity.favoriteproject.FavoriteProject
import com.example.githubuser.core.data.remote.apiresponse.coderepository.RepositoryUserResponseItem
import com.example.githubuser.myapplication.MyApplication
import com.example.githubuser.presentation.fragment.githubrepository.adapter.RepositoryRecyclerViewAdapter
import com.example.githubuser.presentation.fragment.favorite.FavoriteViewModel
import com.example.githubuser.presentation.utils.UtilViewModel
import com.example.githubuser.presentation.utils.viewmodelfactory.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject


class RepositoryFragment: Fragment() {
    @Inject
    lateinit var factory : ViewModelFactory

    private lateinit var binding: FragmentRepoBinding
    private lateinit var adapter : RepositoryRecyclerViewAdapter

    private val repositoryViewModel : GithubRepositoryViewModel by viewModels{
        factory
    }

    private val favoriteViewModel : FavoriteViewModel by viewModels{
        factory
    }

    private val utilViewModel by viewModels<UtilViewModel>()
    private var userName = ""

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApplication).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRepoBinding.inflate(layoutInflater)
        repositoryViewModel.isLoading.observe(viewLifecycleOwner){ isLoading(it) }
        userName = arguments?.getString("value","").toString()


        repositoryViewModel.apply {
            getUserRepository(userName).observe(viewLifecycleOwner){ responData ->
                showRepositoryList(responData)
            }
        }
        return binding.root
    }

    private fun showRepositoryList(list: List<RepositoryUserResponseItem>){

        adapter = RepositoryRecyclerViewAdapter(list)
        val recView = binding.Reporecview
        recView.adapter = adapter
        recView.layoutManager = LinearLayoutManager(requireContext())
        utilViewModel.apply { if (adapter.itemCount== 0) setEmptys(true) else setEmptys(false) }
        emptyChecker()

        if (context?.resources?.configuration?.orientation == Configuration.ORIENTATION_LANDSCAPE){
            recView.layoutManager = GridLayoutManager(requireContext(),2)
        }else{
            recView.layoutManager = LinearLayoutManager(requireContext())
        }

        adapter.onItemClickFav(object : RepositoryRecyclerViewAdapter.OnItemClickFavorite{
            override fun onItemClickFavorite(data: RepositoryUserResponseItem) {
               setFavoriteRepo(data)
            }
        })
    }

    private fun setFavoriteRepo(data : RepositoryUserResponseItem){
        val favTemp = FavoriteProject(
            data.name,
            data.description,
            data.language,
            true
        )

        favoriteViewModel.insertFavoriteRepo(favTemp)
        Snackbar.make(binding.root,"add ${data.name} as Favorite Repo",
            Snackbar.LENGTH_LONG)
            .setTextColor(Color.WHITE)
            .setBackgroundTint(Color.rgb(0, 200, 151))
            .show()
    }

    private fun isLoading(isLoading:Boolean){
        binding.Repoprogress.apply {
            visibility = if (isLoading) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }

    private fun emptyChecker(){
        binding.apply {
            utilViewModel.isEmpty.observe(viewLifecycleOwner){ isDataNotExist ->
                if (isDataNotExist == true){
                    emptyStatmentRepo.visibility = View.VISIBLE
                    "$userName never make a repo".also { emptyStatmentRepo.text = it }
                }
            }
        }
    }

}