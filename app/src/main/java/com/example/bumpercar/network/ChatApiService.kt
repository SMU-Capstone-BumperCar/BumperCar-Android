package com.example.bumpercar.network

import com.example.bumpercar.data.MessageData
import com.example.bumpercar.data.JudgeResponseData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ChatApiService {
    @POST("drive-judge")
    suspend fun postDriveJudge(
        @Body query: MessageData
    ) : Response<JudgeResponseData>
}