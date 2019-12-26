package org.singlebit.ploki.article

import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_article.*
import kotlinx.android.synthetic.main.fragment_article.view.*

import org.singlebit.ploki.R
import org.singlebit.ploki.core.constants.WIKIPEDIA_URL
import org.singlebit.ploki.core.factory.ArticleFactory
import org.singlebit.ploki.wikipedia.model.Article

class ArticleFragment : Fragment(R.layout.fragment_article), ArticleContract.View {

    var presenter: ArticleContract.Presenter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = ArticleFactory.presenter(this)

        val id = arguments?.getInt("a") as Int
        presenter?.onLoad(id)
    }


    override fun onDestroy() {
        presenter?.onDestroy()
        presenter = null
        super.onDestroy()
    }

    override fun showArticle(article: Article) {
        view?.let {
            it.article_title.text = article.title

            val url = "$WIKIPEDIA_URL/"
            val content = article.content
            val mimeType = "text/html"
            val encoding = "utf-8"
            it.web_view.loadDataWithBaseURL(url, content, mimeType, encoding, null)
        }
    }
}
