package id.finale.countrivia.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.fragment.app.Fragment
import id.finale.countrivia.R
import id.finale.countrivia.data.CountryListAdapter
import id.finale.countrivia.data.CountryModel
import id.finale.countrivia.retrofit.RetroInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment: Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home,
            container, false)
        var recyclerView: RecyclerView = root.findViewById(
            R.id.rv_country
        )
        loadData(recyclerView)
        return root
    }

    private fun loadData(recyclerView: RecyclerView){
        RetroInstance.getService().getCountryList().enqueue(
            object : Callback<List<CountryModel>>{
                override fun onResponse(
                    call: Call<List<CountryModel>>,
                    response: Response<List<CountryModel>>
                ) {
                    if(response.isSuccessful){
                        val responseCountry = response.body()
                        val countryListAdapter = CountryListAdapter(responseCountry)
                        recyclerView.apply{
                            layoutManager = LinearLayoutManager(requireContext())
                            setHasFixedSize(true)
                            countryListAdapter.notifyDataSetChanged()
                            adapter = countryListAdapter
                        }
                    }
                }

                override fun onFailure(call: Call<List<CountryModel>>, t: Throwable) {
                    Toast.makeText(requireContext(),
                        t.localizedMessage,
                        Toast.LENGTH_LONG).show()
                }
            }
        )
    }
}


//class HomeFragment : Fragment(){
//    private lateinit var recyclerAdapter: CountryListAdapter
//    private lateinit var recyclerView: RecyclerView
//    private lateinit var countryArrayList: ArrayList<CountryModel>
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return inflater.inflate(
//            R.layout.fragment_home,
//            container,
//            false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        val layoutManager = LinearLayoutManager(context)
//
//        recyclerView = view.findViewById(R.id.rv_country)
//        recyclerView.layoutManager = layoutManager
//        recyclerView.setHasFixedSize(true)
//        countryArrayList = arrayListOf<CountryModel>()
//        initViewModel()
//        recyclerAdapter = CountryListAdapter(countryArrayList)
//        recyclerView.adapter = recyclerAdapter
//    }
//
//    private fun initViewModel(){
//        val viewModel:HomeViewModel = ViewModelProvider(
//            this).get(
//            HomeViewModel::class.java)
//        viewModel.getLiveDataObserver().observe(viewLifecycleOwner, Observer {
//            if(it != null){
//                recyclerAdapter.setCountryList(it)
//                recyclerAdapter.notifyDataSetChanged()
//            }
//            else{
//                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
//            }
//        })
//        viewModel.makeAPICall()
//    }
//}
//
//class HomeFragment : Fragment() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.fragment_home)
//
//        val country = findViewById<RecyclerView>(R.id.rv_country)
//
//        RetroInstance.getService().getCountryList().enqueue(
//            object : Callback<ResponseCountry> {
//                override fun onResponse(
//                    call: Call<ResponseCountry>,
//                    response: Response<ResponseCountry>
//                ) {
//                    val responseCountry = response.body()
//                    val dataCountry = responseCountry?.results
//                    val countryListAdapter = CountryListAdapter(dataCountry)
//                    country.apply {
//                        layoutManager = LinearLayoutManager(this@HomeFragment)
//                        setHasFixedSize(true)
//                        countryListAdapter.notifyDataSetChanged()
//                        adapter = countryListAdapter
//                    }
//                }
//
//                override fun onFailure(call: Call<ResponseCountry>, t: Throwable) {
//                    Toast.makeText(applicationContext, t.localizedMessage, Toast.LENGTH_SHORT).show()
//                }
//            }
//        )
//    }
//}

//class HomeFragment: Fragment(){
//    private lateinit var recyclerAdapter: CountryListAdapter
//    private lateinit var recyclerView: RecyclerView
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View?{
//        val root = inflater.inflate(
//            R.layout.fragment_home, container, false
//        )
//        initRecyclerView(root)
//        dataInit()
//        return root
//    }
//
//    private fun initRecyclerView(root: View){
//        val recyclerView = root.findViewById<RecyclerView>(R.id.rv_country)
//        recyclerView.layoutManager = LinearLayoutManager(activity)
//        recyclerAdapter = CountryListAdapter(requireContext())
//        recyclerView.adapter = recyclerAdapter
//    }
//
//
//    @SuppressLint("NotifyDataSetChanged")
//    private fun dataInit(){
//        val viewModel: HomeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
//        viewModel.getLiveDataObserver().observe(viewLifecycleOwner, Observer {
//            if(it != null){
//                recyclerAdapter.notifyDataSetChanged()
//            } else {
//                Toast.makeText(context, "ERROR", Toast.LENGTH_SHORT).show()
//            }
//        })
//        viewModel.makeAPICall()
//    }
//}

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
//        val layoutManager = LinearLayoutManager(context)
//        recyclerView = view.findViewById(R.id.rv_country)
//        recyclerView.layoutManager = layoutManager
//        recyclerView.setHasFixedSize(true)
//        dataInit()
//    }
