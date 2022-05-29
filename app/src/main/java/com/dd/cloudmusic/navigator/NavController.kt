package com.dd.cloudmusic.navigator

import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dd.cloudmusic.main.MainPage
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@Composable
fun NavController(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = RouteName.Main) {
        composable(RouteName.Main) { MainPage(navController) } //主页面
       /* composable("web?url={url}") { backStackEntry ->
            val url = backStackEntry.arguments?.getString("url") ?: ""
            Web(navController, url)
        }//web页面*/
    }

}