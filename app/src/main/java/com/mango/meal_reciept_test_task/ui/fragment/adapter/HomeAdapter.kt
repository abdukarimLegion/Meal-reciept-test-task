package com.mango.meal_reciept_test_task.ui.fragment.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.mango.meal_reciept_test_task.R
import com.mango.meal_reciept_test_task.data.model.response.Meal
import com.mango.meal_reciept_test_task.databinding.ItemMealBinding


class HomeAdapter (var mList: List<Meal>) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemMealBinding) : RecyclerView.ViewHolder(binding.root)

//    fun setFilteredList(mList: List<Int>){
//        this.mList = mList
//        notifyDataSetChanged()
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemMealBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        with(holder) {
            with(mList[position]){
                binding.root.setOnClickListener {
                    if (onItemClick != null) {
                        onItemClick!!.onClick(mList[position], itemView, position)
                    }
                }

                binding.imgMeal.load(mList.get(position).strMealThumb) {
                    error(R.drawable.image_load_failed)
                    placeholder(R.drawable.icon_loading)
                    crossfade(800)
                }

                binding.btnFavourites.setOnClickListener {
                    if(onItemClick != null){

                    }
                }


                binding.mealName.text = mList.get(position).strMeal

            }
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    private var onItemClick: OnItemClick? = null

    interface OnItemClick {
        fun onClick(model: Meal, itemView: View, position: Int)
    }

    fun setOnItemClick(onItemClick: OnItemClick) {
        this.onItemClick = onItemClick
    }

}