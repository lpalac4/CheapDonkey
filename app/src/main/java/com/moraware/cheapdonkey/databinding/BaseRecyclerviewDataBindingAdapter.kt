package com.morasoftware.mango.bindingadapters

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

import com.android.databinding.library.baseAdapters.BR
import com.moraware.cheapdonkey.base.BaseViewModel

/**
 * Created by luis palacios on 7/29/17.
 */

abstract class BaseRecyclerviewDataBindingAdapter(viewModel: BaseViewModel) : RecyclerView.Adapter<BaseRecyclerviewDataBindingAdapter.BaseDataBindingHolder>() {

    var mViewModel: BaseViewModel = viewModel

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): BaseDataBindingHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
                layoutInflater, viewType, parent, false)
        return BaseDataBindingHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseDataBindingHolder,
                                  position: Int) {
        val obj = getObjForPosition(position)
        holder.bind(obj, mViewModel)
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutIdForPosition(position)
    }

    protected abstract fun getObjForPosition(position: Int): Any

    protected abstract fun getLayoutIdForPosition(position: Int): Int

    inner class BaseDataBindingHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(obj: Any, viewModel: BaseViewModel) {
            binding.setVariable(BR.obj, obj)
            binding.setVariable(BR.viewModel, viewModel)
            binding.executePendingBindings()
        }
    }
}