package com.example.android.googlecertification

import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.app.NotificationCompat
import androidx.core.app.TaskStackBuilder
import com.github.ajalt.timberkt.Timber
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {

    private lateinit var coordinatorLayout: CoordinatorLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        assignViews()

        setupOnClickListeners()
    }

    private fun assignViews() {
        coordinatorLayout = findViewById(R.id.coordinatorLayout)
    }

    private fun setupOnClickListeners() {
        val toastButton: Button = findViewById(R.id.toastButton)
        toastButton.setOnClickListener {
            displayToast()
        }

        val snackbarButton: Button = findViewById(R.id.SnackbarButton)
        snackbarButton.setOnClickListener {
            displaySnackbar()
        }

        val notificationButton: Button = findViewById(R.id.notificationButton)
        notificationButton.setOnClickListener {
            displayNotification()
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

        Snackbar.make(coordinatorLayout, getText(R.string.custom_toast_text), Snackbar.LENGTH_SHORT)
            .apply {
                setAction(getString(R.string.undo_action)) { Timber.d { "Undo" } }
            }.show()
    }

    private fun displayNotification() {

        val channelId = getString(R.string.channel_id)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(
                context = this,
                name = getString(R.string.example),
                channelId = channelId,
                descriptionText = null
            )
        }

        val intent = Intent(this, ResultActivity::class.java)

        val pendingIntent = TaskStackBuilder.create(this).run {
            addNextIntentWithParentStack(intent)
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        val notification =
            createNotificationBuilder(
                context = this,
                channelId = channelId,
                smallIcon = R.drawable.droid,
                title = getString(R.string.example),
                text = getString(R.string.hello_world)
            ).apply {
                setCategory(NotificationCompat.CATEGORY_MESSAGE)
                setContentIntent(pendingIntent)
                setAutoCancel(true)
            }.build()

        showNotification(this, channelId.toInt(), notification)
    }
}