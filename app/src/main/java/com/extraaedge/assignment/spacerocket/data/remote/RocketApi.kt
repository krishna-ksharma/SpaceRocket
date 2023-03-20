package com.extraaedge.assignment.spacerocket.data.remote
import com.extraaedge.assignment.spacerocket.data.model.Rocket
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RocketApi {
    @GET(ApiConstants.API_ENDPOINT)
    suspend fun listRockets(): Response<List<Rocket>>

    @GET(ApiConstants.API_ENDPOINT)
    suspend fun getRocketDetails(@Query("id") id: String): Response<Rocket>
}