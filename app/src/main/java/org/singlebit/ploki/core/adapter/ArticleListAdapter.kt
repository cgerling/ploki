package org.singlebit.ploki.core.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import kotlinx.android.synthetic.main.article_item.view.*
import org.singlebit.ploki.R
import org.singlebit.ploki.core.constants.AVERAGE_READ_SPEED
import org.singlebit.ploki.core.types.ClickHandler
import org.singlebit.ploki.wikipedia.model.ArticleItem

class ArticleListAdapter(private val handler: ClickHandler<ArticleItem>? = null) :
    RecyclerView.Adapter<ArticleListAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val title: TextView? = itemView.title_text
        private val lastUpdated: TextView? = itemView.last_updated_text
        private val readTime: TextView? = itemView.read_time_text

        fun bind(data: ArticleItem, handler: ClickHandler<ArticleItem>? = null) {
            title?.text = data.title
            lastUpdated?.text = toReadableDate(data.timestamp)
            readTime?.text = toReadTime(data.wordcount)

            itemView.setOnClickListener {
                handler?.let { it(data) }
            }
        }

        private fun toReadableDate(date: Date?): String {
            if (date == null) return ""

            return SimpleDateFormat("MMM dd, yy", Locale.US)
                .apply {
                    timeZone = TimeZone.getTimeZone("UTC")
                }
                .format(date)
        }

        private fun toReadTime(wordCount: Int?): String {
            if (wordCount == null) return ""

            return "${readTime(wordCount)} min read"
        }
        private fun readTime(wordCount: Int): Int = wordCount / AVERAGE_READ_SPEED
    }

    var data = listOf<ArticleItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]

        holder.bind(item, handler)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.article_item, parent, false)
        return ViewHolder(view)
    }

}
