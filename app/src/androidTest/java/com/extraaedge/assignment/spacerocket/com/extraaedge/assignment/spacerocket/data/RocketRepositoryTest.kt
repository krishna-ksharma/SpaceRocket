package com.extraaedge.assignment.spacerocket.com.extraaedge.assignment.spacerocket.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.extraaedge.assignment.spacerocket.com.extraaedge.assignment.spacerocket.data.model.FakeRocket
import com.extraaedge.assignment.spacerocket.com.extraaedge.assignment.spacerocket.data.remote.MockServer
import com.extraaedge.assignment.spacerocket.com.extraaedge.assignment.spacerocket.data.remote.enqueueFailureResponse
import com.extraaedge.assignment.spacerocket.com.extraaedge.assignment.spacerocket.data.remote.enqueueResponse
import com.extraaedge.assignment.spacerocket.data.RocketRepository
import com.extraaedge.assignment.spacerocket.data.RocketResult
import com.extraaedge.assignment.spacerocket.data.local.RocketDatabase
import com.extraaedge.assignment.spacerocket.data.model.Rocket
import com.extraaedge.assignment.spacerocket.data.remote.RocketApi
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.*
import java.net.HttpURLConnection
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
class RocketRepositoryTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("FakeRocketApi")
    lateinit var rocketApi: RocketApi

    @Inject
    @Named("fakeDb")
    lateinit var db: RocketDatabase

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @After
    fun tearDown() {
        db.close()
        MockServer.server.shutdown()
    }

    @Test
    fun listRocket_from_remote() {
        val rocketRepository = RocketRepository(rocketApi, db.rocketDAo())
        MockServer.server.enqueueResponse("rockets_response.json")
        runBlocking {
            val result = rocketRepository.listRockets(true)
            Assert.assertEquals(true, result is RocketResult.Success)
            Assert.assertEquals(1, (result as RocketResult.Success).data.size)
        }
    }

    @Test
    fun listRocket_failed_from_remote() {
        val rocketRepository = RocketRepository(rocketApi, db.rocketDAo())
        MockServer.server.enqueueFailureResponse(HttpURLConnection.HTTP_INTERNAL_ERROR)
        runBlocking {
            val result = rocketRepository.listRockets(true)
            Assert.assertEquals(true, result is RocketResult.Error)
            Assert.assertEquals("Server Error", (result as RocketResult.Error).message)
        }
    }

    @Test
    fun listRocket_from_local() {
        val rocketRepository = RocketRepository(rocketApi, db.rocketDAo())
        val rockets = listOf<Rocket>(
            FakeRocket.fakeData()
        )
        runBlocking {
            rockets.forEach {
                db.rocketDAo().insert(it)
            }
            val result = rocketRepository.listRockets(false)
            Assert.assertEquals(true, result is RocketResult.Success)
            Assert.assertEquals(1, (result as RocketResult.Success).data.size)
        }
    }
}