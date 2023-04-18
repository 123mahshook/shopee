package com.mahshook.shopee


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.mahshook.shopee.ui.theme.ShopeeTheme
import com.mahshook.shopee.ui.theme.initSize

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSize()
        setContent {
            ShopeeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: MainScreenViewModel = MainScreenViewModel(),userProfiles: List<UserProfile> = userProfileList) {
    Scaffold(
        backgroundColor = Color.White,
        modifier = Modifier.fillMaxSize(),
        topBar = { AppBar() }
    ) { paddingvalues ->
        Surface(
            modifier = Modifier.padding(paddingvalues)
        ) {
            LazyColumn {
                items(userProfiles) { userProfile ->
                    profileCard(userProfile = userProfile)
                }
            }
        }
    }
}


@Composable
fun AppBar() {
    TopAppBar(
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.Home,
                modifier = Modifier.padding(horizontal = 12.dp),
                contentDescription = ""
            )
        },
        title = { Text("Start Messaging") }
    )
}

@Composable
fun profileCard(userProfile: UserProfile) {
    Card(
        backgroundColor = Color.White,
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 4.dp)
            .wrapContentHeight()
            .fillMaxWidth(),
        elevation = 8.dp


    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ){
            profileImage(userProfile.pictureUrl, userProfile.status)
            profileContent(userProfile.name, userProfile.status)
        }

    }
}

@Composable
fun profileImage(pictureUrl: String, onlineStatus: Boolean){
    Card(
        shape = CircleShape,
        border = BorderStroke(width = 2.dp,color= if (onlineStatus)
            Color.Green
        else Color.Red,),
        modifier = Modifier.padding(16.dp),
        elevation = 8.dp
    ) {
        Image(
            painter = rememberImagePainter(
                data = pictureUrl,
                builder = {
                    transformations(CircleCropTransformation())
                },
            ),
            modifier = Modifier.size(72.dp),
            contentDescription = "Profile picture description",
        )

    }

}

@Composable
fun profileContent(userName: String, onlineStatus: Boolean){
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = userName,
            style = MaterialTheme.typography.h5
        )


            Text(
                text = if (onlineStatus)
                    "Active now"
                else "Offline",
                style = typography.body2
            )
        }
    }



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ShopeeTheme {
        MainScreen()
    }
}