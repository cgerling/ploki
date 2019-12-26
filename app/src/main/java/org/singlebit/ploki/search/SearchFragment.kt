package org.singlebit.ploki.search

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_search.view.*
import org.singlebit.ploki.R
import org.singlebit.ploki.core.adapter.ArticleListAdapter
import org.singlebit.ploki.core.factory.SearchFactory
import org.singlebit.ploki.wikipedia.model.ArticleItem

class SearchFragment : Fragment(R.layout.fragment_search), SearchContract.View {

    var presenter: SearchContract.Presenter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = SearchFactory.presenter(this)

        articles(view)
        searchView(view)
    }

    private fun articles(view: View) {
        val recyclerView = view.articles as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter =
            ArticleListAdapter {
                it?.let { article ->
                    presenter?.onArticleItemClicked(article)
                }
            }
    }

    private fun searchView(view: View) {
        view.search_view.apply {
            isIconified = false
            isIconifiedByDefault = false

            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String?): Boolean = false
                override fun onQueryTextSubmit(term: String?): Boolean {
                    presenter?.onSearchSubmit(term!!)
                    return false
                }
            })
        }
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        presenter = null
        super.onDestroy()
    }

    override fun showError(message: String) {
        Log.e("SearchFragment", message)
    }

    override fun publishResultItems(items: List<ArticleItem>) {
        val adapter = view?.articles?.adapter as? ArticleListAdapter
        adapter?.let {
            it.data = items
        }
    }

}
