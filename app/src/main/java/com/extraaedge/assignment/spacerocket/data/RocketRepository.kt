package com.extraaedge.assignment.spacerocket.data

import com.extraaedge.assignment.spacerocket.api.SpaceApi
import com.extraaedge.assignment.spacerocket.data.model.Rocket
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.http.Query
import javax.inject.Inject

class RocketRepository @Inject constructor(val spaceXApi: SpaceApi) {
    suspend fun listRockets(): Response<List<Rocket>> {
        return withContext(Dispatchers.IO) {
            spaceXApi.listRockets();
        }
    }

    suspend fun getRocketDetails(@Query("id") id: String): Response<Rocket> {
        return withContext(Dispatchers.IO) {
            spaceXApi.getRocketDetails(id)
        }
    }
}