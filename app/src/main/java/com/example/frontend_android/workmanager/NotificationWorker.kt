package com.example.frontend_android.workmanager

import android.content.Context
import androidx.work.ForegroundInfo
import androidx.work.Worker
import androidx.work.WorkerParameters

class NotficationWorker(
    appContext: Context,
    workerParams: WorkerParameters
): Worker(appContext, workerParams) {

    override fun doWork(): Result {

        // Do the work here


        // Indicate whether the work finished successfully with the Result
        return Result.success()
    }

    private suspend fun showNotification() {

    }
}
