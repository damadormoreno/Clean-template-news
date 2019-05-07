package com.deneb.newsapp.core.platform

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.deneb.newsapp.R
import com.deneb.newsapp.core.extensions.inTransaction

abstract class BaseActivity: AppCompatActivity() {

    fun addFragment(savedInstanceState: Bundle?, tag: String) =
        savedInstanceState ?: supportFragmentManager.inTransaction {
            add(R.id.fragmentContainer, fragment(), tag)
        }

    fun replaceFragment(fragment: BaseFragment, tag: String) =
        supportFragmentManager.inTransaction {
            replace(R.id.fragmentContainer, fragment, tag)
            addToBackStack(null)
        }

    abstract fun fragment(): BaseFragment
}