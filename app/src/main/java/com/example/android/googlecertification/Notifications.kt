package com.example.android.googlecertification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

enum class Priority(val value: Int) {
    MIN(NotificationCompat.PRIORITY_MIN),
    LOW(NotificationCompat.PRIORITY_LOW),
    DEFAULT(NotificationCompat.PRIORITY_DEFAULT),
    HIGH(NotificationCompat.PRIORITY_HIGH),
    MAX(NotificationCompat.PRIORITY_MAX)
}

fun createNotificationBuilder(
    context: Context,
    channelId: String,
    @DrawableRes smallIcon: Int,
    title: String,
    text: String,
    priority: Priority = Priority.DEFAULT,
    autoCancel: Boolean = true
) = NotificationCompat.Builder(
    context,
    channelId
)
    .setSmallIcon(smallIcon)
    .setContentTitle(title)
    .setContentText(text)
    .setPriority(priority.value)
    .setAutoCancel(autoCancel)

enum class Importance(val value: Int) {
    NONE(NotificationManager.IMPORTANCE_NONE),
    MIN(NotificationManager.IMPORTANCE_MIN),
    LOW(NotificationManager.IMPORTANCE_LOW),
    DEFAULT(NotificationManager.IMPORTANCE_DEFAULT),
    HIGH(NotificationManager.IMPORTANCE_HIGH),
    MAX(NotificationManager.IMPORTANCE_MAX)
}

@RequiresApi(26)
fun createNotificationChannel(
    context: Context,
    name: String,
    channelId: String,
    descriptionText: String? = null,
    importance: Importance = Importance.DEFAULT
) = NotificationChannel(channelId, name, importance.value).apply {
    if (descriptionText != null) {
        description = descriptionText
    }
}.also { channel ->
    val notificationManager: NotificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    notificationManager.createNotificationChannel(channel)
}

fun showNotification(context: Context, notificationId: Int, notification: Notification) {
    NotificationManagerCompat.from(context).run {
        // notificationId is a unique int for each notification that you must define
        notify(notificationId, notification)
    }
}