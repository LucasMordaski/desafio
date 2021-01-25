package com.mordaski.testeglobant.ui.news

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.mordaski.testeglobant.databinding.ItemNewsBinding
import com.mordaski.testeglobant.ui.news.data.model.NewsResponse

class NewsAdapter(private val listener: NewsItemListener) : RecyclerView.Adapter<NewsViewHolder>() {

    interface NewsItemListener {
        fun onClickedCharacter(newsId: Int)
    }

     val items: MutableList<NewsResponse> = ArrayList()

    fun setItems(items: List<NewsResponse>) {
        this.items.clear()
        this.items.addAll(items)
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding: ItemNewsBinding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) = holder.bind(items[position])
}

class NewsViewHolder(private val itemBinding: ItemNewsBinding, private val listener: NewsAdapter.NewsItemListener) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var news: NewsResponse

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: NewsResponse) {
        this.news = item
        itemBinding.titulo.text = news.titulo
        itemBinding.info.text = news.linhaFina
        Glide.with(itemBinding.root)
            .load(news.imagem)
            .into(itemBinding.image)

    }

    override fun onClick(v: View?) {
        listener.onClickedCharacter(news.id)
    }
}

