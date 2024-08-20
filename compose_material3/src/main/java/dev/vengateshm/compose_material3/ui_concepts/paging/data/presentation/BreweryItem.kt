package dev.vengateshm.compose_material3.ui_concepts.paging.data.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.vengateshm.compose_material3.ui_concepts.paging.domain.Brewery

@Composable
fun BreweryItem(
    brewery: Brewery,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = brewery.name,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Type: ${brewery.type}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Address: ${
                    listOfNotNull(
                        brewery.street,
                        brewery.address1,
                        brewery.address2,
                        brewery.address3
                    ).joinToString(", ")
                }",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "City: ${brewery.city}, ${brewery.stateProvince ?: brewery.state}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Postal Code: ${brewery.postalCode}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Country: ${brewery.country}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            brewery.phone.let {
                Text(
                    text = "Phone: $it",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
            brewery.websiteUrl.let {
                Text(
                    text = "Website: $it",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun BreweryItemPreview() {
    MaterialTheme {
        BreweryItem(
            brewery = Brewery(
                id = "896f26a1-d80e-4790-9287-026a86c1799d",
                name = "180 and Tapped",
                type = "micro",
                address1 = "2010 A State Ave",
                address2 = "",
                address3 = "",
                city = "Coraopolis",
                stateProvince = "Pennsylvania",
                postalCode = "15108",
                country = "United States",
                longitude = "-80.15020356",
                latitude = "40.50984957",
                phone = "4127375273",
                websiteUrl = "http://www.180andtapped.com",
                state = "Pennsylvania",
                street = "2010 A State Ave"
            )
        )
    }
}