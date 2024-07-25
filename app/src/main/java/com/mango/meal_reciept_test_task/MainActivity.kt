package com.mango.meal_reciept_test_task

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.os.Build
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.mango.meal_reciept_test_task.ui.fragment.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var splashScreen: SplashScreen? = null
    private val shouldAvoidSplashScreen = Build.VERSION.SDK_INT <= Build.VERSION_CODES.S
    private var dismissSplash = false


    override fun onCreate(savedInstanceState: Bundle?) {
        if (shouldAvoidSplashScreen) setTheme(R.style.Theme_Mealreciepttesttask) else {
            splashScreen = initSplashScreen()
        }
        super.onCreate(savedInstanceState)
        if (shouldAvoidSplashScreen.not()) splashScreen?.run {
            setKeepOnScreenCondition { dismissSplash }
        }
        setContentView(R.layout.activity_main)



    }

    private fun initSplashScreen() = installSplashScreen().apply {

        val splashScreen = installSplashScreen()
        splashScreen.setOnExitAnimationListener { view ->
            view.view.let { icon ->
                val animator = ValueAnimator
                    .ofInt(icon.bottom, 0)
                    .setDuration(1000)
                animator.addUpdateListener {
                    val value = it.animatedValue as Int
                    icon.layoutParams.width = value
                    icon.layoutParams.height = value
                    icon.requestLayout()
                }
                val animationSet = AnimatorSet()
                animationSet.interpolator = AccelerateDecelerateInterpolator()
                animationSet.play(animator)
                animationSet.start()
            }

        }
    }


}