package com.extraaedge.assignment.spacerocket.com.extraaedge.assignment.spacerocket.data.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.extraaedge.assignment.spacerocket.data.remote.RocketApi
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.*
import java.nio.charset.StandardCharsets
import javax.inject.Inject
import javax.inject.Named

@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
class RocketApiTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("FakeRocketApi")
    lateinit var rocketApi: RocketApi

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun listRocketApiTest() = runTest {
        MockServer.server.enqueueResponse("rockets_response.json", 200)
        val response = rocketApi.listRockets()
        MockServer.server.takeRequest()
        Assert.assertEquals(1, response.body()?.size)
    }

    @After
    fun tearDown() {
        MockServer.server.shutdown()
    }
}