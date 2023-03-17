package com.extraaedge.assignment.spacerocket.data

import com.extraaedge.assignment.spacerocket.data.remote.SpaceApi
import com.extraaedge.assignment.spacerocket.data.model.Rocket
import com.extraaedge.assignment.spacerocket.data.local.RocketDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RocketRepository @Inject constructor(
    private val spaceXApi: SpaceApi, private
    val rocketDao: RocketDao
) {
    suspend fun listRockets(): RocketResult<List<Rocket>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = spaceXApi.listRockets()
                if (response.isSuccessful) {
                    val data = response.body()
                    data?.forEach {
                        rocketDao.insert(it)
                    }
                }
                RocketResult.Success(rocketDao.getAllRockets())
            } catch (e: Exception) {
                val localRockets = rocketDao.getAllRockets();
                if (localRockets.isEmpty()) RocketResult.Error(e.message) else RocketResult.Success(
                    rocketDao.getAllRockets()
                )
            }
        }
    }

    suspend fun getAllRockets() = rocketDao.getAllRockets()

    suspend fun insertRocket(rocket: Rocket) = rocketDao.insert(rocket)

    suspend fun deleteAllRockets() = rocketDao.deleteAllRockets()
}