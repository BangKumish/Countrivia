package id.finale.countrivia

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import id.finale.countrivia.databinding.ActivityMainBinding
import id.finale.countrivia.ui.home.HomeViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var listener: NavController.OnDestinationChangedListener
    private val homeViewModel by viewModels<HomeViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostController = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        navController = navHostController.navController

        val navView: BottomNavigationView = binding.navView
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        listener = NavController.OnDestinationChangedListener{
                _, destination, _ ->
            if (R.id.navigation_home == destination.id){
                binding.toolbar.inflateMenu(R.menu.menu_main)
                setSearchBar(binding)
            }
            else{
                binding.toolbar.menu.clear()
            }
        }
        navController.addOnDestinationChangedListener(listener)
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        super.onCreateOptionsMenu(menu)

//
//        return true
//    }

    private fun setSearchBar(binding: ActivityMainBinding){
        val menuItem: MenuItem = binding.toolbar.menu.findItem(R.id.actionSearch)
        val searchView: SearchView = menuItem.actionView as SearchView

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                homeViewModel.setSearchQuery(newText)
                return true
            }
        })
    }
}