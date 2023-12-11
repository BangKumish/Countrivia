import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter
import id.finale.countrivia.R

class SliderAdapter2(private val context: Context) : PagerAdapter() {

    private val images = arrayOf(
        R.drawable.russia,
        R.drawable.uk,
        R.drawable.usa
    )

    private val headings = arrayOf(
        R.string.russia,
        R.string.uk,
        R.string.usa
    )

    private var layoutInflater: LayoutInflater? = null

    override fun getCount(): Int {
        return headings.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object` as ConstraintLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = layoutInflater!!.inflate(R.layout.slides_layout, container, false)

        val imageView: ImageView = view.findViewById(R.id.slider_image)
        val heading: TextView = view.findViewById(R.id.slider_heading)

        imageView.setImageResource(images[position])
        heading.setText(headings[position])

        container.addView(view)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ConstraintLayout)
    }
}
