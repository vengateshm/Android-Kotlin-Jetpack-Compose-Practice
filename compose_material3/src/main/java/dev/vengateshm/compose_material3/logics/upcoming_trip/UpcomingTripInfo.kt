package dev.vengateshm.compose_material3.logics.upcoming_trip

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.vengateshm.appcore.utility.StringWrapper

@Composable
@RequiresApi(Build.VERSION_CODES.O)
fun UpcomingTripInfo(modifier: Modifier = Modifier) {
    val viewModel = viewModel<TripViewModel>(
        factory = TripViewModelFactory(
            FakeWalletReservationRepository(),
            FakeWalletReservationSegmentRepository1(),
        ),
    )
    val tripText = viewModel.upcomingDateText.observeAsState(initial = StringWrapper.EMPTY)
    val context = LocalContext.current
    if (tripText.value != StringWrapper.EMPTY) {
        Text(
            text = tripText.value.getFormattedString(context),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(16.dp),
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun UpcomingTripInfoPreview() {
    UpcomingTripInfo()
}