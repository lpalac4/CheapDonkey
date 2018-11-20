package com.moraware.domain.client

import com.moraware.data.IDataRepository
import java.util.logging.Logger

class DomainDependencyProvider {

    companion object {
        private lateinit var sDataRepository: IDataRepository
        @JvmStatic
        fun getDataRepository(): IDataRepository { return sDataRepository }
        @JvmStatic
        fun setDataRepository(dataRepository: IDataRepository) { sDataRepository = dataRepository}

        private lateinit var sLogger: Logger
        @JvmStatic
        fun getLogger(): Logger { return sLogger }
        @JvmStatic
        fun setLogger(logger: Logger) { sLogger = logger}
    }
}