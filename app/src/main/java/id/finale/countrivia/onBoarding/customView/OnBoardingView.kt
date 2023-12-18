package id.finale.countrivia.onBoarding.customView

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.viewpager2.widget.ViewPager2
import id.finale.countrivia.activity.LoginActivity
import id.finale.countrivia.data.local.pref.DataStoreManager
import id.finale.countrivia.databinding.OnboardingViewBinding
import id.finale.countrivia.onBoarding.OnBoardingPagerAdapter
import id.finale.countrivia.onBoarding.entity.OnBoardingPage
import id.finale.countrivia.onBoarding.onBoardingHelper.core.setParallaxTransformation

class OnBoardingView @JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
): FrameLayout (context, attrs, defStyleAttr, defStyleRes){
    private val numberOfPages by lazy { OnBoardingPage.values().size }
    private val prefManager: DataStoreManager


    init {
        val binding = OnboardingViewBinding.inflate(LayoutInflater.from(context), this, true)
        with(binding) {
            setUpSlider()
            addingButtonsClickListeners()
            prefManager = DataStoreManager(root.context)
        }

    }

    private fun OnboardingViewBinding.setUpSlider() {
        with(slider) {
            adapter = OnBoardingPagerAdapter()

            setPageTransformer { page, position ->
                setParallaxTransformation(page, position)
            }

            addSlideChangeListener()

            val wormDotsIndicator = pageIndicator
            wormDotsIndicator.attachTo(this)
        }
    }


    private fun OnboardingViewBinding.addSlideChangeListener() {

        slider.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                if (numberOfPages > 1) {
                    val newProgress = (position + positionOffset) / (numberOfPages - 1)
                    onboardingRoot.progress = newProgress
                }
            }
        })
    }

    private fun OnboardingViewBinding.addingButtonsClickListeners() {
        nextBtn.setOnClickListener { navigateToNextSlide(slider) }
        skipBtn.setOnClickListener {
            setFirstTimeLaunchToFalse()
            goToLogin(context)
        }
        startBtn.setOnClickListener {
            setFirstTimeLaunchToFalse()
            goToLogin(context)
        }
    }

    private fun setFirstTimeLaunchToFalse() {
        prefManager.isFirstTimeLaunch = false
    }

    private fun navigateToNextSlide(slider: ViewPager2?) {
        val nextSlidePos: Int = slider?.currentItem?.plus(1) ?: 0
        slider?.setCurrentItem(nextSlidePos, true)
    }

    private fun goToLogin(context: Context){
        val intent = Intent(context, LoginActivity::class.java)
        context.startActivity(intent)
    }
}