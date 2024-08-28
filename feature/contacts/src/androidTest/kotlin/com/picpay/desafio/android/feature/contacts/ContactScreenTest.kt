package com.picpay.desafio.android.feature.contacts

import android.util.Log
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import com.picpay.desafio.android.core.testing.DesafioAppKoinTestRule
import com.picpay.desafio.android.feature.contacts.di.test.testingFeatureContactsModule
import com.picpay.desafio.android.feature.contacts.viewmodel.ContactsScreenViewModel
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject

class ContactScreenTest: KoinTest {

    @get:Rule
    val composeTestRule = createComposeRule()


    @get:Rule
    val testRule = DesafioAppKoinTestRule(
        listOf(testingFeatureContactsModule)
    )

    private val viewModel: ContactsScreenViewModel by inject()


//    @Test
//    fun testContactScreen_displaysTitleInitially() {
//        composeTestRule.setContent {
//            ContactScreen()
//        }
//
//        composeTestRule
//            .onNodeWithTag(TITLE_TEST_TAG)
//            .assertIsDisplayed()
//    }

    @Test
    fun testContactScreen_initialState_shouldDisplay_SearchField() {
        composeTestRule.setContent {
            ContactScreen()
        }

        Log.w("TESTTTT", "isSyncing ? = ${viewModel.uiState.value.isSyncing}" )
        Log.w("TESTTTT", "condition ? = ${viewModel.uiState.value.condition}" )

        composeTestRule.onRoot().printToLog("TESTTTT TAG")

        composeTestRule.waitForIdle()
        composeTestRule.runOnIdle {
            Log.w("TESTTTT ON IDLE", "isSyncing ? = ${viewModel.uiState.value.isSyncing}" )
            Log.w("TESTTTT ON IDLE", "condition ? = ${viewModel.uiState.value.condition}" )
        }

        composeTestRule
            .onNodeWithText("Pesquisar")
            .assertIsDisplayed()


    }

//    @Test
//    fun testContactScreen_initialState_shouldDisplay_ContactList() {
//        composeTestRule.setContent {
//            ContactScreen()
//        }
//
//        composeTestRule
//            .onNodeWithText(CONTACT_LIST_TEST_TAG)
//            .assertIsDisplayed()
//    }
}