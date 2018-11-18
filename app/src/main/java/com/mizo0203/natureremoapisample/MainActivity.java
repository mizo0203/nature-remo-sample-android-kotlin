/*
 * Copyright 2018, Satoki Mizoguchi
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

package com.mizo0203.natureremoapisample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import static com.google.common.base.Preconditions.checkNotNull;

public class MainActivity extends AppCompatActivity implements MainContract.View, View.OnClickListener {

    // TODO: TOKEN, APPLIANCE_ID, SIGNAL_ID をセット
    private static final String TOKEN = "";
    public static final String APPLIANCE_ID = "";
    public static final String SIGNAL_ID = "";
    private MainContract.Presenter mPresenter;

    private boolean mActive = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button_1).setOnClickListener(this);
        findViewById(R.id.button_2).setOnClickListener(this);
        findViewById(R.id.button_3).setOnClickListener(this);
        findViewById(R.id.button_4).setOnClickListener(this);
        findViewById(R.id.button_5).setOnClickListener(this);
        findViewById(R.id.button_6).setOnClickListener(this);
        findViewById(R.id.button_7).setOnClickListener(this);
        findViewById(R.id.button_8).setOnClickListener(this);
        findViewById(R.id.button_9).setOnClickListener(this);
        findViewById(R.id.button_10).setOnClickListener(this);
        findViewById(R.id.button_11).setOnClickListener(this);
        findViewById(R.id.button_12).setOnClickListener(this);

        // Create the presenter
        mPresenter = new MainPresenter(Injection.provideTasksRepository(getApplicationContext(), TOKEN), this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
        mActive = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        mActive = false;
    }

    @Override
    public void setPresenter(@NonNull MainContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.result(requestCode, resultCode);
    }

    @Override
    public void showSuccess() {
        Toast.makeText(getApplicationContext(), R.string.success, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailure() {
        Toast.makeText(getApplicationContext(), R.string.failure, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean isActive() {
        return mActive;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_1:
                mPresenter.sendButtonEvent(ButtonType.NUM_1);
                break;
            case R.id.button_2:
                mPresenter.sendButtonEvent(ButtonType.NUM_2);
                break;
            case R.id.button_3:
                mPresenter.sendButtonEvent(ButtonType.NUM_3);
                break;
            case R.id.button_4:
                mPresenter.sendButtonEvent(ButtonType.NUM_4);
                break;
            case R.id.button_5:
                mPresenter.sendButtonEvent(ButtonType.NUM_5);
                break;
            case R.id.button_6:
                mPresenter.sendButtonEvent(ButtonType.NUM_6);
                break;
            case R.id.button_7:
                mPresenter.sendButtonEvent(ButtonType.NUM_7);
                break;
            case R.id.button_8:
                mPresenter.sendButtonEvent(ButtonType.NUM_8);
                break;
            case R.id.button_9:
                mPresenter.sendButtonEvent(ButtonType.NUM_9);
                break;
            case R.id.button_10:
                mPresenter.sendButtonEvent(ButtonType.NUM_10);
                break;
            case R.id.button_11:
                mPresenter.sendButtonEvent(ButtonType.NUM_11);
                break;
            case R.id.button_12:
                mPresenter.sendButtonEvent(ButtonType.NUM_12);
                break;
        }
    }
}