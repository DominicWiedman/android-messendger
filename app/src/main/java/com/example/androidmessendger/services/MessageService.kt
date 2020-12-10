package com.example.androidmessendger.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.androidmessendger.App
import com.example.androidmessendger.R
import com.example.androidmessendger.base.SubRX
import com.example.androidmessendger.domain.repositories.DialogsRepository
import com.example.androidmessendger.domain.repositories.models.realm.DialogItem
import com.example.androidmessendger.domain.repositories.models.realm.MessageItem
import com.example.androidmessendger.presentation.main.MainActivity
import dagger.Module
import javax.inject.Inject

@Module
class MessageService : Service() {
    @Inject
    lateinit var dialogsRepository: DialogsRepository

    lateinit var mHandler: Handler
    lateinit var mRunnable: Runnable

    private val NOTIFY_ID = 101
    private val CHANNEL_ID = "Messages"


    override fun onCreate() {
        super.onCreate()

        App.appComponent.inject(this)
        createNotificationChannel()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private fun checkNewMessages() {
        dialogsRepository.getNewMessages(SubRX { messages, e ->
            messages?.let {
                messages.forEach {
                    it.id?.let { it1 ->
                        val messageItem = MessageItem()
                        val dialogItem = DialogItem()
                        messageItem.id = it1
                        messageItem.dialogId = it.from!!
                        messageItem.userId = it.from
                        messageItem.deliveredStatus = it.delivered!!
                        messageItem.message = it.message

                        dialogItem.id = it.from
                        dialogItem.userId = it.from
                        dialogItem.lastMessage = it.message
                        dialogItem.userName = it.from.toString()

                        dialogsRepository.createNewDialog(dialogItem, {})
                        dialogsRepository.addMessageInDialog(messageItem, {message ->
                            Log.d("message", message.toString())
                            sendNotification(message.dialogId.toString(), message.message, it1)
                        })
                    }
                }
            }
            e?.let {
                return@SubRX
            }
        })
    }

    private fun sendNotification(from: String, message: String, notifyId: Int) {
        val notificationIntent = Intent(this, MainActivity::class.java)
        val contentIntent = PendingIntent.getActivity(
            this,
            0,
            notificationIntent,
            PendingIntent.FLAG_CANCEL_CURRENT
        )

        val notification = NotificationCompat.Builder(App.appContext, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(from)
            .setContentText(message)
            .setAutoCancel(true)
            .setContentIntent(contentIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val notificationManager =
            NotificationManagerCompat.from(App.appContext)

        notificationManager.notify(notifyId, notification.build())

    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        mHandler = Handler()
        mRunnable = Runnable {
            checkNewMessages()
        }
        mHandler.postDelayed(mRunnable, 5000)

        return START_NOT_STICKY
    }

}