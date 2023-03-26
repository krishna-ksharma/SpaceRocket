package com.extraaedge.assignment.spacerocket.com.extraaedge.assignment.spacerocket.data

import com.extraaedge.assignment.spacerocket.com.extraaedge.assignment.spacerocket.data.model.FakeRocket
import com.extraaedge.assignment.spacerocket.com.extraaedge.assignment.spacerocket.data.remote.MockServer
import com.extraaedge.assignment.spacerocket.com.extraaedge.assignment.spacerocket.data.remote.enqueueFailureResponse
import com.extraaedge.assignment.spacerocket.com.extraaedge.assignment.spacerocket.data.remote.enqueueResponse
import com.extraaedge.assignment.spacerocket.data.RocketRepository
import com.extraaedge.assignment.spacerocket.data.RocketResult
import com.extraaedge.assignment.spacerocket.data.local.RocketDao
import com.extraaedge.assignment.spacerocket.data.model.Rocket
import com.extraaedge.assignment.spacerocket.data.remote.RocketApi
import java.net.HttpURLConnection
import javax.inject.Inject

class FakeRocketRepository @Inject constructor(
    private val fakeApi: RocketApi, private val fakeDao: RocketDao
) {
    var repository: RocketRepository = RocketRepository(fakeApi, fakeDao)

    private suspend fun listRockets(hardRefresh: Boolean): RocketResult<List<Rocket>> {
        return repository.listRockets(hardRefresh)
    }

    suspend fun listRocket_from_remote(): RocketResult<List<Rocket>> {
        MockServer.server.enqueueResponse("rockets_response.json")
        val result = listRockets(true)
        MockServer.server.shutdown()
        return result
    }

    suspend fun listRocket_failed_from_remote(): RocketResult<List<Rocket>> {
        MockServer.server.enqueueFailureResponse(HttpURLConnection.HTTP_INTERNAL_ERROR)
        val result = listRockets(true)
        MockServer.server.shutdown()
        return result
    }

    suspend fun listEmptyRocket_from_local(): RocketResult<List<Rocket>> {
        return listRockets(false)
    }

    suspend fun listRocket_from_local(): RocketResult<List<Rocket>> {
        fakeDao.insert(FakeRocket.fakeData())
        return listRockets(false)
    }

}