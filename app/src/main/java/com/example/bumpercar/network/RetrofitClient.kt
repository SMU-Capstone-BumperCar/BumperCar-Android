package com.example.bumpercar.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {
    companion object { // 객체 생성 없이 사용할 수 있도록 함

        private const val mainURL = ""

        private var chatApiService: ChatApiService? = null

        fun getDecendantApi(): ChatApiService {
            if (chatApiService == null) {

                val okHttpClient = OkHttpClient.Builder()
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .readTimeout(15, TimeUnit.SECONDS)
                    .writeTimeout(15, TimeUnit.SECONDS)
                    .build()

                val moshi = Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()

                chatApiService = Retrofit.Builder()
                    .baseUrl(mainURL)
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .client(okHttpClient)
                    .build()
                    .create(ChatApiService::class.java) // IApiService 인터페이스를 사용할 수 있도록 생성
            }
            return chatApiService!! // apiService가 null이 아닌 경우 반환
        }
    }
}