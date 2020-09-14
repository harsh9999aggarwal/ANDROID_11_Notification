package com.example.notifications

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nm = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        //only to work on devices greater on SDK OREO
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = (NotificationChannel("first","DEFAULT",NotificationManager.IMPORTANCE_HIGH))
            channel.apply {
                enableLights(true)
                enableVibration(true)
            }
            nm.createNotificationChannel(channel)
        }

        button.setOnClickListener {
            val simpleNotification = NotificationCompat.Builder(this,"first")
                .setContentTitle("Simple Title")
                .setContentText("This is the sample description if notification")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build()
            nm.notify(1,simpleNotification)
        }

        button2.setOnClickListener {
            val i=Intent()
            i.action = Intent.ACTION_VIEW
            i.data = Uri.parse("https://www.google.com")

            val pi = PendingIntent.getActivity(this,123,i,PendingIntent.FLAG_UPDATE_CURRENT)

            val clickableNotification = NotificationCompat.Builder(this,"first")
                .setContentTitle("Simple Title 2 ")
                .setContentIntent(pi)
                .setAutoCancel(true)
                .setContentText("it redirect to GOOGLE when you click")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build()
            nm.notify(2,clickableNotification)
        }

        button3.setOnClickListener {
            val i=Intent()
            i.action = Intent.ACTION_VIEW
            i.data = Uri.parse("https://www.google.com")

            val pi = PendingIntent.getActivity(this,123,i,PendingIntent.FLAG_UPDATE_CURRENT)

            val clickableNotification = NotificationCompat.Builder(this,"first")
                .setContentTitle("Simple Title 3 ")
                .addAction(R.drawable.ic_launcher_foreground,"click me ",pi)
                .setAutoCancel(true)
                .setContentText("redirect to GOOGLE when you click on click me")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build()
            nm.notify(3,clickableNotification)
        }

        button4.setOnClickListener {
            val builder =
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    NotificationCompat.Builder(this,"first")
                }else{
                    NotificationCompat.Builder(this)
                        .setPriority(Notification.PRIORITY_MAX)
                        .setDefaults(Notification.DEFAULT_VIBRATE or Notification.DEFAULT_LIGHTS)
                }
            val simpleNotification = builder
                .setContentTitle("Simple Title 4")
                .setContentText("heads up notification")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .build()
            nm.notify(4,simpleNotification)
        }
    }
}