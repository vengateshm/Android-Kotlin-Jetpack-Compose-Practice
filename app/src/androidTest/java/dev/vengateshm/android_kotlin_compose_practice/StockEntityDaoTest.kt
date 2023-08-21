package dev.vengateshm.android_kotlin_compose_practice

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import app.cash.turbine.test
import dev.vengateshm.android_kotlin_compose_practice.room_db.StockDao
import dev.vengateshm.android_kotlin_compose_practice.room_db.StockDb
import dev.vengateshm.android_kotlin_compose_practice.room_db.StockEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
@OptIn(ExperimentalCoroutinesApi::class)
class StockEntityDaoTest {

    @get: Rule
    val dispatcherRule = TestDispatcherRule()

    private lateinit var stockDao: StockDao
    private lateinit var stockDb: StockDb

    @Before
    fun create() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        stockDb = Room
            .inMemoryDatabaseBuilder(context, StockDb::class.java)
            .build()
        stockDao = stockDb.itemDao()
    }

    @Test
    fun addItem_shouldReturn_theItem_inFlow() = runTest {
        val item1 = StockEntity(uid = 1, name = "Apple", symbol = "APPL")
        val item2 = StockEntity(uid = 2, name = "Microsoft", symbol = "MST")
        stockDao.addStock(item1)
        stockDao.addStock(item2)

        stockDao.getAllStocks().test {
            val list = awaitItem()
            assert(list.contains(item1))
            assert(list.contains(item2))
            cancel()
        }
    }

    @Test
    fun deletedItem_shouldNot_be_present_inFlow() = runTest {
        val item1 = StockEntity(uid = 1, name = "Apple", symbol = "APPL")
        val item2 = StockEntity(uid = 2, name = "Microsoft", symbol = "MST")
        stockDao.addStock(item1)
        stockDao.addStock(item2)
        stockDao.removeStock(item2)
        stockDao.getAllStocks().test {
            val list = awaitItem()
            assert(list.size == 1)
            assert(list.contains(item1))
            cancel()
        }
    }

    @Test
    fun updateItem_shouldReturn_theItem_inFlow() = runTest {
        val item1 = StockEntity(uid = 1, name = "Apple", symbol = "APPL")
        val item2 = StockEntity(uid = 2, name = "Microsoft", symbol = "MST")
        val item3 = StockEntity(uid = 2, name = "Google", symbol = "ALP")
        stockDao.addStock(item1)
        stockDao.addStock(item2)
        stockDao.addStock(item3)
        stockDao.getAllStocks().test {
            val list = awaitItem()
            assert(list.size == 2)
            assert(list.contains(item3))
            cancel()
        }
    }

    @Test
    fun search_by_query_should_return_matching_stocks() = runTest {
        val item1 = StockEntity(uid = 1, name = "Apple", symbol = "APPL")
        val item2 = StockEntity(uid = 2, name = "Microsoft", symbol = "MST")
        val item3 = StockEntity(uid = 3, name = "Google", symbol = "ALP")
        stockDao.addStock(item1)
        stockDao.addStock(item2)
        stockDao.addStock(item3)
        val result = stockDao.searchStock("o");
        assert(result.size == 2)
        assert(result[0].symbol == "MST")
        assert(result[1].symbol == "ALP")
    }

    @After
    fun cleanup() {
        stockDb.close()
    }
}