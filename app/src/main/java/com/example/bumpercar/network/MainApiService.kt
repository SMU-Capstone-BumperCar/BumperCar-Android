package com.example.bumpercar.network

import com.example.bumpercar.data.MessageData
import com.example.bumpercar.data.JudgeResponseData
import com.example.bumpercar.data.ReviewData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface MainApiService {
    @POST("drive-judge")
    suspend fun postDriveJudge(
        @Body query: MessageData
    ) : Response<JudgeResponseData>

    @POST("summarize_review")
    suspend fun postReview(
        @Body body: Map<String, String> // JSON 객체 형태로 변경
    ): Response<ReviewData>
}