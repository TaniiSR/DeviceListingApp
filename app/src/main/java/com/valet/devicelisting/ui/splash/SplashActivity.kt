package com.valet.devicelisting.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.viewModels
import com.valet.devicelisting.databinding.ActivitySplashBinding
import com.valet.devicelisting.ui.homemain.HomeMainActivity
import com.valet.devicelisting.utils.base.BaseActivity
import com.valet.devicelisting.utils.extensions.hideSystemUI
import com.valet.devicelisting.utils.extensions.showSystemUI
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity :
    BaseActivity<ActivitySplashBinding, ISplash.State, ISplash.ViewModel>(),
    ISplash.View {
    override fun getViewBinding(): ActivitySplashBinding =
        ActivitySplashBinding.inflate(layoutInflater)

    override val viewModel: ISplash.ViewModel by viewModels<SplashVM>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideSystemUI(mViewBinding.root)
        initCountDownTimer(2)
    }

    private fun initCountDownTimer(time: Int) {
        object : CountDownTimer(time.times(1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) = Unit

            override fun onFinish() {
                startHomeActivity()
            }
        }.start()
    }

    private fun startHomeActivity() {
        showSystemUI(mViewBinding.root)
        val intent = Intent(this@SplashActivity, HomeMainActivity::class.java)
        activityLauncher.launch(intent)
        finish()
    }
}