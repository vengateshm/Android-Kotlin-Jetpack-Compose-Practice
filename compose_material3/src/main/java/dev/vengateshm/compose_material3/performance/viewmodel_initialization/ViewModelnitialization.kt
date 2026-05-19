package dev.vengateshm.compose_material3.performance.viewmodel_initialization

import android.os.SystemClock
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/*
===========================================================
    HEAVY DEPENDENCY
===========================================================

Simulates expensive object creation.

Imagine:
- database
- retrofit
- analytics sdk
- encryption engine
- ML model
- huge cache
*/

class HeavyRepository(
  private val name: String,
) {

  init {
    println("$name creation started")

    // simulate slow dependency creation
    Thread.sleep(1500)

    println("$name creation completed")
  }

  fun fetchData(): String {
    return "Data from $name"
  }
}

/*
===========================================================
    UI STATE
===========================================================
*/

data class DemoUiState(
  val loading: Boolean = false,
  val logs: List<String> = emptyList(),
  val initializationTime: Long = 0L,
)

/*
===========================================================
    BAD VIEWMODEL
===========================================================

PROBLEM:
- all dependencies created immediately
- constructor blocks main thread
- ViewModel creation becomes slow
- Compose screen freezes

THIS IS WHAT MANY APPS DO WRONG
===========================================================
*/

class BadDashboardViewModel : ViewModel() {

  // BAD: all dependencies created immediately
  private val repo1 = HeavyRepository("BadRepo1")
  private val repo2 = HeavyRepository("BadRepo2")
  private val repo3 = HeavyRepository("BadRepo3")
  private val repo4 = HeavyRepository("BadRepo4")
  private val repo5 = HeavyRepository("BadRepo5")
  private val repo6 = HeavyRepository("BadRepo6")
  private val repo7 = HeavyRepository("BadRepo7")
  private val repo8 = HeavyRepository("BadRepo8")
  private val repo9 = HeavyRepository("BadRepo9")
  private val repo10 = HeavyRepository("BadRepo10")

  private val _uiState = MutableStateFlow(DemoUiState())
  val uiState: StateFlow<DemoUiState> = _uiState.asStateFlow()

  init {

    val start = SystemClock.elapsedRealtime()

    val logs = listOf(
      repo1.fetchData(),
      repo2.fetchData(),
      repo3.fetchData(),
      repo4.fetchData(),
      repo5.fetchData(),
      repo6.fetchData(),
      repo7.fetchData(),
      repo8.fetchData(),
      repo9.fetchData(),
      repo10.fetchData(),
    )

    val end = SystemClock.elapsedRealtime()

    _uiState.update {
      it.copy(
        logs = logs,
        initializationTime = end - start,
      )
    }
  }
}

/*
===========================================================
    GOOD VIEWMODEL
===========================================================

BEST PRACTICES USED:
- lazy initialization
- background thread creation
- parallel creation
- UI responsive
- dependencies created only when needed
===========================================================
*/

class GoodDashboardViewModel : ViewModel() {

  private val _uiState = MutableStateFlow(DemoUiState())
  val uiState: StateFlow<DemoUiState> = _uiState.asStateFlow()

  /*
  =======================================================
      LAZY + ASYNC DEPENDENCIES
  =======================================================

  Nothing is created until accessed.
  Creation happens in Dispatchers.IO.
  */

  private val repo1 by lazy {
    viewModelScope.async(Dispatchers.IO) {
      HeavyRepository("GoodRepo1")
    }
  }

  private val repo2 by lazy {
    viewModelScope.async(Dispatchers.IO) {
      HeavyRepository("GoodRepo2")
    }
  }

  private val repo3 by lazy {
    viewModelScope.async(Dispatchers.IO) {
      HeavyRepository("GoodRepo3")
    }
  }

  private val repo4 by lazy {
    viewModelScope.async(Dispatchers.IO) {
      HeavyRepository("GoodRepo4")
    }
  }

  private val repo5 by lazy {
    viewModelScope.async(Dispatchers.IO) {
      HeavyRepository("GoodRepo5")
    }
  }

  private val repo6 by lazy {
    viewModelScope.async(Dispatchers.IO) {
      HeavyRepository("GoodRepo6")
    }
  }

  private val repo7 by lazy {
    viewModelScope.async(Dispatchers.IO) {
      HeavyRepository("GoodRepo7")
    }
  }

  private val repo8 by lazy {
    viewModelScope.async(Dispatchers.IO) {
      HeavyRepository("GoodRepo8")
    }
  }

  private val repo9 by lazy {
    viewModelScope.async(Dispatchers.IO) {
      HeavyRepository("GoodRepo9")
    }
  }

  private val repo10 by lazy {
    viewModelScope.async(Dispatchers.IO) {
      HeavyRepository("GoodRepo10")
    }
  }

  /*
  =======================================================
      LOAD ONLY WHEN USER REQUESTS
  =======================================================
  */

  fun loadHeavyDependencies() {

    viewModelScope.launch {

      _uiState.update {
        it.copy(
          loading = true,
          logs = listOf(
            "Starting lazy initialization...",
            "UI is still responsive",
          ),
        )
      }

      val start = SystemClock.elapsedRealtime()

      /*
      ===================================================
          PARALLEL CREATION
      ===================================================

      All repositories initialize simultaneously.
      Total time ~1.5 sec instead of 15 sec.
      */

      val repos = awaitAll(
        repo1,
        repo2,
        repo3,
        repo4,
        repo5,
        repo6,
        repo7,
        repo8,
        repo9,
        repo10,
      )

      val logs = repos.map {
        it.fetchData()
      }

      val end = SystemClock.elapsedRealtime()

      _uiState.update {
        it.copy(
          loading = false,
          logs = logs,
          initializationTime = end - start,
        )
      }
    }
  }
}

/*
===========================================================
    MAIN SCREEN
===========================================================

Shows:
1. BAD ViewModel behavior
2. GOOD ViewModel behavior
===========================================================
*/

@Composable
fun MainScreen() {

  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp),
  ) {

    Text(
      text = "ViewModel Lazy Initialization Demo",
      style = MaterialTheme.typography.headlineMedium,
    )

    Spacer(modifier = Modifier.height(24.dp))

    Divider()

    Spacer(modifier = Modifier.height(24.dp))

    /*
    ===================================================
        BAD EXAMPLE
    ===================================================
    */

    Text(
      text = "❌ BAD ViewModel",
      style = MaterialTheme.typography.headlineSmall,
    )

    Spacer(modifier = Modifier.height(8.dp))

    Text(
      text =
        "Creates all heavy dependencies immediately on main thread.",
    )

    Spacer(modifier = Modifier.height(16.dp))

    BadViewModelSection()

    Spacer(modifier = Modifier.height(32.dp))

    Divider()

    Spacer(modifier = Modifier.height(32.dp))

    /*
    ===================================================
        GOOD EXAMPLE
    ===================================================
    */

    Text(
      text = "✅ GOOD ViewModel",
      style = MaterialTheme.typography.headlineSmall,
    )

    Spacer(modifier = Modifier.height(8.dp))

    Text(
      text =
        "Uses lazy async initialization on background thread.",
    )

    Spacer(modifier = Modifier.height(16.dp))

    GoodViewModelSection()
  }
}

/*
===========================================================
    BAD VIEWMODEL COMPOSABLE
===========================================================
*/

@Composable
fun BadViewModelSection(
  vm: BadDashboardViewModel = viewModel(),
) {

  val state by vm.uiState.collectAsState()

  Card(
    modifier = Modifier.fillMaxWidth(),
  ) {

    Column(
      modifier = Modifier.padding(16.dp),
    ) {

      Text(
        text =
          "Screen freezes during ViewModel creation.",
      )

      Spacer(modifier = Modifier.height(12.dp))

      Text(
        text =
          "Initialization Time: ${state.initializationTime} ms",
      )

      Spacer(modifier = Modifier.height(12.dp))

      LazyColumn(
        modifier = Modifier.height(180.dp),
      ) {

        items(state.logs) { item ->

          Text(
            text = item,
            modifier = Modifier.padding(vertical = 4.dp),
          )
        }
      }
    }
  }
}

/*
===========================================================
    GOOD VIEWMODEL COMPOSABLE
===========================================================
*/

@Composable
fun GoodViewModelSection(
  vm: GoodDashboardViewModel = viewModel(),
) {

  val state by vm.uiState.collectAsState()

  Card(
    modifier = Modifier.fillMaxWidth(),
  ) {

    Column(
      modifier = Modifier.padding(16.dp),
    ) {

      Text(
        text =
          "Screen opens instantly. Dependencies are lazy loaded.",
      )

      Spacer(modifier = Modifier.height(16.dp))

      Button(
        onClick = {
          vm.loadHeavyDependencies()
        },
      ) {

        Text("Load Dependencies")
      }

      Spacer(modifier = Modifier.height(16.dp))

      if (state.loading) {

        CircularProgressIndicator()

        Spacer(modifier = Modifier.height(12.dp))

        Text(
          text =
            "Creating dependencies on background threads...",
        )
      }

      if (state.initializationTime > 0) {

        Spacer(modifier = Modifier.height(12.dp))

        Text(
          text =
            "Initialization Time: ${state.initializationTime} ms",
        )
      }

      Spacer(modifier = Modifier.height(12.dp))

      LazyColumn(
        modifier = Modifier.height(220.dp),
      ) {

        items(state.logs) { item ->

          Card(
            modifier = Modifier
              .fillMaxWidth()
              .padding(vertical = 4.dp),
          ) {

            Text(
              text = item,
              modifier = Modifier.padding(12.dp),
            )
          }
        }
      }
    }
  }
}

/*
===========================================================
    ACTIVITY USAGE
===========================================================

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                MainScreen()
            }
        }
    }
}

===========================================================
    EXPECTED RESULT
===========================================================

BAD VIEWMODEL:
- app freezes
- screen delayed
- total ~15 seconds

GOOD VIEWMODEL:
- screen opens instantly
- loading indicator visible
- dependencies load in parallel
- total ~1.5 seconds

===========================================================
    IMPORTANT LEARNINGS
===========================================================

❌ NEVER:
- create heavy dependencies in constructor
- block main thread
- use Thread.sleep() on UI thread

✅ ALWAYS:
- use lazy initialization
- use Dispatchers.IO
- use async parallel loading
- keep ViewModel lightweight
- expose loading state to UI

===========================================================
*/