package org.singlebit.ploki.core.factory

import androidx.navigation.fragment.findNavController
import org.singlebit.ploki.article.*

object ArticleFactory {
    fun presenter(view: ArticleFragment): ArticleContract.Presenter {
        val router: ArticleContract.Router? = ArticleRouter(view.findNavController())
        val interactor: ArticleContract.Interactor? = ArticleInteractor()

        return ArticlePresenter(view, router, interactor).apply {
            this.interactor?.output = this
        }
    }
}