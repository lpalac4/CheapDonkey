package com.moraware.data

import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class RepositoryScheduler {

    private val POOL_SIZE = 2

    private val MAX_POOL_SIZE = 4

    private val TIMEOUT = 30

    private val mThreadPoolExecutor: ThreadPoolExecutor = ThreadPoolExecutor(POOL_SIZE, MAX_POOL_SIZE, TIMEOUT.toLong(),
            TimeUnit.SECONDS, ArrayBlockingQueue(POOL_SIZE))

    fun execute(runnable: Runnable) {
        mThreadPoolExecutor.execute(runnable)
    }
}