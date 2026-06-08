package com.example.cinessapp.ui.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cinessapp.ui.theme.CinessAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchField(modifier: Modifier = Modifier) {
    var query by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    SearchBar(
        inputField = {
            SearchBarDefaults.InputField(
                query = query,
                onQueryChange = { query = it },
                onSearch = { expanded = false },
                expanded = expanded,
                onExpandedChange = { expanded = it },
//                enabled = TODO(),
                placeholder = {
                    Text(
                        "Search movies, TV shows..",
                        color = MaterialTheme.colorScheme.primary
                    )
                },
//                leadingIcon = TODO(),
//                trailingIcon = TODO(),
//                colors = colors1.inputFieldColors,
//                interactionSource = TODO(),
            )
        },
        expanded = expanded,
        onExpandedChange = { expanded = it },
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        shape = RoundedCornerShape(12.dp),
        colors = SearchBarDefaults.colors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        tonalElevation = 0.dp,
        shadowElevation = 0.dp,
//        windowInsets = TODO(),
        content = { },
    )
}

@Preview
@Composable
private fun SearchFieldPreview() {
    CinessAppTheme() {
        SearchField()
    }
}