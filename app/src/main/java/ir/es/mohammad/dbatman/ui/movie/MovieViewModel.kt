package ir.es.mohammad.dbatman.ui.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.es.mohammad.dbatman.data.MovieRepository
import ir.es.mohammad.dbatman.data.remote.util.Result
import ir.es.mohammad.dbatman.model.MovieDetails
import ir.es.mohammad.dbatman.model.MovieItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {
    private val _movieFlow: MutableStateFlow<Result<MovieDetails>> =
        MutableStateFlow(Result.Loading())
    val movieFlow = _movieFlow.asStateFlow()

    fun getMovie(id: String) {
        viewModelScope.launch {
            movieRepository.getMovie(id).collect {
                _movieFlow.emit(it)
            }
        }
    }
}