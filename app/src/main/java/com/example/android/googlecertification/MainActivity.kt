package com.example.android.googlecertification

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.snackbar.Snackbar


private val TAG = MainActivity::class.java.simpleName

class MainActivity : AppCompatActivity() {

    private lateinit var coordinatorLayout: CoordinatorLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        coordinatorLayout = findViewById(R.id.coordinatorLayout)

        val toastButton: Button = findViewById(R.id.toastButton)
        toastButton.setOnClickListener {
            displayToast()
        }

        val snackbarButton: Button = findViewById(R.id.SnackbarButton)
        snackbarButton.setOnClickListener {
            displaySnackbar()
        }
    }

    /**
     * Display a simple message in a popup using a Toast
     */
    private fun displayToast() {
        val inflater = layoutInflater
        val layout: View = inflater.inflate(R.layout.custom_toast, null)
        val text: TextView = layout.findViewById(R.id.text)

        text.text = getString(R.string.custom_toast_text)
        with(Toast(applicationContext)) {
            setGravity(Gravity.CENTER_VERTICAL, 0, 0)
            duration = Toast.LENGTH_SHORT
            view = layout
            show()
        }
    }

    private fun displaySnackbar() {

        Snackbar.make(coordinatorLayout, getText(R.string.custom_toast_text), Snackbar.LENGTH_SHORT).apply {
            setAction(getString(R.string.undo_action)) { Log.d(TAG, "Undo") }
        }.show()
    }
}
