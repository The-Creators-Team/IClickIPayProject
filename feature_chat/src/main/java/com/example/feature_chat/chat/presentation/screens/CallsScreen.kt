package com.example.feature_chat.chat.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CallMade
import androidx.compose.material.icons.filled.CallReceived
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Videocam
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.feature_chat.R
import com.example.feature_chat.chat.data.callList
import com.example.feature_chat.chat.domain.CallListDataObject


@Composable
fun CallsScreen() {

    Column(modifier = Modifier.fillMaxSize().background(Color.LightGray)) {
        Text(
            text = "Today",
            style = TextStyle(fontSize = 14.sp, color = Color.Gray),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp)
                .align(Alignment.CenterHorizontally).padding(start = 175.dp)
        )

        Column() {
            Row(
                modifier = Modifier
                    .fillMaxWidth().padding(25.dp)
                   ,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = painterResource(callList[0].userImage),
                    contentDescription = "Caller Image",
                    modifier = Modifier
                        .size(50.dp)
                        .padding(end = 16.dp)
                        .clip(CircleShape)
                )

                Column {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = callList[0].userName,
                            style = TextStyle(fontSize = 16.sp, color = Color.Black),
                            modifier = Modifier.padding(bottom = 4.dp).weight(1f)
                        )

                        Icon(
                            imageVector = Icons.Outlined.Videocam,
                            contentDescription = "Video Icon",
                           tint = Color.Green,
                            modifier = Modifier.size(30.dp)
                        )
                    }


                    Row(
                    ) {
                        Icon(
                    imageVector = Icons.Filled.CallReceived,
                    contentDescription = "Call Received Icon",
                    tint = Color.Green,
                    modifier = Modifier.size(20.dp)
                )
                        Text(text = callList[0].timeStamp, style = TextStyle(fontSize = 14.sp, color = Color.Gray))

                    }
                }
            }
        }

        Column() {
            Row(
                modifier = Modifier
                    .fillMaxWidth().padding(start = 25.dp, end = 25.dp)
                ,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = painterResource(callList[1].userImage),
                    contentDescription = "Caller Image",
                    modifier = Modifier
                        .size(50.dp)
                        .padding(end = 16.dp)
                        .clip(CircleShape)
                )

                Column {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = callList[1].userName,
                            style = TextStyle(fontSize = 16.sp, color = Color.Black),
                            modifier = Modifier.padding(bottom = 4.dp).weight(1f)
                        )

                        Icon(
                            imageVector = Icons.Filled.Call,
                            contentDescription = "Call Icon",
                            tint = Color.Green,
                            modifier = Modifier.size(30.dp)
                        )
                    }


                    Row(
                    ) {
                        Icon(
                            imageVector = Icons.Filled.CallMade,
                            contentDescription = "Call Made Icon",
                            tint = Color.Yellow,
                            modifier = Modifier.size(20.dp)
                        )
                        Text(text = callList[1].timeStamp, style = TextStyle(fontSize = 14.sp, color = Color.Gray))

                    }
                }
            }
        }

        Text(
            text = "01 March 2019",
            style = TextStyle(fontSize = 14.sp, color = Color.Gray),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .align(Alignment.CenterHorizontally).padding(start = 160.dp)
        )

        Column() {
            Row(
                modifier = Modifier
                    .fillMaxWidth().padding(start = 25.dp, end = 25.dp, top = 25.dp)
                ,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = painterResource(callList[2].userImage),
                    contentDescription = "Caller Image",
                    modifier = Modifier
                        .size(50.dp)
                        .padding(end = 16.dp)
                        .clip(CircleShape)
                )

                Column {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = callList[2].userName,
                            style = TextStyle(fontSize = 16.sp, color = Color.Black),
                            modifier = Modifier.padding(bottom = 4.dp).weight(1f)
                        )

                        Icon(
                            imageVector = Icons.Filled.Call,
                            contentDescription = "Call Icon",
                            tint = Color.Green,
                            modifier = Modifier.size(30.dp)
                        )
                    }


                    Row(
                    ) {
                        Icon(
                            imageVector = Icons.Filled.CallReceived,
                            contentDescription = "Call Received Icon",
                            tint = Color.Green,
                            modifier = Modifier.size(20.dp)
                        )
                        Text(text = callList[2].timeStamp, style = TextStyle(fontSize = 14.sp, color = Color.Gray))

                    }
                }
            }
        }

        Column() {
            Row(
                modifier = Modifier
                    .fillMaxWidth().padding(start = 25.dp, end = 25.dp, top = 25.dp)
                ,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = painterResource(callList[3].userImage),
                    contentDescription = "Caller Image",
                    modifier = Modifier
                        .size(50.dp)
                        .padding(end = 16.dp)
                        .clip(CircleShape)
                )

                Column {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = callList[3].userName,
                            style = TextStyle(fontSize = 16.sp, color = Color.Black),
                            modifier = Modifier.padding(bottom = 4.dp).weight(1f)
                        )

                        Icon(
                            imageVector = Icons.Filled.Call,
                            contentDescription = "Call Icon",
                            tint = Color.Green,
                            modifier = Modifier.size(30.dp)
                        )
                    }


                    Row(
                    ) {
                        Icon(
                            imageVector = Icons.Filled.CallReceived,
                            contentDescription = "Call Received Icon",
                            tint = Color.Green,
                            modifier = Modifier.size(20.dp)
                        )
                        Text(text = callList[3].timeStamp, style = TextStyle(fontSize = 14.sp, color = Color.Gray))

                    }
                }
            }
        }

        Column() {
            Row(
                modifier = Modifier
                    .fillMaxWidth().padding(start = 25.dp, end = 25.dp, top = 25.dp)
                ,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = painterResource(callList[4].userImage),
                    contentDescription = "Caller Image",
                    modifier = Modifier
                        .size(50.dp)
                        .padding(end = 16.dp)
                        .clip(CircleShape)
                )

                Column {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = callList[4].userName,
                            style = TextStyle(fontSize = 16.sp, color = Color.Black),
                            modifier = Modifier.padding(bottom = 4.dp).weight(1f)
                        )

                        Icon(
                            imageVector = Icons.Filled.Call,
                            contentDescription = "Call Icon",
                            tint = Color.Green,
                            modifier = Modifier.size(30.dp)
                        )
                    }


                    Row(
                    ) {
                        Icon(
                            imageVector = Icons.Filled.CallMade,
                            contentDescription = "Call Made Icon",
                            tint = Color.Yellow,
                            modifier = Modifier.size(20.dp)
                        )
                        Text(text = callList[4].timeStamp, style = TextStyle(fontSize = 14.sp, color = Color.Gray))

                    }
                }
            }
        }

        Text(
            text = "28 February 2019",
            style = TextStyle(fontSize = 14.sp, color = Color.Gray),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .align(Alignment.CenterHorizontally).padding(start = 150.dp)
        )

        Column() {
            Row(
                modifier = Modifier
                    .fillMaxWidth().padding(start = 25.dp, end = 25.dp, top = 25.dp)
                ,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = painterResource(callList[5].userImage),
                    contentDescription = "Caller Image",
                    modifier = Modifier
                        .size(50.dp)
                        .padding(end = 16.dp)
                        .clip(CircleShape)
                )

                Column {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = callList[5].userName,
                            style = TextStyle(fontSize = 16.sp, color = Color.Black),
                            modifier = Modifier.padding(bottom = 4.dp).weight(1f)
                        )

                        Icon(
                            imageVector = Icons.Filled.Call,
                            contentDescription = "Call Icon",
                            tint = Color.Green,
                            modifier = Modifier.size(30.dp)
                        )
                    }


                    Row(
                    ) {
                        Icon(
                            imageVector = Icons.Filled.CallMade,
                            contentDescription = "Call Made Icon",
                            tint = Color.Yellow,
                            modifier = Modifier.size(20.dp)
                        )
                        Text(text = callList[5].timeStamp, style = TextStyle(fontSize = 14.sp, color = Color.Gray))

                    }
                }
            }
        }

        Column() {
            Row(
                modifier = Modifier
                    .fillMaxWidth().padding(start = 25.dp, end = 25.dp, top = 25.dp)
                ,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = painterResource(callList[6].userImage),
                    contentDescription = "Caller Image",
                    modifier = Modifier
                        .size(50.dp)
                        .padding(end = 16.dp)
                        .clip(CircleShape)
                )

                Column {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = callList[6].userName,
                            style = TextStyle(fontSize = 16.sp, color = Color.Black),
                            modifier = Modifier.padding(bottom = 4.dp).weight(1f)
                        )

                        Icon(
                            imageVector = Icons.Filled.Call,
                            contentDescription = "Call Icon",
                            tint = Color.Green,
                            modifier = Modifier.size(30.dp)
                        )
                    }


                    Row(
                    ) {
                        Icon(
                            imageVector = Icons.Filled.CallMade,
                            contentDescription = "Call Made Icon",
                            tint = Color.Yellow,
                            modifier = Modifier.size(20.dp)
                        )
                        Text(text = callList[6].timeStamp, style = TextStyle(fontSize = 14.sp, color = Color.Gray))

                    }
                }
            }
        }
    }
}
//
//@Composable
//fun CallListItem(call: CallListDataObject) {
//
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(16.dp)
//            .background(Color.White)
//            .padding(12.dp),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//
//        Image(
//            painter = painterResource(call.userImage),
//            contentDescription = "Caller Image",
//            modifier = Modifier
//                .size(50.dp)
//                .padding(end = 16.dp)
//                .clip(CircleShape)
//        )
//
//        Column(modifier = Modifier.weight(1f)) {
//            Row(
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Text(
//                    text = call.userName,
//                    style = TextStyle(fontSize = 16.sp, color = Color.Black),
//                    modifier = Modifier.padding(bottom = 4.dp).weight(1f)
//                )
//
//                Icon(
//                    imageVector = androidx.compose.material.icons.Icons.Default.Call,
//                    contentDescription = "Phone Icon",
//                    tint = Color.Green,
//                    modifier = Modifier.size(20.dp)
//                )
//            }
//
//
//            Row(
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Text(text = call.timeStamp, style = TextStyle(fontSize = 14.sp, color = Color.Gray),modifier = Modifier.weight(1f))
//
//            }
//        }
//    }
//}



@Preview(showBackground = true)
@Composable
fun CallsScreenPreview(){
    CallsScreen()
}