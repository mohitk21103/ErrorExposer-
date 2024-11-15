package mk.project.errorexposer


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navcontroller = rememberNavController()
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Navigation(navController = navcontroller)
                }
            }
        }
    }
}
