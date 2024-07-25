package com.mango.meal_reciept_test_task.ui.fragment.home

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListAdapter
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.mango.meal_reciept_test_task.R
import com.mango.meal_reciept_test_task.data.model.response.Meal
import com.mango.meal_reciept_test_task.data.model.response.MealResponse
import com.mango.meal_reciept_test_task.databinding.FragmentHomeBinding
import com.mango.meal_reciept_test_task.databinding.FragmentLoginBinding
import com.mango.meal_reciept_test_task.ui.fragment.adapter.HomeAdapter
import com.mango.meal_reciept_test_task.ui.fragment.login.LoginFragmentDirections
import com.mango.meal_reciept_test_task.util.Resource
import com.mango.meal_reciept_test_task.util.viewBindings
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val mBinding by viewBindings(FragmentHomeBinding::bind)
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var adapter: HomeAdapter
    private lateinit var mealList: ArrayList<Meal>


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.screenState.collectLatest { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        // Yuklanayotgan holatni ko'rsatish
                        mBinding.errorLayout.visibility = View.VISIBLE
                        mBinding.errorTextMain.text = "Loading..."
                        Log.i(TAG, "onViewCreated: Loading")
                    }

                    is Resource.Success -> {
                        // Ma'lumot muvaffaqiyatli yuklandi
//                        binding.progressBar.visibility = View.GONE
                        val mealResponse = resource.data
                        mBinding.errorLayout.visibility = View.GONE
                        Log.i(TAG, "onViewCreated: Success $mealResponse")

                        updateUI(mealResponse)
                    }

                    is Resource.Error -> {
                        // Xatolikni ko'rsatish
                        mBinding.errorLayout.visibility = View.VISIBLE
                        mBinding.errorTextMain.text =
                            "Nimadir xato ketdi! \n Iltimos internetni yoqilganini tekshiring!"
                        Log.i(TAG, "onViewCreated: Error")
                    }

                    Resource.Empty -> {}
                }
            }
        }
        mBinding.btnMenu.setOnClickListener {
            mBinding.drawerLayout.openDrawer(mBinding.navigationView)
        }

        val headerView = mBinding.navigationView.getHeaderView(0)
        val closeDrawerButton = headerView.findViewById<ImageView>(R.id.btn_close_drawer)

        closeDrawerButton.setOnClickListener {
            mBinding.drawerLayout.closeDrawer(mBinding.navigationView)
        }

        mBinding.navigationView.setNavigationItemSelectedListener { menuItem ->
            handleNavigationItemSelected(menuItem)
            true
        }

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    requireActivity().finish() // Dasturdan chiqish
                }
            })

        setUpUI()
    }

    private fun updateUI(mealResponse: HomeScreenData) {

        mealList = ArrayList()
        mealList.addAll(mealResponse.meal1.meals)
        mealList.addAll(mealResponse.meal2.meals)
        adapter = HomeAdapter(mealList)
        mBinding.rv.adapter = adapter


        adapter.setOnItemClick(object : HomeAdapter.OnItemClick {
            override fun onClick(model: Meal, itemView: View, position: Int) {
                navigateTeRecipeFragment(meal = model)
            }

        })

    }

    private fun navigateTeRecipeFragment(meal: Meal) {
        val action = HomeFragmentDirections.actionHomeFragmentToRecipeFragment()
        Navigation.findNavController(requireView()).navigate(action)
    }


    private fun setUpUI() {

    }

    private fun handleNavigationItemSelected(menuItem: MenuItem) {
        when (menuItem.itemId) {
            R.id.nav_home -> {
                // Home Fragmentga o'tish amali (agar kerak bo'lsa)
            }

            R.id.nav_favourite -> {
                // Favourite Fragmentga o'tish amali (agar kerak bo'lsa)
            }

            R.id.nav_history -> {
                // History Fragmentga o'tish amali (agar kerak bo'lsa)
            }

            R.id.nav_help -> {
                // Help Fragmentga o'tish amali (agar kerak bo'lsa)
            }

            R.id.nav_logout -> {
                // Logout amali (agar kerak bo'lsa)
            }
        }
        mBinding.drawerLayout.closeDrawer(mBinding.navigationView)
    }

}