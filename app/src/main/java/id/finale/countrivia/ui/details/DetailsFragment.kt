package id.finale.countrivia.ui.details

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import id.finale.countrivia.databinding.FragmentDetailsBinding
import id.finale.countrivia.extensions.obtainViewModel


@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val args: DetailsFragmentArgs by navArgs()
    private val countryViewModel: DetailsViewModel by lazy {
        obtainViewModel(
            requireActivity(),
            DetailsViewModel::class.java,
            defaultViewModelProviderFactory
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)

        val countryModel = args.countryModel
        binding.viewmodel = countryViewModel
        countryViewModel.countryModel.set(countryModel)
        binding.executePendingBindings()

    }

}