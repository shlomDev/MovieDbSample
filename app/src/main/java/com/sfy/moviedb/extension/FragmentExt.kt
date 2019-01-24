package com.sfy.moviedb.extension

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import com.sfy.moviedb.widget.animation.FragmentAnimationType


/**
 * The `fragment` is added to the container view with id `frameId`. The operation is
 * performed by the `supportFragmentManager`.
 */
fun Fragment.replaceFragmentInFragment(fragment: Fragment,
                                  @IdRes frameId: Int,
                                  addToBackStack: Boolean = false,
                                  fragmentAnimationType: FragmentAnimationType? = FragmentAnimationType.LEFT_IN_RIGHT_OUT) {

    childFragmentManager.transact {
        if (addToBackStack) addToBackStack(fragment::class.simpleName)
        fragmentAnimationType?.init(this)
        setReorderingAllowed(true)
        replace(frameId, fragment)
    }
}

/**
 * Runs a FragmentTransaction, then calls commit().
 */
inline fun FragmentManager.transact(action: FragmentTransaction.() -> Unit) {
    beginTransaction().apply {
        action()
    }.commit()
}

/**
 * Get which fragment is displayed
 */
fun Fragment.getCurrentFragment(@IdRes frameId: Int): Fragment? = this.childFragmentManager.findFragmentById(frameId)


fun FragmentManager.clearBackStack() {
    if (backStackEntryCount == 0) return
    popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
}


