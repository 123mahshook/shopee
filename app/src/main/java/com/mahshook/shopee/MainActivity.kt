package com.mahshook.shopee


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
                    UsersApplication()
                }
            }
        }
    }
}

@Composable
fun UsersApplication(userProfiles: List<UserProfile> = userProfileList) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "users_list") {
        composable("users_list") {
            MainScreen(userProfiles, navController)
        }
        composable(
            route = "user_details/{userId}",
            arguments = listOf(navArgument("userId") {
                type = NavType.IntType
            })
        ) { navBackStackEntry ->
            ProfileDetailsScreen(navBackStackEntry.arguments!!.getInt("userId"), navController)
        }
    }
}

@Composable
fun MainScreen(userProfiles: List<UserProfile> = userProfileList,navController: NavHostController?) {
    Scaffold(
        backgroundColor = Color.White,
        modifier = Modifier.fillMaxSize(),
        topBar = {    AppBar(
            title = "Users list",
            icon = Icons.Default.Home
        ) { } }
    ) { paddingvalues ->
        Surface(
            modifier = Modifier.padding(paddingvalues)
        ) {
            LazyColumn {
                items(userProfiles) { userProfile ->
                    profileCard(userProfile = userProfile){
                        navController?.navigate("user_details/${userProfile.id}")
                    }
                }
            }
        }
    }
}


@Composable
fun ProfileDetailsScreen(userId: Int, navController: NavHostController?) {
    val userProfiles = userProfileList.first { userProfile:UserProfile -> userId == userProfile.id }
    Scaffold(
        backgroundColor = Color.White,
        modifier = Modifier.fillMaxSize(),
        topBar = {  AppBar(
            title = "User profile details",
            icon = Icons.Default.ArrowBack
        ) {
            navController?.navigateUp()
        } }
    ) { paddingvalues ->
        Surface(
            modifier = Modifier.padding(paddingvalues)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ){
                profileImage(userProfiles.pictureUrl, userProfiles.status,240.dp)
                profileContent(userProfiles.name, userProfiles.status,Alignment.CenterHorizontally)
            }

            }
        }
    }



@Composable
fun AppBar(title: String, icon: ImageVector, iconClickAction: () -> Unit) {
    TopAppBar(
        navigationIcon = {
            Icon(
                icon,
                contentDescription = "",
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .clickable(onClick = { iconClickAction.invoke() })
            )
        },
        title = { Text(title) }
    )
}

@Composable
fun profileCard(userProfile: UserProfile,clickAction: () -> Unit) {
    Card(
        backgroundColor = Color.White,
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 4.dp)
            .wrapContentHeight()
            .clickable(onClick = { clickAction.invoke() })
            .fillMaxWidth(),
        elevation = 8.dp


    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ){
            profileImage(userProfile.pictureUrl, userProfile.status,72.dp)
            profileContent(userProfile.name, userProfile.status,Alignment.Start)
        }

    }
}

@Composable
fun profileImage(pictureUrl: String, onlineStatus: Boolean,imageSize:Dp){
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
            modifier = Modifier.size(imageSize),
            contentDescription = "Profile picture description",
        )

    }

}

@Composable
fun profileContent(userName: String, onlineStatus: Boolean,alignment: Alignment.Horizontal){
    Column(
        modifier = Modifier
            .padding(8.dp),
           // .fillMaxWidth()
    horizontalAlignment = alignment
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



/*
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ShopeeTheme {
        MainScreen()
    }
}*/
