package org.singlebit.ploki.title

import androidx.navigation.NavController
import org.singlebit.ploki.R

class TitleRouter(private val navController: NavController): TitleContract.Router {

    override fun toSearch() {
        navController.navigate(R.id.action_titleFragment_to_searchFragment)
    }

    override fun toArticle(id: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}