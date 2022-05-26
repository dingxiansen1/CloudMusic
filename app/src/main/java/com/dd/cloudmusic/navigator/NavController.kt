package com.dd.cloudmusic.navigator

import androidx.compose.foundation.background
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dd.cloudmusic.find.FindPage
import com.dd.cloudmusic.home.HomePage
import com.dd.cloudmusic.main.MainPage
import com.dd.cloudmusic.mine.MinePage
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding

@Composable
fun NavController(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main") {
        composable(RouteName.Main) { MainPage(navController) } //主页面
       /* composable("web?url={url}") { backStackEntry ->
            val url = backStackEntry.arguments?.getString("url") ?: ""
            Web(navController, url)
        }//web页面*/
    }

}