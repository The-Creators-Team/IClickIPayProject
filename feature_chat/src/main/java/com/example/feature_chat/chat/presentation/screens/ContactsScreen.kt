package com.example.feature_chat.chat.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Group
import androidx.compose.material.icons.outlined.PersonAddAlt
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.feature_chat.chat.data.contactList
import com.example.feature_chat.chat.domain.ContactListDataObject
import kotlinx.coroutines.launch


fun getGroupedContacts(contacts: List<ContactListDataObject>): List<Pair<String, List<ContactListDataObject>>> {

    val favorites = contacts.filter { it.isFavorite }
    val nonFavorites = contacts.filter { !it.isFavorite }


    val groupedNonFavorites = nonFavorites.groupBy { it.userName.first().uppercaseChar() }


    return listOf(
        "Favorites" to favorites
    ) + groupedNonFavorites.map { (letter, contactList) ->
        letter.toString() to contactList
    }
}

@Composable
fun ContactListWithFavorites(contacts: List<ContactListDataObject>, onLetterTapped: (Char) -> Unit) {
    val groupedContacts = getGroupedContacts(contacts)
    val scrollState = rememberLazyListState()


    val coroutineScope = rememberCoroutineScope()

    val scrollToLetter = remember { { letter: Char ->
        val index = groupedContacts.indexOfFirst { it.first == letter.toString() }
        if (index != -1) {

            coroutineScope.launch {
                scrollState.animateScrollToItem(index)
            }
        }
    } }

    Column(modifier = Modifier.fillMaxSize().background(Color.LightGray)) {
        Text(
            text = "76 contacts",
            style = TextStyle(fontSize = 14.sp, color = Color.Gray),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp)
                .align(Alignment.CenterHorizontally).padding(start = 150.dp)
        )

        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = Icons.Outlined.PersonAddAlt,
                contentDescription = "",
                modifier = Modifier
                    .size(40.dp).clip(CircleShape).background(color = Color.Green, shape = CircleShape),
                tint = Color.White
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(text = "New contact", fontWeight = FontWeight.SemiBold)

        }
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = Icons.Outlined.Group,
                contentDescription = "",
                modifier = Modifier
                    .size(40.dp).clip(CircleShape).background(color = Color.Green, shape = CircleShape),
                tint = Color.White
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(text = "New group", fontWeight = FontWeight.SemiBold)
        }

        Row(
            modifier = Modifier.fillMaxHeight().padding(8.dp)
        ) {

            LazyColumn(state = scrollState, modifier = Modifier.weight(1f)) {
                groupedContacts.forEach { (header, contactList) ->
                    item {
                        if (header == "Favorites") {

                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = "Favorites",
                                tint = Color.Yellow,
                                modifier = Modifier.size(24.dp)
                            )

                            contactList.forEach { contact ->
                                ContactItem(contact = contact)
                            }
                        } else {

                            Text(
                                text = header,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
                            )

                            contactList.forEach { contact ->
                                ContactItem(contact = contact)
                            }
                        }
                    }
                }
            }


            AlphabetIndex(letters = groupedContacts.filter { it.first != "Favorites" }.map { it.first.first() }, onLetterTapped = { scrollToLetter(it) })
        }
    }
}

@Composable
fun ContactItem(contact: ContactListDataObject) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp).background(Color.LightGray)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = contact.userImage),
                contentDescription = "Image of ${contact.userName}",
                modifier = Modifier
                    .size(48.dp).clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(16.dp))


            Column(modifier = Modifier.weight(1f)) {
                Text(text = contact.userName)
                Text(text = contact.userDetails, style = TextStyle(color = Color.Gray))
            }
        }
    }
}

@Composable
fun AlphabetIndex(letters: List<Char>, onLetterTapped: (Char) -> Unit) {
    val sortedLetters = letters.sorted()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp)
            .fillMaxHeight()
            .width(30.dp)
    ) {
        sortedLetters.forEach { letter ->
            Text(
                text = letter.toString(),
                fontSize = 14.sp,
                color = Color.Gray, // Set color to gray
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .clickable { onLetterTapped(letter) }
            )
        }
    }
}





@Composable
fun ContactsScreen(){
    ContactListWithFavorites(contacts = contactList, onLetterTapped = {})
}

@Preview(showBackground = true)
@Composable
fun ContactsScreenPreview(){
    ContactsScreen()
}