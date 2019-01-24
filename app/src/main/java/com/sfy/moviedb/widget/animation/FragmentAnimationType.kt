package com.sfy.moviedb.widget.animation

import android.support.annotation.AnimRes
import android.support.annotation.AnimatorRes
import android.support.v4.app.FragmentTransaction
import com.sfy.moviedb.R

enum class FragmentAnimationType(@AnimatorRes @AnimRes private val enter: Int = 0,
                                 @AnimatorRes @AnimRes private val exit: Int = 0,
                                 @AnimatorRes @AnimRes private val popEnter: Int? = null,
                                 @AnimatorRes @AnimRes private val popExit: Int? = null) {
    FADE(android.R.animator.fade_in, android.R.animator.fade_out, android.R.animator.fade_in, android.R.animator.fade_out),
    LEFT_IN_RIGHT_OUT(R.anim.enter_from_right, R.anim.exit_to_left),
    RIGHT_IN_LEFT_OUT(R.anim.exit_to_left, R.anim.enter_from_right);


    fun init(fragmentTransaction: FragmentTransaction) {
        if (popEnter != null && popExit != null) fragmentTransaction.setCustomAnimations(enter, exit, popEnter, popExit)
        else fragmentTransaction.setCustomAnimations(enter, exit)
    }
}
