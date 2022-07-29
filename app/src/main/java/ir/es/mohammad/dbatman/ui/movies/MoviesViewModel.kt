package ir.es.mohammad.dbatman.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.es.mohammad.dbatman.data.MovieRepository
import ir.es.mohammad.dbatman.data.remote.util.Result
import ir.es.mohammad.dbatman.model.MovieItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {
    private val _moviesFlow: MutableStateFlow<Result<ArrayList<MovieItem>>> =
        MutableStateFlow(Result.Loading())
    val moviesFlow = _moviesFlow.asStateFlow()

    fun getMovies() {
        viewModelScope.launch {
            movieRepository.getBatmanMovies().collect {
                _moviesFlow.emit(it)
            }
        }
    }
}