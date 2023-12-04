package id.finale.countrivia.data.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import id.finale.countrivia.data.entities.CountryModel
import id.finale.countrivia.databinding.CountryListRowBinding

class CountryAdapter(
    private val openCountryInfo:(
            binding: CountryListRowBinding,
            countryModel: CountryModel
            ) -> Unit
): RecyclerView.Adapter<CountryAdapter.CountryViewHolder>(), Filterable{


    private val searchableList = ArrayList<CountryModel>()
    private val originalList = ArrayList(searchableList)
    private var searchResultCallBack: ((isDataFound: Boolean) -> Unit)? = null


    inner class CountryViewHolder(
        val view: CountryListRowBinding
    ): RecyclerView.ViewHolder(view.root){
        fun bind(item: CountryModel){
            view.countryModel = item
            view.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding: CountryListRowBinding =
            CountryListRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        val countryViewHolder = CountryViewHolder(binding)
        countryViewHolder.itemView.setOnClickListener{
            openCountryInfo.invoke(
                binding,
                searchableList[countryViewHolder.absoluteAdapterPosition])
        }

        return countryViewHolder
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(searchableList[position])
    }

    override fun getItemCount(): Int = searchableList.size

    override fun getFilter(): Filter {
        return object : Filter(){
            private val filterResults = FilterResults()
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint.toString()
                searchableList.clear()
                if(constraint.isNullOrBlank()){
                    searchableList.addAll(originalList)
                } else {
                    val searchResults = originalList.filter {
                        it.name?.common?.lowercase()?.contains(charString.lowercase())!!
                    }
                    searchableList.addAll(searchResults)
                }

                return filterResults.also { it.values = searchableList }
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                searchResultCallBack?.invoke(searchableList.isEmpty())
                notifyDataSetChanged()
            }
        }
    }

    fun search(s: String, onNothingFound: ((isDataFound: Boolean) -> Unit)?){
        this.searchResultCallBack = onNothingFound
        filter.filter(s)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItem(item: List<CountryModel>?) {
        this.searchableList.clear()
        item?.let {
            this.searchableList.addAll(it)
            originalList.addAll(it)
        }
        notifyDataSetChanged()
    }
}