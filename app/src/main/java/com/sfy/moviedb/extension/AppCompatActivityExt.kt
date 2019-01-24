/*
 * Copyright (C) 2017 The Android Open Source Project
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
package com.sfy.moviedb.extension


import android.support.v7.app.AppCompatActivity
import android.content.Context
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.sfy.moviedb.widget.animation.FragmentAnimationType


/**
 * The `fragment` is added to the container view with id `frameId`. The operation is
 * performed by the `supportFragmentManager`.
 */
fun AppCompatActivity.replaceFragmentInActivity(fragment: Fragment,
                                                @IdRes frameId: Int,
                                                addToBackStack: Boolean = false,
                                                fragmentAnimationType: FragmentAnimationType? = FragmentAnimationType.LEFT_IN_RIGHT_OUT) {

    supportFragmentManager.transact {
        if (addToBackStack) addToBackStack(fragment::class.qualifiedName)
        fragmentAnimationType?.init(this)
        replace(frameId, fragment)
    }
}


/**
 * The `fragment` is added to the container view with TAG. The operation is
 * performed by the `fragmentManager`.
 */
fun AppCompatActivity.addFragmentToActivity(fragment: Fragment, tag: String) {
    supportFragmentManager.transact {
        add(fragment, tag)
    }
}

fun AppCompatActivity.openKeyboard(view: View){
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
}

fun AppCompatActivity.getCurrentFragment(@IdRes frameId: Int):Fragment? = supportFragmentManager.findFragmentById(frameId)


