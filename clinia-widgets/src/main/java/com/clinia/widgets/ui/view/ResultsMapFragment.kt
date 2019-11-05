package com.clinia.widgets.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.clinia.widgets.R
import com.clinia.widgets.ui.main.MainViewModel
import kotlinx.android.synthetic.main.results_map.*

class ResultsMapFragment: Fragment() {

    private lateinit var viewModel: MainViewModel
    private var adapter: ResultAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.results_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        context?.let {
            adapter = ResultAdapter(it, mutableListOf())
            resultsList.adapter = adapter
            resultsList.layoutManager = LinearLayoutManager(activity)
        }
        //TODO: reuse query and location from searchBar
        viewModel.getSearchData("", "").observe(this, Observer {
            adapter?.setData(it.records)
        })
    }


    companion object {
        fun newInstance() = ResultsMapFragment()
    }
}