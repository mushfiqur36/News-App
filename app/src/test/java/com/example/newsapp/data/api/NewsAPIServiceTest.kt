package com.example.newsapp.data.api

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsAPIServiceTest {
    private lateinit var service: NewsAPIService
    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsAPIService::class.java)

    }

    private fun enqueueMockResponse(
        fileName: String
    ) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        server.enqueue(mockResponse)
    }

    @Test
    fun getTopHeadLines_sentRequest_receivedExpected() {
        runBlocking {
            // given
            enqueueMockResponse("newsresponse.json")

            // when
            val responseBody = service.getTopHeadLines("us", 1).body()
            val request = server.takeRequest()

            // then
            assertThat(responseBody).isNotNull()
            assertThat(request.path).isEqualTo("/v2/top-headlines?country=us&page=1&apiKey=ab059a192fbb4479ab5c0de03c693c09")
        }
    }

    @Test
    fun getTopHeadlines_receivedResponse_correctPageSize() {
        runBlocking {
            // given
            enqueueMockResponse("newsresponse.json")

            // when
            val responseBody = service.getTopHeadLines("us", 1).body()
            val articleList = responseBody!!.articles

            // then
            assertThat(articleList.size).isEqualTo(20)
        }
    }

    @Test
    fun getTopHeadlines_receivedResponse_correctContent() {
        runBlocking {
            // given
            enqueueMockResponse("newsresponse.json")

            // when
            val responseBody = service.getTopHeadLines("us", 1).body()
            val articleList = responseBody!!.articles
            val article = articleList[0]

            // then
            assertThat(article.author).isEqualTo("John Keim")
            assertThat(article.url).isEqualTo("https://www.espn.com/nfl/story/_/id/39667086/source-te-zach-ertz-reaches-one-year-deal-commanders")
            assertThat(article.publishedAt).isEqualTo("2024-03-06T19:01:00Z")
        }
    }


    @After
    fun tearDown() {
        server.shutdown()
    }
}