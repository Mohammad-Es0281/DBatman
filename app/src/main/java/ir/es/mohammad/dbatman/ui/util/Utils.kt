package ir.es.mohammad.dbatman.ui

import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import ir.es.mohammad.dbatman.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun ImageView.loadUrl(url: String?, vararg requestOptions: RequestOptions) {
    val placeHolder = CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 32f
        start()
    }
    val requestBuilder =
        Glide.with(context)
            .load(url)
            .placeholder(placeHolder)
            .error(R.drawable.movie_not_found)
            .transition(DrawableTransitionOptions.withCrossFade(440))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
    requestOptions.forEach { rqOp -> requestBuilder.apply(rqOp) }
    requestBuilder.into(this)
}

inline fun Fragment.launchAndRepeatWithViewLifecycle(
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    crossinline block: suspend CoroutineScope.() -> Unit
) {
    viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.lifecycle.repeatOnLifecycle(minActiveState) {
            block()
        }
    }
}