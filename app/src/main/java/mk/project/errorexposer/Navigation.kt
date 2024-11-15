package mk.project.errorexposer

import QuestionDetail
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import mk.project.errorexposer.ui.theme.QuestionsScreen

@Composable
fun Navigation(navController: NavHostController) {
    val qVM: QuestionsViewModel = viewModel()
    val qState by qVM.questionState

    NavHost(navController = navController, startDestination = "question_screen") {
        composable("question_screen") {
            QuestionsScreen(viewstate = qState, onQuestionClicked = { questionItem ->
                // Pass the question_id as a navigation argument
                navController.navigate("question_detail_screen/${questionItem.question_id}")
            })
        }

        composable("question_detail_screen/{questionId}", arguments = listOf(navArgument("questionId") { type = NavType.IntType })) { backStackEntry ->
            val questionId = backStackEntry.arguments?.getInt("questionId")
            val questionItem = questionId?.let { qVM.getQuestionById(it) }

            questionItem?.let {
                QuestionDetail(question = it)
            }
        }
    }

}
