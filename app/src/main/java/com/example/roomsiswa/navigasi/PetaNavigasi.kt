package com.example.roomsiswa.navigasi

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.roomsiswa.R
import com.example.roomsiswa.login.AuthViewModel
import com.example.roomsiswa.login.DestinasiHalamanLogin
import com.example.roomsiswa.login.HalamanLogin
import com.example.roomsiswa.ui.halaman.DestinasiEntry
import com.example.roomsiswa.ui.halaman.DestinasiHalamanAwal
import com.example.roomsiswa.ui.halaman.DestinasiHome
import com.example.roomsiswa.ui.halaman.DetailsDestination
import com.example.roomsiswa.ui.halaman.DetailsScreen
import com.example.roomsiswa.ui.halaman.EntryBarangScreen
import com.example.roomsiswa.ui.halaman.HalamanAwal
import com.example.roomsiswa.ui.halaman.HomeScreen
import com.example.roomsiswa.ui.halaman.ItemEditDestination
import com.example.roomsiswa.ui.halaman.ItemEditScreen


@Composable
fun BarangApp(navController: NavHostController = rememberNavController()) {
    HostNavigasi(navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarangTopAppBar(
    title: String,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    navigateUp: () -> Unit = {},
) {
    CenterAlignedTopAppBar(title = { Text(title) },
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back)
                    )
                }
            }
        })
}

@Composable
fun HostNavigasi(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel = viewModel()
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHalamanAwal.route,
        modifier = Modifier
    )
    {

        composable(DestinasiHalamanAwal.route) {
            HalamanAwal(
                onNextButtonClicked = {
                    navController.navigate(DestinasiHalamanLogin.route)
                })
        }

        composable(DestinasiHalamanLogin.route) {
            HalamanLogin(
                onLoginButtonClicked = { username, password ->
                    if (authViewModel.authenticate(username, password)) {
                        navController.navigate(DestinasiHome.route)
                    } else {

                    }
                }
            )}

        composable(DestinasiHome.route) {
            HomeScreen(
                navigateToItemEntry = { navController.navigate(DestinasiEntry.route) },
                onDetailClick = { itemId ->
                    navController.navigate("${DetailsDestination.route}/$itemId")
                },
            )
        }
        composable(DestinasiEntry.route) {
            EntryBarangScreen(navigateBack = { navController.popBackStack() })
        }

        composable(
            DetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(DetailsDestination.barangIdArg) {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getInt(DetailsDestination.barangIdArg)
            itemId?.let {
                DetailsScreen(
                    navigateBack = { navController.popBackStack() },
                    navigateToEditItem = { navController.navigate("${ItemEditDestination.route}/$it") }
                )
            }
        }

        composable(
            ItemEditDestination.routeWithArgs,
            arguments = listOf(navArgument(ItemEditDestination.itemIdArg) {
                type = NavType.IntType
            })
        ) {
            ItemEditScreen(navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() })
        }


    }
}