package mk.project.errorexposer.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import mk.project.errorexposer.Owner
import mk.project.errorexposer.QuestionItem
import mk.project.errorexposer.QuestionsViewModel
import mk.project.errorexposer.StackOverflowResponse
@Composable
fun QuestionsScreen(
    modifier: Modifier = Modifier,
    viewstate : QuestionsViewModel.QuestionState
){

    Box(modifier = Modifier.fillMaxSize()){
        when{
            viewstate.loading ->{
                CircularProgressIndicator(modifier.align(Alignment.Center))
            }

            viewstate.error != null ->{
                Text("ERROR OCCURRED")
            }
            else ->{
                HomeView(questions = viewstate.list)
            }
        }
    }
}


@Composable
fun HomeView(questions: List<QuestionItem>) {  // Change to List<QuestionItem>
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(questions) { question ->  // Each question is passed individually
            uiOfResponses(OwnerResponse = question.owner, Question = question)
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
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(1f)  // Fill remaining space
            )
        }
    }
}