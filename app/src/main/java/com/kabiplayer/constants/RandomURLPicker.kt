package com.kabiplayer.constants

import kotlin.random.Random

class RandomURLPicker {
    companion object {
        private val stringArray = arrayOf(
            "https://storage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
            "https://storage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
            "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4",
            "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4",
            "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4",
            "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4",
            "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4",
            "https://storage.googleapis.com/gtv-videos-bucket/sample/SubaruOutbackOnStreetAndDirt.mp4",
            "https://storage.googleapis.com/gtv-videos-bucket/sample/TearsOfSteel.mp4"
        )

        fun getRandomString(): String {
            val randomIndex = Random.nextInt(stringArray.size)
            return stringArray[randomIndex]
        }
    }
}