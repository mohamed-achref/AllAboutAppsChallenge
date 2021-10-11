package at.allaboutapps.app.networking.services

import at.allaboutapps.app.networking.model.AuthToken
import at.allaboutapps.app.networking.model.Club
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.POST

const val API_VERSION = "v1"

interface ApiService {

    @POST("/$API_VERSION/login")
    fun login(): Single<AuthToken>

    @GET("/hiring/clubs.json")
    fun getAllClubs(): Single<List<Club>>
}
