package com.deneb.newsapp.core.navigation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.deneb.newsapp.R
import com.deneb.newsapp.core.functional.DialogCallback
import kotlinx.android.synthetic.main.navigation_activity.*

class MainActivity : AppCompatActivity(), PopUpDelegator {
    private lateinit var appBarConfiguration : AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigation_activity)

        setSupportActionBar(toolbar)
        val navController = findNavController(R.id.fragment)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            toolbar.title = when (destination.id) {
                R.id.articlesFragment -> "News"
                R.id.articleDetailFragment -> "Details"
                else -> "News"
            }
            //Controlamos que al cambiar de fragment no siga nuestro progress activo
            if (progress.visibility == View.VISIBLE) progress.visibility = View.GONE

        }
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
}
