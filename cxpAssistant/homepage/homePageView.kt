package com.google.ai.cxpAssistant.homepage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.ai.cxpAssistant.R

@Composable
fun HomePageScreen(viewModel: homepageViewModel = homepageViewModel(), launchChat: () -> Unit, backHome: ()-> Unit) {
    val uiState by viewModel.uiState.collectAsState()
    LandingScreen(launchChat = { launchChat() }, backHome = { backHome() })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandingScreen(launchChat: () -> Unit = {}, backHome: () -> Unit = {}) {
    Scaffold(containerColor = Color(0xffe8ebed), modifier = Modifier.background(color = Color(0xffe8ebed)), topBar =
    {
        TopAppBar(colors = TopAppBarDefaults.topAppBarColors(Color.White),
            title =
            { Text(text = "Workday Home Page", fontWeight = FontWeight.SemiBold) },
            actions = {
                IconButton(onClick = { backHome() }) {
                    Icon(
                        imageVector = Icons.Filled.Home,
                        contentDescription = "Home Menu",
                    )
                }
            })
    },
        bottomBar = {
            BottomAppBar {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White), Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    MenuItem(title = "Home", icon = Icons.Default.Home,)
                    MenuItem(title = "Apps", icon = Icons.Default.AddCircle)
                    MenuItem(title = "My Tasks", icon = Icons.Default.Menu)
                    MenuItem(title = "Find", icon = Icons.Default.Info)
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { launchChat() }, content = {
                Column(horizontalAlignment = Alignment.CenterHorizontally){
                    Icon(Icons.Default.Create, contentDescription = "", modifier = Modifier.padding(top = 8.dp))
                    Text(modifier = Modifier.padding(8.dp), text = "CXP AI")
                }})
        }

    ) { innerPadding ->
        Column(
            Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            Column(modifier = Modifier.align(Alignment.CenterHorizontally)
                .clip(RoundedCornerShape(32.dp))
                .width(372.dp)
                .padding(20.dp)
                .background(color = Color.White, shape = RoundedCornerShape(16.dp))
                , horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceAround) {

                Text(modifier = Modifier.padding(16.dp), text = "Let's Focus on You", fontWeight = FontWeight.SemiBold, fontSize = 22.sp)
                Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()){
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(Icons.Default.PlayArrow, contentDescription = "", modifier = Modifier.size(42.dp))
                        Text(text = "Tutorials")
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(Icons.Default.AccountBox, contentDescription = "", modifier = Modifier.size(42.dp))
                        Text(text = "Accounts")
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(Icons.Default.AccountCircle, contentDescription = "", modifier = Modifier.size(42.dp))
                        Text(text = "Settings")
                    }
                }
                Text(modifier = Modifier.padding(18.dp), text = "View All", fontSize = 16.sp)

            }
            Text(
                modifier = Modifier.padding(8.dp),
                text = "Announcement",
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp
            )

            Bar()
            Text(
                modifier = Modifier.padding(8.dp),
                text = "Timely Suggestions",
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp
            )

            LazyRow(modifier = Modifier.padding(8.dp)) {
                item {
                    WorkerCard(description = "Embark Month 2-3")
                }
                item {
                    WorkerCard(description = "Embark Month 4-4")
                }
                item {
                    WorkerCard(description = "Embark Month 7-4")
                }
            }
            Bar(title = "Required Learning Disability", subtitle = "Start Learning Today")
            Bar(title = "Required Learning Disability", subtitle = "Start Learning Today")

            Text(
                modifier = Modifier.padding(8.dp),
                text = "Your Applications",
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp
            )

            LazyRow() {
                item {
                    WorkerCard(description = "Embark Month 2-3")
                }
                item {
                    WorkerCard(description = "Embark Month 4-4")
                }
                item {
                    WorkerCard(description = "Embark Month 7-4")
                }
            }
        }
    }
}

@Preview
@Composable
fun MenuItem(
    title: String = "Home", icon: ImageVector = Icons.Default.Home
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(icon, contentDescription = "")
        Text(text = title, textAlign = TextAlign.Center)
    }
}

@Preview
@Composable
fun Bar(
    title: String = "Healthy Workplace Policy",
    subtitle: String = "Please Complete this one time ackno.."
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .padding(9.dp)
            .background(color = Color.White, shape = RoundedCornerShape(8.dp))
            .fillMaxWidth()
    ) {
        Box(
            Modifier
                .size(44.dp)
                .padding(start = 8.dp)
                .background(color = Color(0xff0875e1))
        ) {}
        Column(modifier = Modifier.padding(start = 16.dp, top = 12.dp, bottom = 12.dp)) {
            Text(text = title, fontWeight = FontWeight.SemiBold)
            Text(text = subtitle)
        }
    }
}


@Preview
@Composable
fun WorkerCard(description: String = "Sample App Description") {
    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .padding(8.dp), colors = CardDefaults.cardColors(
            containerColor = Color.White,
        )
    ) {
        Column(Modifier.fillMaxWidth()) {
            Image(
                modifier = Modifier
                    .width(123.dp)
                    .align(Alignment.CenterHorizontally),
                painter = painterResource(id = R.drawable.placeholder),
                contentDescription = ""
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 16.dp),
                text = description,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Preview
@Composable
fun CardShape() {
    Card(shape = CircleShape, modifier = Modifier.size(20.dp)) {

    }
}