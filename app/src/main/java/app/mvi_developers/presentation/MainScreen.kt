package app.mvi_developers.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.mvi_developers.data.Developer


@Composable
private fun MainScreen(viewmodel: DeveloperViewModel, modifier: Modifier) {

    val state by  viewmodel.state.collectAsState()

    Column(modifier = modifier) {
        when {
            state.loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    contentAlignment = androidx.compose.ui.Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            state.error != null -> {
                Text(
                    text = "Error: ${state.error}",
                    color = androidx.compose.ui.graphics.Color.Red
                )
            }

            else -> {
                DeveloperList(developers = state.developers)
            }
        }
    }
}

@Composable
private fun DeveloperList(developers: List<Developer>) {

    Text(
        text = "Developer Details", Modifier.padding(10.dp),
        fontSize = 16.sp, fontWeight = FontWeight.Bold)

    LazyColumn {
        items(items = developers) { developer ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .shadow(4.dp , shape = RoundedCornerShape(4.dp))
            ) {
                Text(text = "ID: ${developer.id}" , modifier = Modifier.padding(4.dp))
                Text(text = "Title: ${developer.title}" , modifier = Modifier.padding(4.dp))
                Text(text = "Year: ${developer.year}" , modifier = Modifier.padding(4.dp))

            }
        }
    }

}



