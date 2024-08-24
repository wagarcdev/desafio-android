package com.picpay.desafio.android.core.database.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.picpay.desafio.android.core.database.DesafioDatabase
import com.picpay.desafio.android.core.database.model.fakeUserEntityList
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class UserDaoTest {

    private lateinit var userDao: UserDao
    private lateinit var dataBase: DesafioDatabase

    @Before
    fun createDatabase() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        dataBase = Room.inMemoryDatabaseBuilder(
            context,
            DesafioDatabase::class.java
        ).build()

        userDao = dataBase.usersDao()
    }

    @After
    fun tearDown() {
        dataBase.close()
    }

    @Test
    fun insertUser_retrieveUser() = runTest {
        // Given
        val userInserted = fakeUserEntityList[0]

        userDao.insertUser(userInserted)

        // When
        val users = userDao.getAllUsers().first()

        // Then
        assertEquals(1, users.size)
        assertEquals(userInserted, users[0])

    }

    @Test
    fun insertAndRetrieveAllUsers() = runTest {
        // Given
        val usersToInsert = fakeUserEntityList

        // When
        usersToInsert.forEach { userDao.insertUser(it) }
        val retrievedUsers = userDao.getAllUsers().first()

        // Then
        assertEquals(usersToInsert.size, retrievedUsers.size)
        assertTrue(retrievedUsers.containsAll(usersToInsert))
        // Optional: You can also check if the retrieved users are in the same order
        assertEquals(usersToInsert, retrievedUsers)
    }

    @Test
    fun searchUsers_byName() = runTest {
        // Given
        val user1 = fakeUserEntityList[0]
        val user2 = fakeUserEntityList[1]
        val name = user2.name

        userDao.insertUser(user1)
        userDao.insertUser(user2)

        // When
        val users = userDao.searchUsers(name, "name", "ASC").first()

        // Then
        assertEquals(1, users.size)
        assertEquals(user2, users[0])
    }

    @Test
    fun searchUsers_byUsername() = runTest {
        // Given
        val user1 = fakeUserEntityList[0]
        val user2 = fakeUserEntityList[1]
        val username = user2.username

        userDao.insertUser(user1)
        userDao.insertUser(user2)

        // When
        val users = userDao.searchUsers(username, "username", "ASC").first()

        // Then
        assertEquals(1, users.size)
        assertEquals(user2, users[0])
    }

    @Test
    fun searchUsers_sortByUsernameAsc() = runTest {
        // Given
        val user1 = fakeUserEntityList[0] // Tod86
        val user2 = fakeUserEntityList[1] // Constantin_Sawayn
        val user3 = fakeUserEntityList[2] // Lawrence_Nader62
        userDao.insertUser(user1)
        userDao.insertUser(user2)
        userDao.insertUser(user3)

        // When
        val users = userDao.searchUsers("", "username", "ASC").first()

        // Then
        assertEquals(3, users.size)
        assertEquals(user2, users[0]) // Constantin_Sawayn
        assertEquals(user3, users[1]) // Lawrence_Nader62
        assertEquals(user1, users[2]) // Tod86
    }

    @Test
    fun searchUsers_sortByUsernameDesc() = runTest {
        // Given
        val user1 = fakeUserEntityList[0] // Tod86
        val user2 = fakeUserEntityList[1] // Constantin_Sawayn
        val user3 = fakeUserEntityList[2] // Lawrence_Nader62
        userDao.insertUser(user1)
        userDao.insertUser(user2)
        userDao.insertUser(user3)

        // When
        val users = userDao.searchUsers("", "username", "DESC").first()

        // Then
        assertEquals(3, users.size)
        assertEquals(user1, users[0]) // Tod86
        assertEquals(user3, users[1]) // Lawrence_Nader62
        assertEquals(user2, users[2]) // Constantin_Sawayn
    }

    @Test
    fun searchUsers_sortByNameAsc() = runTest {
        // Given
        val user1 = fakeUserEntityList[0] // Sandrine Spinka
        val user2 = fakeUserEntityList[1] // Carli Carroll
        val user3 = fakeUserEntityList[2] // Annabelle Reilly
        userDao.insertUser(user1)
        userDao.insertUser(user2)
        userDao.insertUser(user3)

        // When
        val users = userDao.searchUsers("", "name", "ASC").first()

        // Then
        assertEquals(3, users.size)
        assertEquals(user3, users[0]) // Annabelle Reilly
        assertEquals(user2, users[1]) // Carli Carroll
        assertEquals(user1, users[2]) // Sandrine Spinka
    }

    @Test
    fun searchUsers_sortByNameDesc() = runTest {
        // Given
        val user1 = fakeUserEntityList[0] // Sandrine Spinka
        val user2 = fakeUserEntityList[1] // Carli Carroll
        val user3 = fakeUserEntityList[2] // Annabelle Reilly
        userDao.insertUser(user1)
        userDao.insertUser(user2)
        userDao.insertUser(user3)

        // When
        val users = userDao.searchUsers("", "name", "DESC").first()

        // Then
        assertEquals(3, users.size)
        assertEquals(user1, users[0]) // Sandrine Spinka
        assertEquals(user2, users[1]) // Carli Carroll
        assertEquals(user3, users[2]) // Annabelle Reilly
    }

}