package ir.es.mohammad.dbatman.ui.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("src_url")
fun srcUrl(imageView: ImageView, imageUrl: String?) {
    imageView.loadUrl(imageUrl)
}