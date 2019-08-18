package com.mizo0203.natureremoapisample.data

@Suppress("unused")
class IRSignal(
        /**
         * Freq
         *
         *
         * minimum: 30
         * maximum: 80
         * default: 38
         *
         *
         * IR sub carrier frequency.
         */
        val freq: Int,
        /**
         * IR signal consists of on/off of sub carrier frequency.
         * When receiving IR, Remo measures on to off, off to on time intervals and records the time interval sequence.
         * When sending IR, Remo turns on/off the sub carrier frequency with the provided time interval sequence.
         */
        val data: IntArray,
        /**
         * Format
         *
         *
         * default: us
         *
         *
         * Format and unit of values in the data array.
         * Fixed to us, which means each integer of data array is in microseconds.
         */
        val format: String)
