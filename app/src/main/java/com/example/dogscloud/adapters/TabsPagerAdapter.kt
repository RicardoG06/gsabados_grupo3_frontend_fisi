package com.example.dogscloud.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.dogscloud.activities.Fragments.misservicios.ClientOrdersStatusFragment

class TabsPagerAdapter(
    fragmentManager: FragmentManager,
    lifeCycle: Lifecycle,
    var numberOfTabs: Int

): FragmentStateAdapter(fragmentManager,lifeCycle) {

    override fun getItemCount(): Int {
        return numberOfTabs
    }

    override fun createFragment(position: Int): Fragment {
        when(position){
            0 -> {
                val bundle = Bundle()
                bundle.putString("status", "PAGADO")
                val clientStatusFragment= ClientOrdersStatusFragment()
                clientStatusFragment.arguments = bundle
                return clientStatusFragment
            }
            1 -> {
                val bundle = Bundle()
                bundle.putString("status", "EN SERVICIO")
                val clientStatusFragment= ClientOrdersStatusFragment()
                clientStatusFragment.arguments = bundle
                return clientStatusFragment
            }
            2 -> {
                val bundle = Bundle()
                bundle.putString("status", "CULMINADO")
                val clientStatusFragment= ClientOrdersStatusFragment()
                clientStatusFragment.arguments = bundle
                return clientStatusFragment
            }
            else -> return ClientOrdersStatusFragment()
        }
    }

}