package com.annasolox.weather

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.annasolox.weather.core.navigation.Navigation
import com.annasolox.weather.ui.theme.WeatherTheme
import com.annasolox.weather.ui.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<WeatherViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                !viewModel.isReady.value
            }
            setOnExitAnimationListener { screen ->
                val scaleX = ObjectAnimator.ofFloat(screen.iconView, View.SCALE_X, .6f, 0.3f)
                val scaleY = ObjectAnimator.ofFloat(screen.iconView, View.SCALE_Y, .6f, 0.3f)
                val opacity = ObjectAnimator.ofFloat(screen.iconView, View.ALPHA, 1f, 0f)

                AnimatorSet().apply {
                    playTogether(scaleX, scaleY, opacity)
                    duration = 1200L
                    interpolator = OvershootInterpolator()
                    doOnEnd {
                        screen.remove()
                    }
                    start()
                }
            }
        }

        enableEdgeToEdge()
        setContent {
            WeatherTheme {
                Navigation()
            }
        }
    }
}