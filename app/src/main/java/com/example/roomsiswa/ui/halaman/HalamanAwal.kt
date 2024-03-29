package com.example.roomsiswa.ui.halaman

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.roomsiswa.R
import com.example.roomsiswa.navigasi.DestinasiNavigasi

object DestinasiHalamanAwal : DestinasiNavigasi {
    override val route = "halamanAwal"
    override val titleRes = R.string.app_name
}
@Composable
fun HalamanAwal(
    onNextButtonClicked: () -> Unit)
{
    val image = painterResource(id = R.drawable.inven)
    Column (modifier = Modifier
            .background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color(0xFF3700B3),
                    Color(0xFFBB86FC)
                )
            )
            ),
        verticalArrangement = Arrangement.SpaceBetween){
        OutlinedCard (
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
            ),
            border = BorderStroke(1.dp, Color.Black), modifier = Modifier
                .fillMaxWidth(0.95f)
                .padding(vertical = 30.dp)
                .shadow(16.dp, RoundedCornerShape(16.dp))
                .align(Alignment.CenterHorizontally)
        ){
            Column (horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)) {
                Image(
                    painter = image,
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
                Divider(
                    modifier = Modifier.padding(vertical = 10.dp),
                    color = Color(0xFF6200EE),
                    thickness = 2.dp
                )
                Text(
                    text = "Selamat Datang",
                    color = Color.DarkGray,
                    fontFamily = FontFamily.Cursive,
                    fontSize = 35.sp,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                )
                Text(
                    text = "INVENTORYKU",
                    fontSize = 24.sp,
                    color = Color(0xFF6200EE),
                    fontWeight = FontWeight.Bold,
                )
            }
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_medium))
                .weight(1f, false),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
        ){
            Button(modifier = Modifier
                .padding(100.dp)
                .weight(1f),
                onClick = onNextButtonClicked
            ) {
                Text(stringResource(R.string.Next))
            }
        }
    }
}