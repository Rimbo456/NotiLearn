package com.rim.notilearn.core.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.rim.notilearn.core.database.dao.VocabularyDao
import com.rim.notilearn.core.database.entity.VocabularyEntity

@Database(entities = [VocabularyEntity::class], version = 1, exportSchema = false)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun vocabularyDao(): VocabularyDao
}

@Suppress("KotlinNoActualForExpect")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}


fun getAppDatabase(databaseFactory: DatabaseFactory): AppDatabase {
    return databaseFactory.createBuilder()
        .fallbackToDestructiveMigration(dropAllTables = true)
        .build()
}