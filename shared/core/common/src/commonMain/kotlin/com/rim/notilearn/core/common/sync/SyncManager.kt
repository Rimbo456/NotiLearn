package com.rim.notilearn.core.common.sync

import kotlinx.coroutines.coroutineScope

class SyncManager(
    private val synchronizes: Set<Synchronizer>
) {
    suspend fun syncAll() {
        return coroutineScope {
            synchronizes.forEach { synchronizer ->
                synchronizer.sync()
            }
        }
    }
}