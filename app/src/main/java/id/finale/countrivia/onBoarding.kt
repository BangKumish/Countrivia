import android.os.Bundle
import android.text.Html
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import id.finale.countrivia.R

class OnBoarding : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var dotsLayout: LinearLayout
    private lateinit var sliderAdapter2: SliderAdapter2
    private lateinit var dots: Array<TextView?>
    private lateinit var letsGetStarted: Button
    private lateinit var animation: Animation
    private var currentPos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_on_boarding)

        viewPager = findViewById(R.id.slider)
        dotsLayout = findViewById(R.id.dots)
        letsGetStarted = findViewById(R.id.get_started_btn)

        sliderAdapter2 = SliderAdapter2(this)
        viewPager.adapter = sliderAdapter2

        addDots(0)
        viewPager.registerOnPageChangeCallback(changeListener)
    }

    fun next(view: View) {
        viewPager.currentItem = currentPos + 1
    }

    private fun addDots(position: Int) {
        dots = arrayOfNulls(3)
        dotsLayout.removeAllViews()

        for (i in dots.indices) {
            dots[i] = TextView(this)
            dots[i]?.text = Html.fromHtml("&#8226;")
            dots[i]?.textSize = 35f

            dotsLayout.addView(dots[i])
        }

        if (dots.isNotEmpty()) {
            dots[position]?.setTextColor(resources.getColor(com.google.android.material.R.color.design_default_color_primary_dark))
        }
    }

    private val changeListener = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            addDots(position)
            currentPos = position

            when (position) {
                0, 1, 2 -> letsGetStarted.visibility = View.INVISIBLE
                else -> {
                    animation = AnimationUtils.loadAnimation(this@OnBoarding, androidx.appcompat.R.anim.abc_fade_in)
                    letsGetStarted.animation = animation
                    letsGetStarted.visibility = View.VISIBLE
                }
            }
        }
    }
}
