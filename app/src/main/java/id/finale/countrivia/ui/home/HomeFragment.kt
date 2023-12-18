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
}