package com.deneb.newsapp.core.navigation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.deneb.newsapp.R
import com.deneb.newsapp.core.functional.DialogCallback
import com.deneb.newsapp.core.platform.BaseActivity
import com.deneb.newsapp.core.platform.BaseFragment
import com.deneb.newsapp.features.news.ArticlesFragment

class MainActivity : BaseActivity(), PopUpDelegator {

    override fun fragment() = ArticlesFragment()

    companion object {
        fun callingIntent(context: Context): Intent {
            val intentToLaunch = Intent(context, MainActivity::class.java)
            intentToLaunch.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            return intentToLaunch
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout)
        addFragment(savedInstanceState, "ArticlesFragment")
    }
    override fun showErrorWithRetry(
        title: String,
        message: String,
        positiveText: String,
        negativeText: String,
        callback: DialogCallback
    ) {
        // Implementar aqui el dialog con el que quereis mostrar los errores y en función
        // del boton pulsado llamar a callback.onAccept() o callback.onDecline() que lo que hace es
        // delegar al fragment
    }

    override fun onBackPressed() {
        val container = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
        if (container?.tag.equals("ArticlesFragment")){
            moveTaskToBack(true)
        }else {
            ((container) as BaseFragment).onBackPressed()
            super.onBackPressed()
        }
    }

    // Propaga el onActivityResult al fragment inflado
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val container = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
        ((container) as BaseFragment).onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

}
