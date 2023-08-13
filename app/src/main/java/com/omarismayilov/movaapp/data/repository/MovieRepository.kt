package com.omarismayilov.movaapp.data.repository

import com.omarismayilov.movaapp.common.utils.Resource
import com.omarismayilov.movaapp.data.model.NowPlayingDTO
import com.omarismayilov.movaapp.data.model.TopRatedDTO
import com.omarismayilov.movaapp.data.model.response.FilterOption
import com.omarismayilov.movaapp.data.network.MovieApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieService: MovieApiService,
) {

    fun getNowPlaying(): Flow<Resource<NowPlayingDTO>> = flow {
        emit(Resource.Loading)
        val response = movieService.getNowPlayingApi()
        emit(Resource.Success(response))
    }.catch {
        emit(Resource.Error(it as Exception))
    }

    fun getTrending(): Flow<Resource<TopRatedDTO>> = flow {
        emit(Resource.Loading)
        val response = movieService.getTrending()
        emit(Resource.Success(response))
    }.catch {
        emit(Resource.Error(it as Exception))
    }

    fun getTopRated(): Flow<Resource<TopRatedDTO>> = flow {
        emit(Resource.Loading)
        val response = movieService.getTopRated()
        emit(Resource.Success(response))
    }.catch {
        emit(Resource.Error(it as Exception))
    }

    fun getUpcoming(): Flow<Resource<NowPlayingDTO>> = flow {
        emit(Resource.Loading)
        val response = movieService.getUpComing()
        emit(Resource.Success(response))
    }.catch {
        emit(Resource.Error(it as Exception))
    }

    fun getSearchData(query: String): Flow<Resource<TopRatedDTO>> = flow {
        emit(Resource.Loading)
        val response = movieService.getSearch(query)
        emit(Resource.Success(response))
    }.catch {
        emit(Resource.Error(it as Exception))
    }

    fun getFilter(filterOption: FilterOption): Flow<Resource<TopRatedDTO>> = flow {
        emit(Resource.Loading)

        val sort = if (filterOption.sort == "Popularity") "popularity.desc" else "primary_release_date.desc"
        val genre = filterOption.genre.joinToString(",")
        val period = filterOption.period.takeIf { it != "All periods" }?.toInt()
        val region = filterOption.region.takeUnless { it == "All regions" } ?: ""
        val category = filterOption.category.takeUnless { it == "All categories" } ?: ""

        val response = if (category == "Movie") {
            movieService.getMovieFilter(genre, period, region, sort)
        } else {
            movieService.getTvFilter(genre, period, region, sort)
        }

        emit(Resource.Success(response))
    }.catch {
        emit(Resource.Error(it as Exception))
    }



}