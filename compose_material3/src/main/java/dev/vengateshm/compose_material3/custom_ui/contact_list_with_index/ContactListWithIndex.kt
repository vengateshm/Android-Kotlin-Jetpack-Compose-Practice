package dev.vengateshm.compose_material3.custom_ui.contact_list_with_index

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

enum class ScrollDirection {
    NONE,
    NEXT,
    PREVIOUS
}
@Composable
fun ContactListWithIndex(modifier: Modifier = Modifier) {
    val contacts = dummyContactList.filterNot { it.firstName.startsWith("L", ignoreCase = true) }.sortedBy { it.firstName }
    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = lazyListState
        ) {
            items(contacts) { contact ->
                ContactListItem(contact = contact)
            }
        }
        AlphabetIndex(modifier = Modifier.align(Alignment.CenterEnd), onCharacterClick = { char ->
            var index = contacts.indexOfFirst { it.firstName.startsWith(char, ignoreCase = true) }
            var scrollDirection = ScrollDirection.NONE

            if (index == -1) {
                index = findNearestIndex(contacts, char, ScrollDirection.NEXT)
                if (index != -1) {
                    scrollDirection = ScrollDirection.NEXT
                } else {
                    index = findNearestIndex(contacts, char, ScrollDirection.PREVIOUS)
                    if (index != -1) {
                        scrollDirection = ScrollDirection.PREVIOUS
                    }
                }
            }

            if (index != -1) {
                coroutineScope.launch {
                    lazyListState.scrollToItem(index)
                }
            }
        })
    }
}

/**
 * Finds the index of the nearest contact in the list based on the starting character and scroll direction.
 *
 * The function works as follows:
 * 1. It defines the alphabet as a list of characters from 'A' to 'Z'.
 * 2. It finds the index of the uppercase version of the `startChar` within the alphabet.
 *    - If the `startChar` is not found in the alphabet (e.g., it's a digit or symbol),
 *      it returns -1, indicating no match can be found.
 * 3. It determines the range of alphabet indices to search based on the `direction`:
 *    - If `direction` is `ScrollDirection.NEXT`, it will search from the character immediately
 *      after `startChar` to the end of the alphabet ('Z').
 *    - If `direction` is `ScrollDirection.PREVIOUS`, it will search from the character immediately
 *      before `startChar` down to the beginning of the alphabet ('A').
 * 4. It iterates through the determined range of alphabet characters:
 *    - For each character in the range, it tries to find the first contact in the `contacts` list
 *      whose `firstName` starts with that character (case-insensitive).
 *    - If a contact is found, its index in the `contacts` list is immediately returned.
 * 5. If the loop completes without finding any matching contact, it means no contact starts with
 *    any of the subsequent (or preceding) letters in the specified direction. In this case, it returns -1.
 */
private fun findNearestIndex(contacts: List<ContactItem>, startChar: Char, direction: ScrollDirection): Int {
    val alphabet = ('A'..'Z').toList()
    val startIndex = alphabet.indexOf(startChar.uppercaseChar())

    if (startIndex == -1) return -1

    val range = if (direction == ScrollDirection.NEXT) {
        (startIndex + 1 until alphabet.size)
    } else {
        (startIndex - 1 downTo 0)
    }

    for (i in range) {
        val charToFind = alphabet[i]
        val index = contacts.indexOfFirst { it.firstName.startsWith(charToFind, ignoreCase = true) }
        if (index != -1) {
            return index
        }
    }
    return -1
}

data class ContactItem(
    val id: Int,
    val firstName: String,
    val fullName: String,
    val phone: String,
    val email: String,
)

val dummyContactList = ('A'..'Z').flatMap { char ->
    (1..3).map { index ->
        val firstName = "$char${"c".repeat(index + 2)}" // e.g. Accc, Acccc, Accccc
        val lastNameInitial = ('A'..'Z').random()
        ContactItem(
            id = (char - 'A') * 3 + index,
            firstName = firstName,
            fullName = "$firstName $lastNameInitial.",
            phone = "(${String.format("%03d", (0..999).random())}) ${
                String.format(
                    "%03d",
                    (0..999).random(),
                )
            }-${String.format("%04d", (0..9999).random())}",
            email = "${firstName.lowercase()}@example.com",
        )
    }
}

@Composable
fun ContactListItem(modifier: Modifier = Modifier, contact: ContactItem) {
    Column(modifier = modifier.padding(16.dp)) {
        Text(text = contact.fullName)
        Text(text = contact.phone)
        Text(text = contact.email)
    }
}

@Composable
fun AlphabetIndex(
    modifier: Modifier = Modifier,
    onCharacterClick: (Char) -> Unit
) {
    Column(
        modifier = modifier.padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ('A'..'Z').forEach { char ->
            Text(text = char.toString(), modifier = Modifier.clickable { onCharacterClick(char) }.padding(vertical = 2.dp))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ContactListWithIndexPreview() {
    ContactListWithIndex()
}


