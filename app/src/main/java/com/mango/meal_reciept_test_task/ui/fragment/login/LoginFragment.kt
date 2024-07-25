package com.mango.meal_reciept_test_task.ui.fragment.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.mango.meal_reciept_test_task.R
import com.mango.meal_reciept_test_task.databinding.FragmentLoginBinding
import com.mango.meal_reciept_test_task.ui.fragment.home.HomeFragmentDirections
import com.mango.meal_reciept_test_task.util.viewBindings
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {
    private val mBinding by viewBindings(FragmentLoginBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.btnLogin.setOnClickListener {
            navigateToHomeFragment()
        }

    }


    private fun navigateToHomeFragment() {
        val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
        Navigation.findNavController(requireView()).navigate(action)
    }


}