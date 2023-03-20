package com.extraaedge.assignment.spacerocket.data

import com.extraaedge.assignment.spacerocket.data.local.RocketDao
import com.extraaedge.assignment.spacerocket.data.model.Rocket
import com.extraaedge.assignment.spacerocket.data.remote.RocketApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RocketRepository @Inject constructor(
    private val spaceXApi: RocketApi, private
    val rocketDao: RocketDao
) {
    suspend fun listRockets(hardRefresh: Boolean): RocketResult<List<Rocket>> {
        return withContext(Dispatchers.IO) {
            val dbResults = rocketDao.getAllRockets()
            if (!hardRefresh && dbResults.isNotEmpty()) {
                RocketResult.Success(dbResults)
            } else {
                try {
                    val response = spaceXApi.listRockets()
                    if (response.isSuccessful) {
                        val data = response.body()
                        data?.forEach { rocket ->
                            rocketDao.insert(rocket)
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
    }
}