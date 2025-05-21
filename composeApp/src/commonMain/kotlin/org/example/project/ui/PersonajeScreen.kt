package org.example.project.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.example.project.data.Character
import org.example.project.network.ApiClient

@Composable
fun CharacterCard(character: Character) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            KamelImage(
                resource = asyncPainterResource(data = character.image),
                contentDescription = null,
                modifier = Modifier
                    .size(96.dp)
                    .padding(end = 16.dp)
            )
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = character.name,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Status: ${character.status}")
                Text(text = "Species: ${character.species}")
            }
        }
    }
}

@Composable
fun CharacterScreen() {
    var characters by remember { mutableStateOf(emptyList<Character>()) }
    var isLoading by remember { mutableStateOf(true) }
    var searchQuery by remember { mutableStateOf("") }

    // Cargar datos una vez
    LaunchedEffect(Unit) {
        characters = ApiClient.fetchCharacters()
        isLoading = false
    }

    // Filtrar personajes por nombre
    val filteredCharacters = characters.filter {
        it.name.contains(searchQuery, ignoreCase = true)
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Barra de búsqueda
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Buscar personaje") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Mostrar resultados
        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn {
                items(filteredCharacters) { character ->
                    CharacterCard(character)
                }
            }
        }
    }
}
