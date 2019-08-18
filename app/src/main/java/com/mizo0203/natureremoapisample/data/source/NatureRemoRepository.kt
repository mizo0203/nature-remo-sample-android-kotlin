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
package com.mizo0203.natureremoapisample.data.source

import com.mizo0203.natureremoapisample.data.IRSignal
import com.mizo0203.natureremoapisample.util.AppExecutors

/**
 * Concrete implementation to load tasks from the data sources into a cache.
 *
 *
 * For simplicity, this implements a dumb synchronisation between locally persisted data and data
 * obtained from the server, by using the remote data source only if the local database doesn't
 * exist or is empty.
 */
class NatureRemoRepository(
        private val appExecutors: AppExecutors,
        private val localApiClient: NatureRemoLocalApiClient
) : NatureRemoDataSource {

    override fun getMessages(callback: NatureRemoDataSource.Callback<IRSignal>) {
        appExecutors.networkIO.execute {
            val message = localApiClient.getMessages()
            appExecutors.mainThread.execute {
                message?.let { callback.onLoaded(it) } ?: callback.onDataNotAvailable()
            }
        }
    }

    override fun postMessages(message: IRSignal, callback: NatureRemoDataSource.Callback<Unit>) {
        appExecutors.networkIO.execute {
            val unit = localApiClient.postMessages(message)
            appExecutors.mainThread.execute {
                unit?.let { callback.onLoaded(it) } ?: callback.onDataNotAvailable()
            }
        }
    }

    companion object {

        private var INSTANCE: NatureRemoRepository? = null

        /**
         * Returns the single instance of this class, creating it if necessary.
         *
         * @return the [NatureRemoRepository] instance
         */
        @JvmStatic
        fun getInstance(appExecutors: AppExecutors,
                        localApiClient: NatureRemoLocalApiClient) =
                INSTANCE
                        ?: synchronized(NatureRemoRepository::class.java) {
                    INSTANCE
                            ?: NatureRemoRepository(appExecutors, localApiClient)
                            .also { INSTANCE = it }
                }


        /**
         * Used to force [getInstance] to create a new instance
         * next time it's called.
         */
        @Suppress("unused")
        @JvmStatic fun destroyInstance() {
            INSTANCE = null
        }
    }
}
