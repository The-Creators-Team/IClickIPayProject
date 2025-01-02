package com.examole.feature_mechanic.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.examole.feature_mechanic.domain.model.Mechanic
import com.examole.feature_mechanic.presentation.routes.MechanicsScreenRoutes
import com.example.feature_mechanic.R


@Composable
fun ListCard(movers: Mechanic, navController: NavController) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        onClick = { navController.navigate(MechanicsScreenRoutes.MechanicProfileScreen.route+"/${movers.id}") },


    ) {
        Column{

            Image(
                painter = painterResource(id = R.drawable.moverlogo),
                contentDescription = "Mover Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))


            Text(
                text = movers.name,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Text(
                text = movers.location,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Rating
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.Star, contentDescription = "Rating")
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "${movers.rating}")
                }


                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.LocationOn, contentDescription = "Distance", tint = Color.Gray)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "${movers.id} m")
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "$${movers.id}/hr")
                }
            }
        }
    }
}