package mk.project.errorexposer

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class QuestionsViewModel : ViewModel() {
    private val _questionState = mutableStateOf(QuestionState())
    val questionState: State<QuestionState> = _questionState

    init {
        fetchQuestions()
    }

    // Fetch the full QuestionItem based on questionId
    fun getQuestionById(questionId: Int): QuestionItem? {
        return _questionState.value.list.find { it.question_id == questionId }
    }

    private fun fetchQuestions() {
        viewModelScope.launch {
            try {
                val response = soResponses.getSOResponse()
                _questionState.value = _questionState.value.copy(
                    list = response.items,
                    loading = false,
                    error = null
                )
            } catch (e: Exception) {
                _questionState.value = _questionState.value.copy(
                    loading = false,
                    error = "Error fetching Categories ${e.message}"
                )
            }
        }
    }

    // State class to hold question data
    data class QuestionState(
        val loading: Boolean = true,
        val list: List<QuestionItem> = emptyList(),
        val error: String? = null
    )
}
