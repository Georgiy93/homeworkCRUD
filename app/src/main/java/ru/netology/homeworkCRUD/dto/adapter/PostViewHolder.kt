package ru.netology.homeworkCRUD.dto.adapter

import android.system.Os.remove
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import ru.netology.homeworkCRUD.DisplayingNumbers
import ru.netology.homeworkCRUD.R
import ru.netology.homeworkCRUD.databinding.CardPostBinding
import ru.netology.homeworkCRUD.dto.Post

class PostViewHolder(
    private val binding: CardPostBinding,
    private val onInteractionListener: OnInteractionListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.apply {
            author.text = post.author
            published.text = post.published
            content.text = post.content

            likes.setImageResource(
                if (post.likedByMe)
                    R.drawable.ic_baseline_favorite_24
                else R.drawable.ic_baseline_favorite_border_24
            )
            countView.text = DisplayingNumbers(post.view)
            countLikes.text = DisplayingNumbers(post.like)
            countShares.text = DisplayingNumbers(post.share)
            likes.setOnClickListener {
                onInteractionListener.onLike(post)

            }
            share.setOnClickListener {
                onInteractionListener.onShare(post)

            }

            view.setOnClickListener {
                onInteractionListener.onView(post)
            }
            menu.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.options_post)
                    setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.remove -> {
                                onInteractionListener.onRemove(post)
                                true
                            }
                            R.id.edit -> {
                                onInteractionListener.onEdit(post)
                                true
                            }
                            else -> false
                        }
                    }
                }.show()
            }

        }

    }
}