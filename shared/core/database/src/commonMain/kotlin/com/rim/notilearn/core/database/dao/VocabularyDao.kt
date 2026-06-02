package com.rim.notilearn.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rim.notilearn.core.database.entity.VocabularyEntity

@Dao
interface VocabularyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVocabulary(vocabulary: VocabularyEntity)

    @Query("SELECT * FROM vocabulary WHERE nextReviewTime <= :currentTime AND status = 'LEARNING' LIMIT 1")
    suspend fun getNextReviewVocabulary(currentTime: Long): VocabularyEntity?
}