package com.example.iclickipay.presentation.chat.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.iclickipay.data.tabs

@Composable
fun TabBarComponent(initialIndex: Int = 0,
                    pagerState: PagerState,
                    onTabSelected: (Int) -> Unit) {
    var selectedIndex by remember{
        mutableStateOf(initialIndex)
    }
    TabRow(
        selectedTabIndex = selectedIndex,
        modifier = Modifier.fillMaxWidth(),
        containerColor = Color.LightGray,
        contentColor = Color.Gray,
        indicator = {tabPosition ->
            TabRowDefaults.Indicator(
                modifier = Modifier.tabIndicatorOffset(tabPosition[selectedIndex]),
                height = 4.dp)}){
        tabs.forEachIndexed {index,tabData ->
            Tab(selected = index == selectedIndex,
                onClick = {
                    selectedIndex = index
                    onTabSelected(selectedIndex)
                          },
                        text = {
                            TabTitle(title = tabData.title)})}
    }
}

@Composable
fun TabTitle(title:String) {
    Text(
        text = title,
        style = TextStyle(
            fontSize = 16.sp
        )
    )
}

//@Preview
//@Composable
//fun TabBarComponentPreview(){
//    TabBarComponent()
//}