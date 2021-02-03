package com.example.testwork.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testwork.data.DataSource
import com.example.testwork.data.Number
import com.example.testwork.R
import com.example.testwork.adapters.RecyclerviewAdapter


class PrimeNumbersFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: RecyclerviewAdapter
    private var dataSet= mutableListOf<Number>()
    private var k= 100
    private lateinit var dataSource: DataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataSource= DataSource(requireContext())
        recyclerViewAdapter =
            RecyclerviewAdapter(dataSet)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_prime, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerview_prime)
        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.adapter = recyclerViewAdapter
        recyclerView.layoutManager = gridLayoutManager

        addData()

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                //  Log.d(
                //    "TAG",
                // "last visible position: ${gridLayoutManager.findLastCompletelyVisibleItemPosition()}, total count: ${gridLayoutManager.itemCount}"
                //)
                val visibleThreshold = 2

                if(dy > 0){
                    val layoutManager = recyclerView.layoutManager as GridLayoutManager?
                    val lastItem = layoutManager!!.findLastCompletelyVisibleItemPosition()
                    val currentTotalCount = layoutManager.itemCount

                    //15
                    if (currentTotalCount-25 <= lastItem + visibleThreshold) {
                        addData()
                    }
                }

            }
        })

    }

    fun addData() {
        if (k == 100)
            dataSet.addAll(dataSource.getPrimeNumber(0,k))

        else
            dataSet.addAll(dataSource.getPrimeNumber(k-99,k))

        recyclerViewAdapter.notifyDataSetChanged()
        k += 100

    }

}