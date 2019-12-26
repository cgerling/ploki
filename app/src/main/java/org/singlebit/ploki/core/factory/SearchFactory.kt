package org.singlebit.ploki.core.factory

import androidx.navigation.fragment.findNavController
import org.singlebit.ploki.search.*

object SearchFactory {
    fun presenter(view: SearchFragment): SearchContract.Presenter {
        val router: SearchContract.Router? = SearchRouter(view.findNavController())
        val interactor: SearchContract.Interactor? = SearchInteractor()

        return SearchPresenter(view, router, interactor).apply {
            this.interactor?.output = this
        }
    }
}