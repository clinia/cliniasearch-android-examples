package com.clinia.demoApp.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.clinia.widgets.R
import com.clinia.widgets.ui.main.MainViewModel
import com.clinia.widgets.ui.view.ResultAdapter
import com.clinia.widgets.ui.view.SearchBar
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment(), SearchBar.SearchListener {

    private lateinit var viewModel: MainViewModel

    private var adapter: ResultAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchBar.searchListener = this
        context?.let {
            adapter = ResultAdapter(it, mutableListOf())
            resultsList.adapter = adapter
            resultsList.layoutManager = LinearLayoutManager(activity)
        }
    }

    override fun search(query: String) {
        viewModel.getSearchData(query, null, null).observe(this, Observer {
            adapter?.setData(it.records)
        })
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}
