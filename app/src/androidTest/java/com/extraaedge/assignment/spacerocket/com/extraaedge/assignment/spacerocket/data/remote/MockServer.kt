package com.extraaedge.assignment.spacerocket.com.extraaedge.assignment.spacerocket.data.remote

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import java.net.HttpURLConnection
import java.nio.charset.StandardCharsets

class MockServer {
    companion object {
        val server = MockWebServer()
    }
}

fun MockWebServer.enqueueResponse(fileName: String) {
    val inputStream = javaClass.classLoader?.getResourceAsStream(fileName)
    inputStream?.source()?.buffer()?.let { source ->
        enqueue(
            MockResponse().setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(source.readString(StandardCharsets.UTF_8))
        )
    }
}

fun MockWebServer.enqueueFailureResponse(responseCode: Int) {
    enqueue(MockResponse().setResponseCode(responseCode))
}