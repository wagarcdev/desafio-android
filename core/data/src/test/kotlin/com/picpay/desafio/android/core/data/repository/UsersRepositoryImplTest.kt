package com.picpay.desafio.android.core.data.repository

import com.picpay.desafio.android.common.util.ApiResponse
import com.picpay.desafio.android.core.data.image.createFakeImageProcessor
import com.picpay.desafio.android.core.data.image.fake.FakeImageProcessor
import com.picpay.desafio.android.core.data.model.UserModel
import com.picpay.desafio.android.core.data.model.mappers.toDomainModel
import com.picpay.desafio.android.core.data.repository.fake.FakeUserLocalDataSource
import com.picpay.desafio.android.core.data.repository.fake.FakeUserRemoteDataSource
import com.picpay.desafio.android.core.data.repository.fake.lists.fakeUserModelList
import com.picpay.desafio.android.core.data.repository.fake.lists.fakeUserResponseList
import com.picpay.desafio.android.core.data.repository.impl.UsersRepositoryImpl
import com.picpay.desafio.android.core.data.sync.DataSyncManager
import com.picpay.desafio.android.core.data.sync.Synchronizer
import com.picpay.desafio.android.core.data.sync.TestSynchronizer
import com.picpay.desafio.android.core.data.sync.usersSync
import com.picpay.desafio.android.core.data.util.OrderDirection
import com.picpay.desafio.android.core.data.util.SortBy
import com.picpay.desafio.android.datastore.DesafioAppPreferencesDataSource
import com.picpay.desafio.android.datastore.PreferencesDataSource
import com.picpay.desafio.android.datastore.test.TestPreferencesDataStore
import com.picpay.desafio.android.network.model.UserResponse
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import kotlin.coroutines.EmptyCoroutineContext

class UsersRepositoryImplTest {

    private lateinit var remoteDataSource: FakeUserRemoteDataSource
    private lateinit var localDataSource: FakeUserLocalDataSource
    private lateinit var prePopulatedLocalDataSource: FakeUserLocalDataSource
    private lateinit var imageProcessor: FakeImageProcessor
    private lateinit var usersRepository: UsersRepository
    private lateinit var prePopulatedUserRepository: UsersRepository
    private lateinit var preferences: PreferencesDataSource
    private lateinit var synchronizer: Synchronizer

    private lateinit var users: MutableList<UserModel>

    @Before
    fun setUp() {

        users = fakeUserModelList.toMutableList()

        imageProcessor = createFakeImageProcessor()

        remoteDataSource = FakeUserRemoteDataSource()
        localDataSource = FakeUserLocalDataSource()

        usersRepository = UsersRepositoryImpl(
            remoteDataSource = remoteDataSource,
            localDataSource = localDataSource,
            imageProcessor = imageProcessor
        )

        prePopulatedLocalDataSource = FakeUserLocalDataSource(
            prePopulateList = users
        )

        prePopulatedUserRepository = UsersRepositoryImpl(
            remoteDataSource = remoteDataSource,
            localDataSource = prePopulatedLocalDataSource,
            imageProcessor = imageProcessor
        )

        preferences = DesafioAppPreferencesDataSource(
            repository = TestPreferencesDataStore()
        )

        synchronizer = TestSynchronizer(
            preferences = preferences,
            dataSyncManager = DataSyncManager()
        )
    }

    private suspend fun insertUsers(users: List<UserResponse>) = withContext(EmptyCoroutineContext) {
        return@withContext localDataSource
            .insertUsers(*users.map { it.toDomainModel(imageProcessor) }.toTypedArray())
    }

    private suspend fun fetchUsers() = withContext(EmptyCoroutineContext) {
        return@withContext kotlin.run {
            when (val apiResponse = remoteDataSource.getUsers()) {
                is ApiResponse.Error -> null
                is ApiResponse.Success -> apiResponse.value
            }
        }
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `repository sync should persist ApiResponse`() = runTest {

        //When
        synchronizer.usersSync(
            usersFetcher = ::fetchUsers,
            usersPersistence = ::insertUsers
        )

        val dbUsers: List<UserModel> = localDataSource.getUsers().first()
        val remoteUsers = fetchUsers()?.toDomainModel(imageProcessor)

        // Then
        assertEquals(dbUsers, remoteUsers)
    }

    @Test
    fun `searchUser should return filtered and sorted users`() = runTest {

        // When
        val result = prePopulatedUserRepository.searchUser(
            searchQuery = "Annabelle",
            sortColumn = SortBy.NAME.parameter,
            sortOrder = OrderDirection.ASCENDING.parameter
        ).first()

        // Then
        assertEquals(1, result.size)
        assertEquals("Annabelle Reilly", result.first().name)
    }

    @Test
    fun `getLocalUsers should return all local users`() = runTest {
        // When
        val result = prePopulatedUserRepository.getLocalUsers().first()

        // Then
        assertEquals(fakeUserModelList.size, result.size)
    }

    @Test
    fun `insertLocalUser should add a user to the local data source`() = runTest {
        // Given
        val newUserResponse = UserResponse(
            id = 7,
            name = "New User",
            img = "https://randomuser.me/api/portraits/men/7.jpg",
            username = "newuser"
        )

        // When
        prePopulatedUserRepository.insertLocalUser(newUserResponse)

        // Then
        val localUsers = localDataSource.getUsers().first()
        assertTrue(localUsers.any { it.externalId == 7 && it.name == "New User" })
    }

    @Test
    fun `getRemoteUsers should return all remote users`() = runTest {
        // When
        val result = prePopulatedUserRepository.getRemoteUsers()

        // Then
        assertTrue(result is ApiResponse.Success)
        assertEquals(fakeUserResponseList.size, (result as ApiResponse.Success).value.size)
    }

    @Test
    fun `insertLocalUsers should add multiple users to the local data source`() = runTest {
        // Given
        val newUsersResponse = listOf(
            UserResponse(
                id = 7,
                name = "New User 1",
                img = "https://randomuser.me/api/portraits/men/7.jpg",
                username = "newuser1"
            ),
            UserResponse(
                id = 8,
                name = "New User 2",
                img = "https://randomuser.me/api/portraits/men/8.jpg",
                username = "newuser2"
            )
        )

        // When
        prePopulatedUserRepository.insertLocalUsers(*newUsersResponse.toTypedArray())

        // Then
        val localUsers = localDataSource.getUsers().first()
        assertTrue(localUsers.any { it.externalId == 7 && it.name == "New User 1" })
        assertTrue(localUsers.any { it.externalId == 8 && it.name == "New User 2" })
    }
}