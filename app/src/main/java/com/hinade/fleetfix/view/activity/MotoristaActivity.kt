package com.hinade.fleetfix.view.activity

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.navigation.NavigationView
import com.hinade.fleetfix.R
import com.hinade.fleetfix.databinding.ActivityMotoristaBinding
import com.hinade.fleetfix.view.fragment.mecanico.ServicosVeiculoFragment
import com.hinade.fleetfix.view.fragment.motorista.VeiculosFragment

class MotoristaActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMotoristaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMotoristaBinding.inflate(layoutInflater)

        with(binding) {
            setContentView(root)
            ViewCompat.setOnApplyWindowInsetsListener(root.rootView) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }

            setSupportActionBar(toolbar)

            val toggle = ActionBarDrawerToggle(
                this@MotoristaActivity,
                drawerLayout,
                toolbar,
                R.string.abrir_menu,
                R.string.fechar_menu
            )
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()

            val navController =
                supportFragmentManager.findFragmentById(R.id.motoristaFragmentContainer)!!
                    .findNavController()
            setupActionBarWithNavController(
                navController,
                AppBarConfiguration(navController.graph, drawerLayout)
            )

            if (savedInstanceState == null) {
                replaceWithFragment(
                    VeiculosFragment()
                )
                navview.setCheckedItem(R.id.teste)
            }
        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        Log.d("NAVITEM", item.toString())
        when (item.itemId) {
            R.id.teste -> replaceWithFragment(VeiculosFragment())
            R.id.outroteste -> replaceWithFragment(ServicosVeiculoFragment())
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        return supportFragmentManager
            .findFragmentById(R.id.mecanicoActivityFragmentContainerView)!!
            .findNavController()
            .navigateUp() ||
                binding.drawerLayout
                    .findNavController().navigateUp() ||
                super.onSupportNavigateUp()
    }

    private fun replaceWithFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(
            binding.motoristaFragmentContainer.id,
            fragment
        ).commit()
    }

}