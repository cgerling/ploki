package org.singlebit.ploki.title

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_title.view.*

import org.singlebit.ploki.R
import org.singlebit.ploki.core.adapter.ArticleListAdapter
import org.singlebit.ploki.core.factory.TitleFactory
import org.singlebit.ploki.wikipedia.model.ArticleItem

class TitleFragment : Fragment(R.layout.fragment_title), TitleContract.View {

    var presenter: TitleContract.Presenter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = TitleFactory.presenter(this)

        featuredArticles(view)
        searchView(view)

        presenter?.onViewCreated()
    }

    private fun featuredArticles(view: View) {
        val recyclerView = view.featured_articles as RecyclerView
        recyclerView.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter =
            ArticleListAdapter { item ->
                item?.let { it ->
                    presenter?.onArticleItemClicked(it)
                }
            }
    }

    private fun searchView(view: View) {
        val searchView = view.search_view as SearchView
        searchView.apply {
            setOnClickListener {
                presenter?.onSearchIntent()
            }

            setOnQueryTextFocusChangeListener { v, hasFocus ->
                if (!hasFocus) return@setOnQueryTextFocusChangeListener

                presenter?.onSearchIntent()
            }
        }
    }

    override fun showError(message: String) {
        Log.e("TitleFragment", message)
    }

    override fun publishItems(items: List<ArticleItem>) {
        val adapter = view?.featured_articles?.adapter as? ArticleListAdapter
        adapter?.let {
            it.data = items
        }
    }

}
