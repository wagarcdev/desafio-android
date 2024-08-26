package com.picpay.desafio.android.core.domain.usecase

import com.picpay.desafio.android.core.data.image.fake.FakeAppImageProcessor
import com.picpay.desafio.android.core.data.image.fake.createFakeImageProcessor
import com.picpay.desafio.android.core.data.model.UserModel
import com.picpay.desafio.android.core.data.repository.fake.FakeUserLocalDataSource
import com.picpay.desafio.android.core.data.repository.fake.FakeUserRemoteDataSource
import com.picpay.desafio.android.core.data.repository.fake.FakeUsersRepository
import com.picpay.desafio.android.core.data.repository.fake.lists.fakeUserModelList
import com.picpay.desafio.android.core.data.sync.DataSyncManager
import com.picpay.desafio.android.core.data.sync.Synchronizer
import com.picpay.desafio.android.core.data.sync.test.TestSynchronizer
import com.picpay.desafio.android.core.data.util.OrderDirection
import com.picpay.desafio.android.core.data.util.SortBy
import com.picpay.desafio.android.core.datastore.DesafioAppPreferencesDataSource
import com.picpay.desafio.android.core.datastore.PreferencesDataSource
import com.picpay.desafio.android.core.datastore.test.FakePreferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class SearchLocalUsersFlowUseCaseTest {

    private lateinit var fakeUserRepository: FakeUsersRepository
    private lateinit var searchLocalUsersFlowUseCase: SearchLocalUsersFlowUseCase
    private lateinit var searchPrePopulatedLocalUsersFlowUseCase: SearchLocalUsersFlowUseCase
    private lateinit var preferences: PreferencesDataSource
    private lateinit var imageProcessor: FakeAppImageProcessor
    private lateinit var localDataSource: FakeUserLocalDataSource
    private lateinit var prePopulatedLocalDataSource: FakeUserLocalDataSource
    private lateinit var prePopulatedUserRepository: FakeUsersRepository

    private lateinit var remoteDataSource: FakeUserRemoteDataSource
    private lateinit var synchronizer: Synchronizer
    private lateinit var testDispatcher: TestDispatcher

    private lateinit var users: MutableList<UserModel>


    @Before
    fun setUp() {
        testDispatcher = StandardTestDispatcher()

        preferences = DesafioAppPreferencesDataSource(
            repository = FakePreferencesDataStore()
        )
        synchronizer = TestSynchronizer(
            preferences = preferences,
            dataSyncManager = DataSyncManager()
        )

        users = fakeUserModelList.toMutableList()


        remoteDataSource = FakeUserRemoteDataSource()
        localDataSource = FakeUserLocalDataSource()

        imageProcessor = createFakeImageProcessor()

        fakeUserRepository = FakeUsersRepository(
            remoteDataSource = remoteDataSource,
            localDataSource = localDataSource,
            imageProcessor = imageProcessor,
            ioDispatcher = testDispatcher,
            testSynchronizer = synchronizer
        )
        searchLocalUsersFlowUseCase =
            SearchLocalUsersFlowUseCase(fakeUserRepository)

        prePopulatedLocalDataSource = FakeUserLocalDataSource(
            prePopulateList = users
        )

        prePopulatedUserRepository = FakeUsersRepository(
            remoteDataSource = remoteDataSource,
            localDataSource = prePopulatedLocalDataSource,
            imageProcessor = imageProcessor,
            ioDispatcher = testDispatcher,
            testSynchronizer = synchronizer
        )

        searchPrePopulatedLocalUsersFlowUseCase =
            SearchLocalUsersFlowUseCase(prePopulatedUserRepository)

    }

    @Test
    fun `invoke searches and sorts users correctly`() = runTest(testDispatcher) {
        // Given
        val searchQuery = "John"
        val sortColumn = SortBy.NAME.parameter
        val sortOrder = OrderDirection.ASCENDING.parameter

        // When
        val result =
            searchPrePopulatedLocalUsersFlowUseCase(searchQuery, sortColumn, sortOrder).first()

        // Then
        val expectedResult =
            fakeUserModelList.filter { it.name.contains(searchQuery, ignoreCase = true) }
                .sortedBy { it.name } // Assuming sorting by name in ascending order
        assertEquals(expectedResult, result)
    }

    @Test
    fun `invoke returns empty list when no users match the search query`() = runTest(testDispatcher) {
        // Given
        val searchQuery = "NonExistingUser"
        val sortColumn = SortBy.NAME.parameter
        val sortOrder = OrderDirection.ASCENDING.parameter

        // When
        val result = searchPrePopulatedLocalUsersFlowUseCase(searchQuery, sortColumn, sortOrder).first()

        // Then
        assertTrue(result.isEmpty())
    }

    @Test
    fun `invoke sorts users by username correctly`() = runTest(testDispatcher) {
        // Given
        val searchQuery = ""
        val sortColumn = SortBy.USERNAME.parameter
        val sortOrder = OrderDirection.ASCENDING.parameter

        // When
        val result = searchPrePopulatedLocalUsersFlowUseCase(searchQuery, sortColumn, sortOrder).first()

        // Then
        val expectedResult =
            if (sortOrder == OrderDirection.ASCENDING.parameter) {
                fakeUserModelList.sortedBy { it.username }
            } else {
                fakeUserModelList.sortedByDescending { it.username }
            }

        assertEquals(expectedResult, result)
    }

    @Test
    fun `invoke sorts users in descending order correctly`() = runTest(testDispatcher) {
        // Given
        val searchQuery = "" // Empty search query to include all users
        val sortColumn = SortBy.NAME.parameter
        val sortOrder = OrderDirection.DESCENDING.parameter

        // When
        val result = searchPrePopulatedLocalUsersFlowUseCase(searchQuery, sortColumn, sortOrder).first()

        // Then
        val expectedResult =
            if (sortOrder == OrderDirection.ASCENDING.parameter) {
                fakeUserModelList.sortedBy { it.name }
            } else {
                fakeUserModelList.sortedByDescending { it.name }
            }

        assertEquals(expectedResult, result)
    }

    @Test
    fun `invoke performs case-insensitive search`() = runTest(testDispatcher) {
        // Given
        val searchQuery = "SANDRINE" // Uppercase version of "Sandrine"
        val sortColumn = SortBy.NAME.parameter
        val sortOrder = OrderDirection.ASCENDING.parameter

        // When
        val result = searchPrePopulatedLocalUsersFlowUseCase(searchQuery, sortColumn, sortOrder).first()

        // Then
        val expectedResult =
            if (sortOrder == OrderDirection.ASCENDING.parameter) {
                fakeUserModelList.filter { it.name.contains(searchQuery, ignoreCase = true) }
                    .sortedBy { it.name }
            } else {
                fakeUserModelList.filter { it.name.contains(searchQuery, ignoreCase = true) }
                    .sortedByDescending { it.name }
            }

        assertEquals(expectedResult, result)
    }

    @Test
    fun `invoke handles special characters in search query`() = runTest(testDispatcher) {
        // Given
        val searchQuery = "Ms. Simeon Yost" // Exact match
        val sortColumn = SortBy.NAME.parameter
        val sortOrder = OrderDirection.ASCENDING.parameter

        // When
        val result = searchPrePopulatedLocalUsersFlowUseCase(searchQuery, sortColumn, sortOrder).first()

        // Then
        val expectedResult =
            if (sortOrder == OrderDirection.ASCENDING.parameter) {
                fakeUserModelList.filter { it.name == searchQuery }
                    .sortedBy { it.name }
            } else {
                fakeUserModelList.filter { it.name == searchQuery }
                    .sortedByDescending { it.name }
            }

        assertEquals(expectedResult, result)
    }
}
