package ir.es.mohammad.dbatman.ui.movies

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import ir.es.mohammad.dbatman.R
import ir.es.mohammad.dbatman.util.Result
import ir.es.mohammad.dbatman.databinding.FragmentMoviesBinding
import ir.es.mohammad.dbatman.ui.util.launchAndRepeatWithViewLifecycle
import ir.es.mohammad.dbatman.ui.util.startLoading
import ir.es.mohammad.dbatman.ui.util.stopLoading

@AndroidEntryPoint
class MoviesFragment : Fragment(R.layout.fragment_movies) {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MoviesViewModel by viewModels()
    private lateinit var movieAdapter: MoviesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMoviesBinding.bind(view)

        if (savedInstanceState == null)
            viewModel.getMovies()
        initViews()
        observe()
    }

    private fun initViews() {
        with(binding) {
            movieAdapter = MoviesAdapter() {
                val navDirections = MoviesFragmentDirections.actionMoviesFragmentToMovieFragment(it.id)
                findNavController().navigate(navDirections)
            }
            recyclerViewMovies.apply {
                adapter = movieAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }
        }
    }

    private fun observe() {
        launchAndRepeatWithViewLifecycle {
            observeMovies()
        }
    }

    private suspend fun observeMovies() {
        viewModel.moviesFlow.collect { result ->
            with(binding) {
                when (result) {
                    is Result.Loading -> {
                        startLoading(groupLoad, lottieLoading)
                    }
                    is Result.Success -> {
                        movieAdapter.submitList(result.data!!)
                        stopLoading(groupLoad, lottieLoading)
                    }
                    is Result.Error -> {
                        Snackbar.make(requireView(), result.message!!, 10000)
                            .setAction("Try Again") { viewModel.getMovies() }.show()
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