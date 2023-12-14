package id.finale.countrivia.onBoarding.entity

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import id.finale.countrivia.R

enum class OnBoardingPage(
    @StringRes val titleResource: Int,
    @StringRes val subTitleResource: Int,
    @StringRes val descriptionResource: Int,
    @DrawableRes val logoResource: Int
) {
    ONE(R.string.onboarding_slide1_title,
        R.string.onboarding_slide1_subtitle,
        R.string.onboarding_slide1_desc,
        R.drawable.american),

    TWO(R.string.onboarding_slide2_title,
        R.string.onboarding_slide2_subtitle,
        R.string.onboarding_slide2_desc,
        R.drawable.qatar),

    THREE(R.string.onboarding_slide2_title,
        R.string.onboarding_slide3_subtitle,
        R.string.onboarding_slide1_desc,
        R.drawable.argentina)

}