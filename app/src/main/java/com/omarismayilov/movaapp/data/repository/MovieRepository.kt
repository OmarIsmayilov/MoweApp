package com.omarismayilov.movaapp.data.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.omarismayilov.movaapp.common.utils.Resource
import com.omarismayilov.movaapp.data.database.MyListDAO
import com.omarismayilov.movaapp.data.model.CreditDTO
import com.omarismayilov.movaapp.data.model.DetailDTO
import com.omarismayilov.movaapp.data.model.GenreDTO
import com.omarismayilov.movaapp.data.model.NowPlayingDTO
import com.omarismayilov.movaapp.data.model.RateDTO
import com.omarismayilov.movaapp.data.model.ReviewsDTO
import com.omarismayilov.movaapp.data.model.TopRatedDTO
import com.omarismayilov.movaapp.data.model.TrailersDTO
import com.omarismayilov.movaapp.data.model.local.MovieLocalModel
import com.omarismayilov.movaapp.data.model.response.FilterOption
import com.omarismayilov.movaapp.data.network.MovieApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieService: MovieApiService,
    private val localService: MyListDAO
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

        val sort =
            if (filterOption.sort == "Popularity") "popularity.desc" else "primary_release_date.desc"
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

    fun getDetail(id: Int): Flow<Resource<DetailDTO>> = flow {
        emit(Resource.Loading)
        val response = movieService.getMovieDetail(id)
        emit(Resource.Success(response))
    }.catch {
        emit(Resource.Error(it as Exception))
    }

    fun getCredit(id: Int): Flow<Resource<CreditDTO>> = flow {
        emit(Resource.Loading)
        val response = movieService.getCredits(id)
        emit(Resource.Success(response))
    }.catch {
        emit(Resource.Error(it as Exception))
    }

    fun getRecommendation(id: Int): Flow<Resource<TopRatedDTO>> = flow {
        emit(Resource.Loading)
        val response = movieService.getRecommendation(id)
        emit(Resource.Success(response))
    }.catch {
        emit(Resource.Error(it as Exception))
    }

    fun getReview(id: Int): Flow<Resource<ReviewsDTO>> = flow {
        emit(Resource.Loading)
        val response = movieService.getReview(id)
        emit(Resource.Success(response))
    }.catch {
        emit(Resource.Error(it as Exception))
    }

    fun getTrailer(id: Int): Flow<Resource<TrailersDTO>> = flow {
        emit(Resource.Loading)
        val response = movieService.getTrailer(id)
        emit(Resource.Success(response))
    }.catch {
        emit(Resource.Error(it as Exception))
    }

    suspend fun addList(movie: MovieLocalModel) = localService.addList(movie)

    suspend fun getListData(): Flow<Resource<List<MovieLocalModel>>> = flow {
        emit(Resource.Loading)
        val response = localService.getListData()
        emit(Resource.Success(response))
    }.catch {
        emit(Resource.Error(it as Exception))
    }


    suspend fun rateMovie(id: Int, rate: Float) : Flow<RateDTO> = flow {
        val json = "{\"value\": $rate}"
        val response = movieService.rateMovie(id,json)
        emit(response)
        Log.e(TAG, "rateMovie: $response", )
    }

    suspend fun deleteItem(id: Int) = localService.deleteItem(id)


}