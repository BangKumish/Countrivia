package id.finale.countrivia.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import id.finale.countrivia.databinding.FragmentDashboardBinding
import id.finale.countrivia.di.data.user.UserDao
import id.finale.countrivia.di.extensions.obtainViewModel
import id.finale.countrivia.ui.activity.EditProfile
import id.finale.countrivia.ui.activity.LoginActivity

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

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = userDao.getActiveUser()

        binding.usernameText.text = user.userName
        binding.userEmail.text = user.email
        binding.userNIM.text = user.nim

        binding.btnLogout.setOnClickListener {
            btnLogout()
        }
         binding.updateButton.setOnClickListener {
             btnUpdate()
         }
    }

    private fun btnLogout() {
        userDao.deActiveUser()
        val intent = Intent(context, LoginActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }

    private fun btnUpdate(){
        val intent = Intent(context, EditProfile::class.java)
        startActivity(intent)
    }
}