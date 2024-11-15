import android.content.Intent
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import mk.project.errorexposer.QuestionItem

@Composable
fun QuestionDetail(
    question: QuestionItem
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        // Title Box with Gradient Background
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFF000000), Color(0xFF737373))
                    )
                )
                .padding(16.dp)
        ) {
            Text(
                text = question.title,
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Tags
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp) // Adds space between the tags
        ) {
            items(question.tags) { tag -> // LazyRow uses `items` to iterate through the list
                Text(
                    text = "#$tag",
                    color = Color(0xFFFF66C4),
                    fontSize = 14.sp,
                    modifier = Modifier
                        .background(Color(0xFF646464), shape = CircleShape)
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
        }


        Spacer(modifier = Modifier.height(16.dp))

        // Button to Open Link
        SeeFullDescriptionButton(questionUrl = question.link)


        Spacer(modifier = Modifier.height(16.dp))

        // Asked By Text
        Text(
            text = "Asked by",
            color = Color(0xFFFF66C4),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        // User Details Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF646464))
                .padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // User Profile Icon and Name
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(1f)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally, // Align children horizontally to the center
                        verticalArrangement = Arrangement.Top // Align children vertically to the top
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(model = question.owner.profile_image),
                            contentDescription = "User profile image",
                            modifier = Modifier
                                .size(60.dp)
                                .clip(CircleShape)
                        )

                        Spacer(modifier = Modifier.height(4.dp)) // Spacer to provide some space between the image and text

                        Text(
                            text = question.owner.display_name,
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.align(Alignment.CenterHorizontally) // Ensures the text is centered
                        )
                    }
                }

                // Vertical Separator
                Box(
                    modifier = Modifier
                        .width(1.dp)
                        .height(64.dp)
                        .background(Color.LightGray)
                )

                // Additional User Details
                Column(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .weight(2f)
                ) {
                    Text(
                        text = "Account ID: ${question.owner.account_id}",
                        color = Color(0xFFFFDE59),
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "User Type: ${question.owner.user_type}",
                        color = Color(0xFFFFDE59),
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Reputation: ${question.owner.reputation}",
                        color = Color(0xFFFFDE59),
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

@Composable
fun SeeFullDescriptionButton(questionUrl: String) {
    val context = LocalContext.current

    Button(
        onClick = {
            // Check if the URL is not blank
            if (questionUrl.isNotBlank()) {
                try {
                    // Create an intent with the URL string directly
                    val intent = Intent(Intent.ACTION_VIEW, android.net.Uri.parse(questionUrl))
                    context.startActivity(intent)
                } catch (e: Exception) {
                    // Handle errors if URL is invalid or an issue occurs
                    Log.e("IntentError", "Invalid URL: $questionUrl", e)
                }
            } else {
                Log.e("IntentError", "Question URL is empty or invalid.")
            }
        },
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(Color(0xFF004AAD), Color(0xFFE385EC))
                )
            )
    ) {
        Text(text = "See Full Description", color = Color.White)
    }
}