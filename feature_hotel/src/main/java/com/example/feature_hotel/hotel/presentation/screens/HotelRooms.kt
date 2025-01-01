package com.example.feature_hotel.hotel.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.feature_hotel.R

@Composable
fun RoomSelector() {
    var rooms by remember { mutableStateOf(
        mutableListOf(
            Room(1,2, 1),
            Room(2,2, 2)
        )
    )}

    var room = 2




    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start){
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.Red,
                modifier = Modifier.size(24.dp)
            )
        }

        Image(
            painter = painterResource(R.drawable.hotel_room),
            contentDescription = "Hotel Image",
            modifier = Modifier
                .size(200.dp)
        )

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End){
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Favorite",
                modifier = Modifier.size(24.dp)
            )
        }
        Text(
            text = "Number of rooms",
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 20.sp
        )
        val totalRooms = rooms.size
        val totalAdults = rooms.sumOf { it.adults }
        val totalKids = rooms.sumOf { it.kids }

        Text(
            text = "Rooms: $totalRooms  Adults: $totalAdults  Kids: $totalKids",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(16.dp))


        LazyRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(rooms) { room ->
                RoomCard(room) { updatedRoom ->

                    rooms = rooms.map {
                        if (it == room) updatedRoom else it
                    }.toMutableList()
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))


        Button(
            onClick = {
                rooms.add(Room(++room, 1, 0)) // Add a room with 1 adult, 0 kids
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Room")
        }

        Button(
            onClick = {
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit")
        }
    }
}

@Composable
fun RoomCard(room: Room, onRoomUpdated: (Room) -> Unit) {
    Card(
        modifier = Modifier
            .padding(end = 8.dp)
            .width(160.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.elevatedCardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(text = "Adults: ${room.adults}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Kids (included): ${room.kids}", style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(8.dp))


            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                IconButton(onClick = { onRoomUpdated(room.copy(adults = room.adults + 1)) }) {
                    Icon(Icons.Default.Add, contentDescription = "Add Adult")
                }
                IconButton(onClick = { if (room.adults > 0) onRoomUpdated(room.copy(adults = room.adults - 1)) }) {
                    Icon(Icons.Default.Remove, contentDescription = "Remove Adult")
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                IconButton(onClick = { onRoomUpdated(room.copy(kids = room.kids + 1)) }) {
                    Icon(Icons.Default.Add, contentDescription = "Add Kid")
                }
                IconButton(onClick = { if (room.kids > 0) onRoomUpdated(room.copy(kids = room.kids - 1)) }) {
                    Icon(Icons.Default.Remove, contentDescription = "Remove Kid")
                }
            }
        }
    }
}

data class Room(var rooms: Int, var adults: Int, var kids: Int)

@Preview(showBackground = true)
@Composable
fun RoomSelectorPreview() {
    RoomSelector()
}
