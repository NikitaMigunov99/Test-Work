package com.example.testwork.ui


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.testwork.R


class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager= findViewById(R.id.view_pager)

        viewPager.adapter= ViewPagerAdapter(supportFragmentManager)
    }


    private class ViewPagerAdapter(manager: FragmentManager): FragmentStatePagerAdapter(manager){


        override fun getItem(position: Int): Fragment {
             if(position==0)
                return FibonacciFragment()
             else
                 return PrimeNumbersFragment()

            }


        override fun getCount(): Int {
            return 2
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return when (position) {
                0 -> "Фибоначчи"
                1 -> "Простые"
                else -> ""
            }
        }

    }

}