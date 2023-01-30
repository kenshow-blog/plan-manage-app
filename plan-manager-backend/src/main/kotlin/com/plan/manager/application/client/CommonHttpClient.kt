package com.plan.manager.application.client

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.apache.http.NameValuePair
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.utils.URIBuilder
import org.springframework.stereotype.Repository
import java.net.http.HttpClient
import java.net.http.HttpClient.Version
import java.net.http.HttpRequest
import java.net.http.HttpResponse

@Suppress("SpringJavaInjectionPointsAutowiringInspection")
@Repository
class CommonHttpClient {
    private val httpClient = HttpClient.newBuilder()
            .version(Version.HTTP_1_1)
            .build()
    fun <V> get(
            uri: String,
            headers: Map<String, String>,
            queryParameters: List<NameValuePair>,
            dto: Class<V>
    ) : V {
        val uriBuild = URIBuilder(HttpGet(uri).uri)
                .addParameters(queryParameters)
                .build()
        val httpRequestBuilder = HttpRequest.newBuilder(uriBuild)
                .GET()
        headers.forEach {
            httpRequestBuilder.header(it.key,it.value)
        }
        val httpRequest = httpRequestBuilder.build()
        val jsonResponse = this.httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString()).body()
        return jacksonObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).readValue(jsonResponse, dto)
    }
}