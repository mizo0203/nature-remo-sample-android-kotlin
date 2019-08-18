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

package com.mizo0203.natureremoapisample

import com.mizo0203.natureremoapisample.data.source.NatureRemoDataSource
import com.mizo0203.natureremoapisample.data.source.NatureRemoLocalApiClient
import com.mizo0203.natureremoapisample.data.source.NatureRemoRepository
import com.mizo0203.natureremoapisample.util.AppExecutors

/**
 * Enables injection of production implementations for
 * [TasksDataSource] at compile time.
 */
object Injection {

    // TODO: NATURE_REMO_IP_ADDRESS をセット
    private const val NATURE_REMO_IP_ADDRESS = "192.168.2.15"

    fun provideTasksRepository(): NatureRemoDataSource {
        val localApiClient = NatureRemoLocalApiClient(NATURE_REMO_IP_ADDRESS)
        return NatureRemoRepository.getInstance(AppExecutors(), localApiClient)
    }
}
