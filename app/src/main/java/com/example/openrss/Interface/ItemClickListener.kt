package com.example.openrss.Interface

import android.view.View

public interface ItemClickListener {
    fun onClick(view: View?, position:Int, isLongClick:Boolean);
}