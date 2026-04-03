@Composable
fun LoginScreen() {
  var email by remember { mutableStateOf("") }
  var password by remember { mutableStateOf("") }

  Column(modifier = Modifier.padding(16.dp)) {
    TextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
    TextField(value = password, onValueChange = { password = it }, label = { Text("Password") })
    Button(onClick = { /* handle login */ }) {
      Text("Login")
    }
  }
}