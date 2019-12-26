package org.singlebit.ploki.search

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import org.singlebit.ploki.R

class SearchRouter(private var navController: NavController) : SearchContract.Router {

    override fun toArticle(id: Int) {
        val bundle = bundleOf("a" to id)
        navController.navigate(R.id.action_searchFragment_to_articleFragment, bundle)
    }

}