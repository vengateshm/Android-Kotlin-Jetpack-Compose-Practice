package dev.vengateshm.compose_material3.third_party_libraries.compose_stability_analyzer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class User(
  val id: Int,
  val name: String,
)

@Immutable
data class StableUser(
  val id: Int,
  val name: String,
  var age: Int = 0,
)

@Composable
fun CounterScreen() {

  var count by remember { mutableStateOf(0) }

  // Normal Kotlin List (unstable)
  val normalUsers = remember {
    listOf(
      User(1, "John"),
      User(2, "Alice"),
    )
  }

  // ImmutableList (stable)
  val immutableUsers: ImmutableList<StableUser> = remember {
    persistentListOf(
      StableUser(1, "John"),
      StableUser(2, "Alice"),
    )
  }

  Column {

    Text("Counter: $count")

    Button(
      onClick = { count++ },
    ) {
      Text("Increment")
    }

    Spacer(modifier = Modifier.height(24.dp))

    UnstableUsersComposable(normalUsers)

    Spacer(modifier = Modifier.height(24.dp))

    StableUsersComposable(immutableUsers)
  }
}

@Composable
fun UnstableUsersComposable(
  users: List<User>,
) {

  println("❌ UnstableUsersComposable Recomposed")

  Column {
    Text("Unstable List")
    users.forEach {
      Text(it.name)
    }
  }
}

@Composable
fun StableUsersComposable(
  users: ImmutableList<StableUser>,
) {

  println("✅ StableUsersComposable Recomposed")

  Column {
    Text("Stable ImmutableList")
    users.forEach {
      Text(it.name)
    }
  }
}