package com.extraaedge.assignment.spacerocket.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.extraaedge.assignment.spacerocket.data.model.FakeRocket
import com.extraaedge.assignment.spacerocket.data.remote.MockServer
import com.extraaedge.assignment.spacerocket.data.remote.enqueueFailureResponse
import com.extraaedge.assignment.spacerocket.data.remote.enqueueResponse
import com.extraaedge.assignment.spacerocket.data.RocketRepository
import com.extraaedge.assignment.spacerocket.data.RocketResult
import com.extraaedge.assignment.spacerocket.data.local.RocketDatabase
import com.extraaedge.assignment.spacerocket.data.remote.RocketApi
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.collect
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

    private lateinit var repository: RocketRepository

    @Before
    fun setup() {
        hiltRule.inject()
        repository = RocketRepository(rocketApi, db.rocketDAo())
    }

    @After
    fun tearDown() {
        db.close()
        MockServer.server.shutdown()
    }

    @Test
    fun listRocket_from_remote() {
        MockServer.server.enqueueResponse("rockets_response.json")
        runBlocking {
            repository.listRockets(true).collect { result ->
                Assert.assertEquals(true, result is RocketResult.Success)
                Assert.assertEquals(1, (result as RocketResult.Success).data.size)
            }
        }
    }

    @Test
    fun listRocket_failed_from_remote() {
        MockServer.server.enqueueFailureResponse(HttpURLConnection.HTTP_INTERNAL_ERROR)
        runBlocking {
            repository.listRockets(true).collect{
                Assert.assertEquals(true, it is RocketResult.Error)
            }
        }
    }

    @Test
    fun listRocket_from_local() {
        runBlocking {
            db.rocketDAo().insert(FakeRocket.fakeData())
            repository.listRockets(false).collect { result ->
                Assert.assertEquals(true, result is RocketResult.Success)
                Assert.assertEquals(1, (result as RocketResult.Success).data.size)
            }
        }
    }

    @Test
    fun listEmptyRocket_from_local() {
        runBlocking {
            repository.listRockets(false).collect { result ->
                Assert.assertEquals(true, result is RocketResult.Success)
                Assert.assertEquals(0, (result as RocketResult.Success).data.size)
            }
        }
    }
}