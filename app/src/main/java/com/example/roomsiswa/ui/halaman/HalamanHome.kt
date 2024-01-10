package com.example.roomsiswa.ui.halaman

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roomsiswa.Greeting
import com.example.roomsiswa.R
import com.example.roomsiswa.data.Barang
import com.example.roomsiswa.model.HomeViewModel
import com.example.roomsiswa.model.PenyediaViewModel
import com.example.roomsiswa.navigasi.DestinasiNavigasi
import com.example.roomsiswa.navigasi.BarangTopAppBar
import com.example.roomsiswa.ui.theme.RoomSiswaTheme
import kotlinx.coroutines.flow.MutableStateFlow

object DestinasiHome : DestinasiNavigasi {
    override val route = "home"
    override val titleRes = R.string.app_name
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (Int) -> Unit = {},
    viewModel: HomeViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        modifier = modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),

        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.padding_small))
            ){
            }
            BarangTopAppBar(
                title = stringResource(DestinasiHome.titleRes),
                canNavigateBack = false,
                scrollBehavior = scrollBehavior,
                modifier = modifier
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFF3700B3),
                                Color(0xFFBB86FC)
                            )
                        )
                    )

            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large))
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.entry_data)
                )
            }
        },
    ) { innerPadding ->
        val uiStateBarang by viewModel.homeUiState.collectAsState()
        BodyHome(
            itemBarang = uiStateBarang.listBarang,
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF3700B3),
                            Color(0xFFBB86FC)
                        )
                    )
                )
                .padding(innerPadding)
                .fillMaxSize(),
            onBarangClick = onDetailClick
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BodyHome(
    itemBarang: List<Barang>,
    modifier: Modifier = Modifier,
    onBarangClick: (Int) -> Unit = {}
) {
    var searchQuery by remember { mutableStateOf("") }
    
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        TextField(
            value = searchQuery,
            onValueChange = {newQuery ->
                searchQuery = newQuery
        },
            placeholder = {
                Text(text = stringResource(R.string.search))
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_small))
            )
        
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_small)))
        
        if (itemBarang.isEmpty()) {
            Text(
                text = stringResource(R.string.deskripsi_no_item),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
        } else {
            ListBarang(
                itemBarang = itemBarang.filter { it.nama.contains(searchQuery, ignoreCase = true) },
                modifier = Modifier
                    .padding(horizontal = dimensionResource(id = R.dimen.padding_small)),
                onItemClick = { onBarangClick(it.id) }
            )
        }
    }
}

@Composable
fun ListBarang(
    itemBarang: List<Barang>,
    modifier: Modifier = Modifier,
    onItemClick: (Barang) -> Unit
) {
    LazyColumn(modifier = Modifier) {
        items(items = itemBarang, key = { it.id }) { person ->
            DataBarang(
                barang = person,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))
                    .clickable { onItemClick(person) }
            )
        }
    }
}

@Composable
fun DataBarang(
    barang: Barang,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = barang.tanggal,
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = null,
                )
                Text(
                    text = barang.kode,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Text(
                text = barang.nama,
                style = MaterialTheme.typography.titleLarge,
            )
            Text(
                text = barang.jumlah,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = barang.harga,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

