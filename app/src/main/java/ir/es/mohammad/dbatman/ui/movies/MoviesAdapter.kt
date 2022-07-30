package ir.es.mohammad.dbatman.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ir.es.mohammad.dbatman.databinding.ItemMovieBinding
import ir.es.mohammad.dbatman.model.MovieItem
import ir.es.mohammad.dbatman.ui.loadUrl

class MoviesAdapter(
    private val onMovieSelected: (movie: MovieItem) -> Unit
) : ListAdapter<MovieItem, MoviesAdapter.CustomViewHolder>(UserDiffCallBack()) {

    inner class CustomViewHolder(private val itemViewBinding: ItemMovieBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root) {

        fun bind(movie: MovieItem) {
            with(itemViewBinding) {
                root.setOnClickListener { onMovieSelected(movie) }
                textMovieTitle.text = movie.title
                imgMovie.loadUrl(movie.poster)
                textType.text = movie.type
                textYear.text = movie.year
            }
        }
    }

    private class UserDiffCallBack : DiffUtil.ItemCallback<MovieItem>() {
        override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CustomViewHolder(ItemMovieBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}