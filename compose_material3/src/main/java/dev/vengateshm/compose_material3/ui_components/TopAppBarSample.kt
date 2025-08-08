package dev.vengateshm.compose_material3.ui_components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun TopAppBarSample(modifier: Modifier = Modifier) {
  var actionText by remember { mutableStateOf("") }
  var expandMoreMenu by remember { mutableStateOf(false) }
  Scaffold(
    topBar = {
      TopAppBar(
        title = { Text(text = "Title") },
        subtitle = { Text(text = "Sub Title") },
        navigationIcon = {
          IconButton(
            onClick = {
              actionText = "Menu Clicked"
            },
          ) {
            Icon(
              imageVector = Icons.Filled.Menu,
              contentDescription = null,
            )
          }
        },
        actions = {
          IconButton(
            onClick = {
              actionText = "Share action Clicked"
            },
          ) {
            Icon(
              imageVector = Icons.Filled.Share,
              contentDescription = null,
            )
          }
          IconButton(
            onClick = {
              actionText = "Search action Clicked"
            },
          ) {
            Icon(
              imageVector = Icons.Filled.Search,
              contentDescription = null,
            )
          }
          IconButton(
            onClick = {
              expandMoreMenu = true
              actionText = "More action Clicked"
            },
          ) {
            Icon(
              imageVector = Icons.Filled.MoreVert,
              contentDescription = null,
            )
            DropdownMenu(
              expanded = expandMoreMenu,
              onDismissRequest = {
                expandMoreMenu = false
              },
            ) {
              DropdownMenuItem(
                text = { Text(text = "Settings") },
                onClick = {
                  actionText = "Settings action Clicked"
                },
              )
              DropdownMenuItem(
                text = { Text(text = "Logout") },
                onClick = {
                  actionText = "Logout action Clicked"
                },
              )
            }
          }
        },
      )
    },
  ) {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(it),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center,
    ) {
      Text(text = actionText)
    }
  }
}

@Preview
@Composable
private fun TopAppBarSamplePreview() {
  MaterialTheme {
    TopAppBarSample()
  }
}