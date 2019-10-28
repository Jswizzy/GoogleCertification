package com.example.android.googlecertification

import android.content.Context
import androidx.core.app.NotificationCompat

enum class Priority(val value: Int) {
    LOW(NotificationCompat.PRIORITY_LOW),
    DEFAULT(NotificationCompat.PRIORITY_DEFAULT),
    HIGH(NotificationCompat.PRIORITY_HIGH)
}

fun notifification(context: Context, channelId: String, title: String, text: String, priority: Priority) {
    val builder = NotificationCompat.Builder(
        context,
        channelId
    )
        .setSmallIcon(R.drawable.droid)
        .setContentTitle(title)
        .setContentText(text)
        .setPriority(priority.value)
}