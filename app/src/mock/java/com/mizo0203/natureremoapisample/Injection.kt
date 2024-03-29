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

import com.mizo0203.natureremoapisample.data.FakeNatureRemoDataSource
import com.mizo0203.natureremoapisample.data.source.NatureRemoDataSource
import com.mizo0203.natureremoapisample.util.AppExecutors

/**
 * Enables injection of mock implementations for
 * [NatureRemoDataSource] at compile time. This is useful for testing, since it allows us to use
 * a fake instance of the class to isolate the dependencies and run a test hermetically.
 */
object Injection {

    fun provideTasksRepository(): NatureRemoDataSource {
        return FakeNatureRemoDataSource.getInstance(AppExecutors())
    }
}
