package mk.project.errorexposer.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import mk.project.errorexposer.Owner
import mk.project.errorexposer.QuestionItem
import mk.project.errorexposer.QuestionsViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionsScreen(
    modifier: Modifier = Modifier,
    viewstate : QuestionsViewModel.QuestionState
){

    Box(modifier = Modifier
        .fillMaxSize()
        .size(20.dp)){
        when{
            viewstate.loading ->{
                CircularProgressIndicator(modifier.align(Alignment.Center))
            }

            viewstate.error != null ->{
                Text("ERROR OCCURRED")
            }
            else ->{
                Scaffold(
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = {
                                Text(
                                    text = "ErrorExposer",
                                    style = MaterialTheme.typography.titleLarge,
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    modifier = Modifier.padding(8.dp),
                                )
                            },
                            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            )
                        )
                    },
                    content = { paddingValues ->
                        Surface(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(paddingValues)
                                .background(MaterialTheme.colorScheme.background)
                        ) {
                            HomeView(questions = viewstate.list)
                        }
                    },
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = { /* Add action here */ },
                            containerColor = MaterialTheme.colorScheme.secondary
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Add Question",
                                tint = MaterialTheme.colorScheme.onSecondary
                            )
                        }
                    }
                )
            }

            }
        }
    }


@Composable
fun HomeView(questions: List<QuestionItem>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(questions) { question ->
            QuestionCard(question)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun QuestionCard(question: QuestionItem) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFF000000), Color(0xFF3533CD))
                ),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp)// Inner padding for content
            .clickable {
                //TODO Add Another Screen in onclick
            }
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = question.owner.profile_image),
                    contentDescription = "User profile image",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = question.title,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "by ${question.owner.display_name}",
                style = MaterialTheme.typography.bodySmall,
                color = Color.LightGray
            )
        }
    }
}


@Composable
fun uiOfResponses(OwnerResponse: Owner, Question: QuestionItem) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()  // Use fillMaxWidth to avoid stretched images
            .clickable { }
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {  // Center vertically
            Image(
                painter = rememberAsyncImagePainter(model = OwnerResponse.profile_image),
                contentDescription = "Question owner profile image",
                modifier = Modifier
                    .size(48.dp)  // Set a specific size for the image
                    .clip(CircleShape)  // Make image circular for better appearance
            )

            Spacer(modifier = Modifier.width(8.dp))  // Add spacing between image and text

            Text(
                text = Question.title,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.weight(1f)  // Fill remaining space
            )
        }
    }
}

