package org.singlebit.ploki.title

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_title.view.*

import org.singlebit.ploki.R

class TitleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_title, container, false)

        view.search_view.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_titleFragment_to_searchFragment)
        )

        return view
    }

}
