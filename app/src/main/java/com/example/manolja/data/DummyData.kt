package com.example.manolja.data

import RecordEntity
import com.example.manolja.data.entity.UserEntity

// RecordEntity 더미 데이터
val dummyRecords = listOf(
    RecordEntity(
        recordId = 1,
        title = "Mountain Hike",
        content = "Completed a 10km hike up the mountain.",
        imagePath = "/images/hike.png"
    ),
    RecordEntity(
        recordId = 2,
        title = "City Marathon",
        content = "Finished the city marathon in 3 hours.",
        imagePath = "/images/marathon.png"
    ),
    RecordEntity(
        recordId = 3,
        title = "Beach Cleanup",
        content = "Participated in a beach cleanup event.",
        imagePath = "/images/beach_cleanup.png"
    )
)

// UserEntity 더미 데이터
val dummyUsers = listOf(
    UserEntity(
        nickname = "김민혁",
        uuid = "uuid_kyle_123",
        exp = 2000,
    ),
    UserEntity(
        nickname = "김정희",
        uuid = "uuid_jenny_456",
        exp = 1500,
    ),
    UserEntity(
        nickname = "김현민",
        uuid = "uuid_sheep_789",
        exp = 2500,
    ),
    UserEntity(
        nickname = "임경윤",
        uuid = "uuid_taewoong_789",
        exp = 2000,
    ),
    UserEntity(
        nickname = "김주송",
        uuid = "uuid_joosoi_789",
        exp = 2500,
    ),
    UserEntity(
        nickname = "박순홍",
        uuid = "uuid_estj_789",
        exp = 2500,
    )

)

