package com.extraaedge.assignment.spacerocket.com.extraaedge.assignment.spacerocket.data.remote

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import java.nio.charset.StandardCharsets

class MockServer {
    companion object {
        val server = MockWebServer()
    }
}

fun MockWebServer.enqueueResponse(fileName: String, responseCode: Int) {
    val inputStream = javaClass.classLoader?.getResourceAsStream(fileName)
    inputStream?.source()?.buffer()?.let { source ->
        enqueue(
            MockResponse().setResponseCode(responseCode)
                .setBody(source.readString(StandardCharsets.UTF_8))
        )
    }
}