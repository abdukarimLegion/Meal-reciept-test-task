package com.mango.meal_reciept_test_task.ui.fragment.recipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.mango.meal_reciept_test_task.R
import com.mango.meal_reciept_test_task.databinding.FragmentLoginBinding
import com.mango.meal_reciept_test_task.databinding.FragmentRecipeBinding
import com.mango.meal_reciept_test_task.util.viewBindings
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeFragment : Fragment(R.layout.fragment_recipe) {

    private val mBinding by viewBindings(FragmentRecipeBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

    }


}