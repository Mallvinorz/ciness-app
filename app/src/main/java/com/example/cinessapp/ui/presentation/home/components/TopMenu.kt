package com.example.cinessapp.ui.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cinessapp.R
import com.example.cinessapp.core.utils.AppIcons
import com.example.cinessapp.ui.theme.CinessAppTheme

@Composable
fun TopMenu(
    modifier: Modifier = Modifier
) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp),
        shadowElevation = 4.dp
    ) {
        Column(
            modifier = modifier.padding(horizontal = 16.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Image(
                    painter = painterResource(R.drawable.ciness_logo),
                    contentDescription = "Top Menu Logo",
                    modifier = Modifier.size(48.dp)
                )
                Column() {
                    Text(
                        "Hi, Guest",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.displayLarge
                    )
                    Text(
                        "What do you want yo watch today?",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.displaySmall
                    )
                }
            }
//            SearchField()
        }
    }
}

@Composable
fun SearchField(modifier: Modifier = Modifier) {
    var query by remember { mutableStateOf("") }

    TextField(
        value = query,
        onValueChange = { query = it },
        modifier = modifier
            .fillMaxWidth(),
        placeholder = {
            Text(
                "Search movies, TV shows..",
                color = MaterialTheme.colorScheme.primary
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(AppIcons.search),
                contentDescription = "Search Icon",
                tint = MaterialTheme.colorScheme.primary,
                modifier = modifier.size(20.dp)
            )
        },
        shape = RoundedCornerShape(16.dp),
        singleLine = true,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        )
    )
}

@Preview()
@Composable
private fun SearchFieldPreview() {
    CinessAppTheme() {
        SearchField()
    }
}


@Preview
@Composable
private fun TopMenuPreview() {
    CinessAppTheme() {
        TopMenu()
    }
}