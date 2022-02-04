package com.example.dogscloud.activities.Fragments.misservicios

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainer
import androidx.viewpager2.widget.ViewPager2
import com.example.dogscloud.R
import com.example.dogscloud.adapters.TabsPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MisServiciosFragment : Fragment() {

    var myView: View? = null

    var viewPager: ViewPager2? = null
    var tabLayout: TabLayout? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState : Bundle?
    ): View? {
       myView =  inflater.inflate(R.layout.fragment_mis_servicios, container , false)

        viewPager = myView?.findViewById(R.id.viewpager)
        tabLayout = myView?.findViewById(R.id.tab_layout)

        tabLayout?.setSelectedTabIndicatorColor(Color.BLACK)
        tabLayout?.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
        tabLayout?.tabTextColors = ContextCompat.getColorStateList(requireContext(), R.color.black)
        tabLayout?.tabMode = TabLayout.MODE_SCROLLABLE
        tabLayout?.isInlineLabel = true
        var numberOfTabs = 3

        val adapter = TabsPagerAdapter(requireActivity().supportFragmentManager, lifecycle ,numberOfTabs)
        viewPager?.adapter = adapter
        viewPager?.isUserInputEnabled = true

        TabLayoutMediator(tabLayout!!, viewPager!!){ tab , position ->

            when(position){
                0 -> {
                    tab.text = "PAGADO"
                }
                1 -> {
                    tab.text = "EN SERVICIO"
                }
                2-> {
                    tab.text = "CULMINADO"
                }
            }

        }.attach()

        return myView
    }

}