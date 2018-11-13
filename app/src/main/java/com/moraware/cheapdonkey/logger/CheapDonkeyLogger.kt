package com.moraware.cheapdonkey.logger

import java.util.logging.Level
import java.util.logging.Logger

class CheapDonkeyLogger(name: String, resourceBundleName: String?) : Logger(name, resourceBundleName) {

    override fun log(level: Level?, msg: String?) {
        if(Level.SEVERE == level) {
            reportToCrashAnalytics(msg)
        }

        super.log(level, msg)
    }

    private fun reportToCrashAnalytics(msg: String?) {
        msg?.let {
            //report to analytics
        }
    }
}