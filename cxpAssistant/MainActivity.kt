/*
 * Copyright 2023 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.ai.cxpAssistant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.google.ai.cxpAssistant.feature.chat.ChatRoute
import com.google.ai.cxpAssistant.feature.chat.ChatViewModel
import com.google.ai.cxpAssistant.homepage.HomePageScreen

class MainActivity : ComponentActivity() {
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        apiService = RetrofitClient.api
        setContent {
                // A surface container using the 'background' color from the theme
                    val navController = rememberNavController()
                    val chatViewModel: ChatViewModel = viewModel(factory = GenerativeViewModelFactory)
                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") {
                            HomePageScreen(
                                launchChat = { navController.navigate("chat") },
                                backHome = { navController.navigate("home") })
                        }
                        composable("chat") {
                            ChatRoute(chatViewModel, backHome = { navController.navigate("home") })
                        }
                    }
        }
    }
}
