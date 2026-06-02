package com.rim.notilearn.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rim.notilearn.core.database.dao.VocabularyDao
import com.rim.notilearn.core.database.entity.VocabularyEntity

@Database(entities = [VocabularyEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun vocabularyDao(): VocabularyDao
}