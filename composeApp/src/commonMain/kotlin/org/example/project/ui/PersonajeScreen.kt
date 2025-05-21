package org.example.project.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.content.MediaType.Companion.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.project.data.Character
import org.example.project.network.ApiClient
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.CardDefaults


@Composable
fun CharacterCard(character: Character) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = character.name, style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))



            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Status: ${character.status}")
            Text(text = "Species: ${character.species}")
        }
    }
}
@Composable
fun CharacterList(characters: List<Character>) {
    LazyColumn {
        items(characters) { character ->
            CharacterCard(character)
        }
    }
}

@Composable
fun CharacterScreen() {
    var characters by remember { mutableStateOf(emptyList<Character>()) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        characters = ApiClient.fetchCharacters()
        isLoading = false
    }

    if (isLoading) {
        CircularProgressIndicator()
    } else {
        CharacterList(characters)
    }
}