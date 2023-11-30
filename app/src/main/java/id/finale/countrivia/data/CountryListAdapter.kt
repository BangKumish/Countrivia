package id.finale.countrivia.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.finale.countrivia.R

//class CountryListAdapter(private var dataCountry: List<CountryModel?>?):RecyclerView.Adapter<CountryListAdapter.MyViewHolder>(){
//    class MyViewHolder(view: View) :RecyclerView.ViewHolder(view){
//        val flagImage: AppCompatImageView = view.findViewById(R.id.flagImage)
//        val tvName:TextView = view.findViewById(R.id.tvName)
//        val tvCapital:TextView = view.findViewById(R.id.tvCapital)
//        val tvPopulation:TextView = view.findViewById(R.id.tvPopulation)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.country_list_row, parent, false)
//        return MyViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        holder.tvName.text = dataCountry?.get(position)?.name
//        holder.tvCapital.text = "Capital: "+ dataCountry?.get(position)?.capital
//        holder.tvPopulation.text = "Region: "+ dataCountry?.get(position)?.population
//
//        Glide.with(holder.flagImage)
//            .load(dataCountry?.get(position)?.flag)
//            .error(R.drawable.ic_launcher_background)
//            .into(holder.flagImage)
//
//        holder.itemView.setOnClickListener{
//            val name = dataCountry?.get(position)?.name
//            Toast.makeText(holder.itemView.context, "$name", Toast.LENGTH_SHORT).show()
//        }
//    }
//
//    override fun getItemCount(): Int {
//        if (dataCountry != null){
//            return dataCountry!!.size
//        }
//        return 0
//    }
//    fun setDataCountry(dataCountry: ResponseCountry){
//        this.dataCountry = dataCountry
//    }
//}

class CountryListAdapter(private val dataCountry: List<CountryModel?>?) : RecyclerView.Adapter<CountryListAdapter.MyViewHolder>() {
    class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        val flagImage: ImageView = view.findViewById(R.id.flagImage)
        val tvName:TextView = view.findViewById(R.id.tvName)
        val tvCapital:TextView = view.findViewById(R.id.tvCapital)
        val tvPopulation:TextView = view.findViewById(R.id.tvPopulation)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.country_list_row, parent, false
        )
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvName.text = dataCountry?.get(position)?.name?.official
        holder.tvCapital.text = dataCountry?.get(position)?.capital?.get(index = 0)
        holder.tvPopulation.text = dataCountry?.get(position)?.population

        Glide.with(holder.flagImage)
            .load(dataCountry?.get(position)?.flags?.png)
            .error(R.drawable.ic_launcher_background)
            .into(holder.flagImage)

        holder.itemView.setOnClickListener{
            val name = dataCountry?.get(position)?.name?.official
            Toast.makeText(holder.itemView.context, "$name", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        if (dataCountry != null){
            return dataCountry.size
        }
        return 0
    }
}
