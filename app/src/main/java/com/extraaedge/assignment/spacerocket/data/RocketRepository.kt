package com.extraaedge.assignment.spacerocket.data

import com.extraaedge.assignment.spacerocket.data.local.RocketDao
import com.extraaedge.assignment.spacerocket.data.model.Rocket
import com.extraaedge.assignment.spacerocket.data.remote.RocketApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RocketRepository @Inject constructor(
    private val spaceXApi: RocketApi, private
    val rocketDao: RocketDao
) {
    suspend fun listRockets(hardRefresh: Boolean): Flow<RocketResult> {
        return flow {
            val dbResults = rocketDao.getAllRockets()
            if (!hardRefresh) {
                emit(RocketResult.Success(dbResults))
            } else {
                try {
                    val response = spaceXApi.listRockets()
                    if (response.isSuccessful) {
                        val data = response.body()
                        data?.forEach { rocket ->
                            rocketDao.insert(rocket)
                        }
                        emit(RocketResult.Success(rocketDao.getAllRockets()))
                    } else {
                        emit(handleError(response.message()))
                    }
                } catch (e: Exception) {
                    emit(handleError(e.message))
                }
            }
        }.flowOn(Dispatchers.IO)
    }

    private fun handleError(message: String?): RocketResult {
        val localRockets = rocketDao.getAllRockets();
        return if (localRockets.isEmpty()) RocketResult.Error(message) else RocketResult.Success(
            rocketDao.getAllRockets()
        )
    }
}