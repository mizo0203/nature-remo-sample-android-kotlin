/*
 * Copyright 2019, Satoki Mizoguchi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mizo0203.natureremoapisample.data

import com.mizo0203.natureremoapisample.data.source.NatureRemoDataSource
import com.mizo0203.natureremoapisample.data.source.NatureRemoRepository.Companion.getInstance
import com.mizo0203.natureremoapisample.util.AppExecutors

/**
 * Implementation of a remote data source with static access to the data for easy testing.
 */
class FakeNatureRemoDataSource(
        private val appExecutors: AppExecutors
) : NatureRemoDataSource {

    override fun getMessages(callback: NatureRemoDataSource.Callback<IRSignal>) {
        appExecutors.mainThread.execute { callback.onDataNotAvailable() }
    }

    override fun postMessages(message: IRSignal, callback: NatureRemoDataSource.Callback<Unit>) {
        appExecutors.mainThread.execute { callback.onLoaded(Unit) }
    }

    companion object {

        private var INSTANCE: FakeNatureRemoDataSource? = null

        /**
         * Returns the single instance of this class, creating it if necessary.
         *
         * @return the [FakeNatureRemoDataSource] instance
         */
        @JvmStatic
        fun getInstance(appExecutors: AppExecutors) =
                INSTANCE
                        ?: synchronized(FakeNatureRemoDataSource::class.java) {
                            INSTANCE
                                    ?: FakeNatureRemoDataSource(appExecutors)
                                            .also { INSTANCE = it }
                        }


        /**
         * Used to force [getInstance] to create a new instance
         * next time it's called.
         */
        @Suppress("unused")
        @JvmStatic
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
