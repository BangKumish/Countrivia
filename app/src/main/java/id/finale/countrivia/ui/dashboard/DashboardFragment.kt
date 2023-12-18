package id.finale.countrivia.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import id.finale.countrivia.activity.LoginActivity
import id.finale.countrivia.data.user.UserDao
import id.finale.countrivia.databinding.FragmentDashboardBinding
import id.finale.countrivia.extensions.obtainViewModel

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val dashboardViewModel: DashboardViewModel by lazy {
        obtainViewModel(
            requireActivity(),
            DashboardViewModel::class.java,
            defaultViewModelProviderFactory
        )
    }

    private lateinit var userDao: UserDao
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userDao = dashboardViewModel.getUserDao()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this)[DashboardViewModel::class.java]

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.usernameText.text = dashboardViewModel.user.userName
        binding.emailText.text = dashboardViewModel.user.email

        binding.btnLogout.setOnClickListener {
                btnLogout()
        }
    }

    private fun btnLogout(){
        userDao.deActiveUser()
        val intent = Intent(context, LoginActivity::class.java)
            startActivity(intent)
            activity?.finish()
    }
}