package ir.es.mohammad.dbatman.ui.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import ir.es.mohammad.dbatman.ui.loadUrl

@BindingAdapter("src_url")
fun srcUrl(imageView: ImageView, imageUrl: String?) {
    imageView.loadUrl(imageUrl)
}