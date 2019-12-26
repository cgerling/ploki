package org.singlebit.ploki.core.factory

import androidx.navigation.fragment.findNavController
import org.singlebit.ploki.title.*

object TitleFactory {
    fun presenter(view: TitleFragment): TitleContract.Presenter {
        val router: TitleContract.Router = TitleRouter(view.findNavController())
        val interactor: TitleContract.Interactor = TitleInteractor()

        return TitlePresenter(view, router, interactor).apply {
            this.interactor?.output = this
        }
    }
}