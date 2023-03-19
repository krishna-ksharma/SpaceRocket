package com.extraaedge.assignment.spacerocket.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.extraaedge.assignment.spacerocket.data.remote.SpaceApi
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.*
import javax.inject.Inject

@HiltAndroidTest
class SpaceApiTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var mockWebServer: MockWebServer

    @Inject
    lateinit var spaceApi: SpaceApi

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
    }

    @Test
    fun listRocketApiTest() = runTest {
        val mockResponse = MockResponse();
        mockResponse.setBody("[]")
        mockWebServer.enqueue(mockResponse)
        val response = spaceApi.listRockets()
        mockWebServer.takeRequest()
        Assert.assertEquals(true, response.body()?.isEmpty())
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}