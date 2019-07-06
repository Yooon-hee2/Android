package jjackjjack.sopt.com.jjackjjack.activities.donate.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import jjackjjack.sopt.com.jjackjjack.activities.donate.adapter.DonateSortCategoryPagerAdapter
import jjackjjack.sopt.com.jjackjjack.R
import kotlinx.android.synthetic.main.fragment_animal_category.*

class AnimalFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_animal_category, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var sub_adapter =
            DonateSortCategoryPagerAdapter(childFragmentManager)
        //var sub_adapter = DonateSortCategoryPagerAdapter(activity!!.supportFragmentManager)
        donate_sort_pager.adapter = sub_adapter

        donate_sort_tab.setupWithViewPager(donate_sort_pager)

        donate_sort_tab.getTabAt(0)!!.setText("최신순")
        donate_sort_tab.getTabAt(1)!!.setText("기부율 높은순")
        donate_sort_tab.getTabAt(2)!!.setText("기부율 낮은순")

//        val navDonateListSort: View =
//            (activity!!.getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
//                .inflate(R.layout.nav_donatelist_sort, null, false)
//
//        donate_sort_tab.getTabAt(0)!!.customView =
//            navDonateListSort.findViewById(R.id.nav_donatelist_sort_recent) as RelativeLayout
//        donate_sort_tab.getTabAt(1)!!.customView =
//            navDonateListSort.findViewById(R.id.nav_donatelist_sort_popular) as RelativeLayout
//        donate_sort_tab.getTabAt(2)!!.customView =
//            navDonateListSort.findViewById(R.id.nav_donatelist_sort_unpopular) as RelativeLayout

    }
}