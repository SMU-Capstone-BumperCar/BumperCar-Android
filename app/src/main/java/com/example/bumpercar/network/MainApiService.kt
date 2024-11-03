package com.example.bumpercar.network

import com.example.bumpercar.data.MessageData
import com.example.bumpercar.data.JudgeResponseData
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
        @Body query: MessageData
    ) : Response<JudgeResponseData>
}