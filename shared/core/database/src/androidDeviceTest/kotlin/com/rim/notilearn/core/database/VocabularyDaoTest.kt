package com.rim.notilearn.core.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.rim.notilearn.core.database.dao.VocabularyDao
import com.rim.notilearn.core.database.entity.VocabularyEntity
import kotlinx.coroutines.test.runTest
import org.junit.runner.RunWith
import java.io.IOException
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

@RunWith(AndroidJUnit4::class)
class VocabularyDaoTest {
    private lateinit var database: AppDatabase
    private lateinit var vocabularyDao: VocabularyDao

    @BeforeTest
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        database = Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        vocabularyDao = database.vocabularyDao()
    }

    @AfterTest
    @Throws(IOException::class)
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertAndGetVocabularyById_shouldReturnCorrectData() = runTest {
        // 1. Chuẩn bị dữ liệu mẫu
        val sampleWord = VocabularyEntity(
            id = 1,
            word = "Concurrency",
            ipa = "/kənˈkʌrənsi/",
            partOfSpeech = "Noun",
            meaning = "Sự đồng thời, đồng hành",
            defaultExampleEn = "Concurrency is essential.",
            defaultExampleVi = "Xử lý đồng thời là thiết yếu.",
            aiExampleEn = null,
            aiExampleVi = null,
            intervalLevel = 1,
            nextReviewTime = 1717330000000L,
            status = "LEARNING",
            topic = "IT"
        )

        // 2. Thực thi hành động
        vocabularyDao.insertVocabulary(sampleWord)

        // 3. Kiểm chứng kết quả
        val result = vocabularyDao.getVocabularyById("1")
        assertNotNull(result)
        assertEquals("Concurrency", result.word)
        assertEquals("Sự đồng thời, đồng hành", result.meaning)
    }

    @Test
    fun queryExpiredVocabulary_shouldReturnOnlyOverdueWords() = runTest {
        val currentTime = 1717334400000L // Giả lập 12:00 trưa

        val expiredWord = VocabularyEntity(
            id = 2,
            word = "Mutex",
            meaning = "Khóa loại trừ lẫn nhau",
            nextReviewTime = currentTime - 3600000L, // Đã quá hạn 1 tiếng
            status = "LEARNING"
        )

        val futureWord = VocabularyEntity(
            id = 3,
            word = "Asynchronous",
            meaning = "Bất đồng bộ",
            nextReviewTime = currentTime + 3600000L, // 1 tiếng nữa mới đến hạn
            status = "LEARNING"
        )

        vocabularyDao.insertVocabulary(expiredWord)
        vocabularyDao.insertVocabulary(futureWord)

        // Thực thi hàm bốc từ vựng đến hạn
        val nextWordToReview = vocabularyDao.getNextReviewVocabulary(currentTime)

        // Kiểm chứng: Bắt buộc phải lấy ra từ "Mutex"
        assertNotNull(nextWordToReview)
        assertEquals(2, nextWordToReview.id)
        assertEquals("Mutex", nextWordToReview.word)
    }

    @Test
    fun queryExpiredVocabulary_withNoData_shouldReturnNull() = runTest {
        val currentTime = 1717334400000L

        val nextWordToReview = vocabularyDao.getNextReviewVocabulary(currentTime)

        // Kết quả phải trả về null vì DB trống
        assertNull(nextWordToReview)
    }
}