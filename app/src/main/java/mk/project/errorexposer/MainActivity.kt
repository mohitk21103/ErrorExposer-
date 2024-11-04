package mk.project.errorexposer


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import mk.project.errorexposer.ui.theme.HomeView
import mk.project.errorexposer.ui.theme.QuestionsScreen


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val qVM : QuestionsViewModel = viewModel()
                    val qState by qVM.questionState
                    QuestionsScreen(viewstate = qState)
                }
            }
        }
    }
}
