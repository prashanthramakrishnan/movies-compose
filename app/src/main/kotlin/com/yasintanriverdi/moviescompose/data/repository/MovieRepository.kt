package com.yasintanriverdi.moviescompose.data.repository

import com.yasintanriverdi.moviescompose.data.datasource.MovieRemoteDataSource
import com.yasintanriverdi.moviescompose.data.mapper.MovieResponseItemToMovieMapper
import com.yasintanriverdi.moviescompose.data.mapper.MoviesResponseToMovieListMapper
import com.yasintanriverdi.moviescompose.model.Movie
import com.yasintanriverdi.moviescompose.model.RepositoryResult
import com.yasintanriverdi.moviescompose.model.toRepositoryResult
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieListMapper: MoviesResponseToMovieListMapper,
    private val movieItemMapper: MovieResponseItemToMovieMapper
) {

    suspend fun fetchMovies(page: Int, category: String = CATEGORY_POPULAR): RepositoryResult<List<Movie>> =
        movieRemoteDataSource.fetchMovies(page, category).toRepositoryResult(movieListMapper)

    suspend fun fetchMovieById(movieId: String): RepositoryResult<Movie> =
        movieRemoteDataSource.fetchMovieById(movieId).toRepositoryResult(movieItemMapper)

    companion object {
        const val CATEGORY_POPULAR = "popular"
    }
}
