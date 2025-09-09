package com.example.mydatasiswa.uicontroller

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mydatasiswa.uicontroller.route.DestinasiDetail
import com.example.mydatasiswa.uicontroller.route.DestinasiEdit
import com.example.mydatasiswa.uicontroller.route.DestinasiEntry
import com.example.mydatasiswa.uicontroller.route.DestinasiHome
import com.example.mydatasiswa.view.DetailSiswaScreen
import com.example.mydatasiswa.view.HomeScreen
import com.example.mydatasiswa.view.EntrySiswaScreen

@Composable
fun DataSiswaApp(navController: NavHostController = rememberNavController(),
                 modifier: Modifier = Modifier) {
    HostNavigasi(navController = navController)
}

@Composable
fun HostNavigasi(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = modifier
    ) {
        // Defines the composable screen for the home destination.
        composable(route = DestinasiHome.route) {
            HomeScreen(navigateToItemEntry = {navController.navigate(DestinasiEntry.route)}, navigateToItemUpdate = {
                navController.navigate("${DestinasiDetail.route}/${it}")
            })
        }
        composable (DestinasiEntry.route){
            EntrySiswaScreen(navigateBack = {navController.navigate(DestinasiHome.route)})
        }

        composable (route = DestinasiDetail.routeWithArgs,arguments = listOf(navArgument(name = DestinasiDetail.itemIdArg){
            type = NavType.IntType})
        ){
            DetailSiswaScreen(navigateBack={ navController.navigate(route = DestinasiHome.route)})

        }
    }
}