package com.extraaedge.assignment.spacerocket.data

import com.extraaedge.assignment.spacerocket.api.SpaceApi
import com.extraaedge.assignment.spacerocket.data.model.Rocket
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RocketRepository @Inject constructor(private val spaceXApi: SpaceApi) {
    suspend fun listRockets(): ApiResult<List<Rocket>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = spaceXApi.listRockets()
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) ApiResult.Success(data) else ApiResult.Error(response.message())
                } else {
                    ApiResult.Error(response.message())
                }
            } catch (e: Exception) {
                ApiResult.Error(e.message)
            }
        }
    }
}