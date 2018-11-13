package com.moraware.cheapdonkey.databinding

import android.databinding.BindingAdapter
import android.view.View

class BaseViewBindingAdapter {

    companion object {
        @JvmStatic
        @BindingAdapter("android:visibility")
        fun setVisibility(view: View, show: Boolean?) {
            if (show == null) {
                view.visibility = View.GONE
                return
            }

            view.visibility = if (show) View.VISIBLE else View.GONE
        }

        @JvmStatic
        @BindingAdapter("android:visibility")
        fun setVisibility(view: View, show: Boolean) {
            view.visibility = if (show) View.VISIBLE else View.GONE
        }
    }
}