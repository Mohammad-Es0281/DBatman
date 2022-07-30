package ir.es.mohammad.dbatman.ui.movies

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import ir.es.mohammad.dbatman.R
import ir.es.mohammad.dbatman.data.remote.util.Result
import ir.es.mohammad.dbatman.databinding.FragmentMovieBinding
import ir.es.mohammad.dbatman.ui.launchAndRepeatWithViewLifecycle
import ir.es.mohammad.dbatman.ui.loadUrl
import ir.es.mohammad.dbatman.ui.movie.MovieViewModel

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
        initViews()
        observe()
    }

    private fun initViews() {

    }

    private fun observe() {
        launchAndRepeatWithViewLifecycle {
            observeMovie()
        }
    }

    private suspend fun observeMovie() {
        viewModel.movieFlow.collect { result ->
            when (result) {
                is Result.Loading -> {}
                is Result.Success -> {
                    with(binding) {
                        movie = result.data!!
                        textToolbarTitle.isSelected = true
                    }
                }
                is Result.Error -> {}
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}