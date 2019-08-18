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

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mizo0203.natureremoapisample.data.source.NatureRemoDataSource
import com.mizo0203.natureremoapisample.panasonic.tv.RemoteControlButtonType

/**
 * Exposes the data to be used in the task list screen.
 *
 *
 * [BaseObservable] implements a listener registration mechanism which is notified when a
 * property changes. This is done by assigning a [Bindable] annotation to the property's
 * getter method.
 */
class MainViewModel(
        private val natureRemoDataSource: NatureRemoDataSource
) : ViewModel() {

    private val _snackbarText = MutableLiveData<Event<Int>>()
    val snackbarMessage: LiveData<Event<Int>>
        get() = _snackbarText

    private fun showSuccess() {
        showSnackbarMessage(R.string.success)
    }

    private fun showFailure() {
        showSnackbarMessage(R.string.failure)
    }

    /**
     * Called by the Data Binding library and the FAB's click listener.
     */
    fun addNewTask(v: View) {
        when (v.id) {
            R.id.button_1 -> RemoteControlButtonType.NUM_1
            R.id.button_2 -> RemoteControlButtonType.NUM_2
            R.id.button_3 -> RemoteControlButtonType.NUM_3
            R.id.button_4 -> RemoteControlButtonType.NUM_4
            R.id.button_5 -> RemoteControlButtonType.NUM_5
            R.id.button_6 -> RemoteControlButtonType.NUM_6
            R.id.button_7 -> RemoteControlButtonType.NUM_7
            R.id.button_8 -> RemoteControlButtonType.NUM_8
            R.id.button_9 -> RemoteControlButtonType.NUM_9
            R.id.button_10 -> RemoteControlButtonType.NUM_10
            R.id.button_11 -> RemoteControlButtonType.NUM_11
            R.id.button_12 -> RemoteControlButtonType.NUM_12
            R.id.button_ch_up -> RemoteControlButtonType.CH_UP
            R.id.button_ch_down -> RemoteControlButtonType.CH_DOWN
            R.id.button_vol_up -> RemoteControlButtonType.VOL_UP
            R.id.button_vol_down -> RemoteControlButtonType.VOL_DOWN
            else -> null
        }?.let {
            natureRemoDataSource.postMessages(it, object : NatureRemoDataSource.Callback<Unit> {
                override fun onLoaded(responses: Unit) {
                    showSuccess()
                }

                override fun onDataNotAvailable() {
                    showFailure()
                }
            })
        }
    }

    private fun showSnackbarMessage(message: Int) {
        _snackbarText.value = Event(message)
    }
}
