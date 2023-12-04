package id.finale.countrivia.ui.home

import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import id.finale.countrivia.data.adapter.CountryAdapter
import id.finale.countrivia.data.remote.Resource
import id.finale.countrivia.databinding.FragmentHomeBinding
import id.finale.countrivia.extensions.handleApiError
import id.finale.countrivia.extensions.obtainViewModel
import id.finale.countrivia.extensions.visible
import id.finale.countrivia.utilities.autoCleared
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment: Fragment(){
    private var binding: FragmentHomeBinding by autoCleared()
    private lateinit var  countryAdapter : CountryAdapter
    private val tag = HomeFragment::class.java.name
    private val viewModel: HomeViewModel by lazy {
        obtainViewModel(
            requireActivity(),
            HomeViewModel::class.java,
            defaultViewModelProviderFactory
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(tag, "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(
            inflater, container, false
        )
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)

        CountryAdapter{binding, countryModel ->
            val action = HomeFragmentDirections.openCountryDetail(countryModel)

            findNavController()
                .navigate(
                    action,
                    FragmentNavigatorExtras(
                        binding.flagImage to "flag_transition"
                    )
                )
        }.also { countryAdapter = it }

        binding.countryRV.adapter = countryAdapter
        postponeEnterTransition()
        binding.countryRV.viewTreeObserver
            .addOnDrawListener {
            startPostponedEnterTransition()
                return@addOnDrawListener
        }
        viewModel.searchQuery.observe(viewLifecycleOwner){
            countryAdapter.search(it){it1 ->
                binding.errorMessage.visible(it1)
                binding.errorMessage.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        android.R.color.black
                    )
                )
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(tag, "onCreateView")

        viewModel.countries.observe(viewLifecycleOwner) {
            Log.d(tag, "observe")
            if (it !is Resource.Loading)
                when (it) {
                    is Resource.Success -> {
                        lifecycleScope.launch {
                            Log.d(tag,"Size: ${it.value?.size}")
                            countryAdapter.setItem(it.value)
                        }
                    }
                    is Resource.Failure -> {
                        handleApiError(binding.errorMessage, it) { viewModel.fetchAndCacheData() }
                    }
                    else -> {
                    }
                }
        }
    }

//        countryAdapter.onCountryClick{
//            val action = CountryHomeDirections
//                .openCountryDetail(it)
//            findNavController().navigate(action)
//        }

//        viewModel.list.observe(viewLifecycleOwner){
//            countryAdapter.submitData(lifecycle, it)
//        }
    }

//    private fun setRecycleView(){
//        binding.countryRV.apply {
//            adapter = countryAdapter
//            layoutManager = LinearLayoutManager(requireContext())
//        }
//    }

//}
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val root = inflater.inflate(R.layout.fragment_home,
//            container, false)
//        var recyclerView: RecyclerView = root.findViewById(
//            R.id.rv_country
//        )
//        loadData(recyclerView)
//        return root
//    }
//
//    private suspend fun loadData(recyclerView: RecyclerView){
//        CountryApi.getService().getCountryList().enqueue(
//            object : Callback<List<CountryModel>>{
//                override fun onResponse(
//                    call: Call<List<CountryModel>>,
//                    response: Response<List<CountryModel>>
//                ) {
//                    if(response.isSuccessful){
//                        val responseCountry = response.body()
//                        val countryListAdapter = CountryListAdapter(responseCountry)
//                        recyclerView.apply{
//                            layoutManager = LinearLayoutManager(requireContext())
//                            setHasFixedSize(true)
//                            countryListAdapter.notifyDataSetChanged()
//                            adapter = countryListAdapter
//                        }
//                    }
//                }
//
//                override fun onFailure(call: Call<List<CountryModel>>, t: Throwable) {
//                    Toast.makeText(requireContext(),
//                        t.localizedMessage,
//                        Toast.LENGTH_LONG).show()
//                }
//            }
//        )
//    }


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
