package ir.es.mohammad.dbatman.ui.movies

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import ir.es.mohammad.dbatman.R
import ir.es.mohammad.dbatman.data.remote.util.Result
import ir.es.mohammad.dbatman.databinding.FragmentMovieBinding
import ir.es.mohammad.dbatman.ui.*
import ir.es.mohammad.dbatman.ui.movie.MovieViewModel
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MovieFragment : Fragment(R.layout.fragment_movie) {

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!
    private val args: MovieFragmentArgs by navArgs()
    private val viewModel: MovieViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMovieBinding.bind(view)

        if (savedInstanceState == null)
            viewModel.getMovie(args.id)
        observe()
    }

    private fun observe() {
        launchAndRepeatWithViewLifecycle {
            observeMovie()
        }
    }

    private suspend fun observeMovie() {
        viewModel.movieFlow.collect { result ->
            with(binding) {
                when (result) {
                    is Result.Loading -> {
                        textIntroductionTitle.invisible()
                        startLoading(groupLoad, lottieLoading)
                    }
                    is Result.Success -> {
                        movie = result.data!!
                        textToolbarTitle.isSelected = true
                        textIntroductionTitle.visible()
                        stopLoading(groupLoad, lottieLoading)
                    }
                    is Result.Error -> {
                        Snackbar.make(requireView(), result.message!!, 10000)
                            .setAction("Try Again") { viewModel.getMovie(args.id) }.show()
                        textIntroductionTitle.visible()
                        stopLoading(groupLoad, lottieLoading)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}