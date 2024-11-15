package mk.project.errorexposer
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class StackOverflowResponse(
    val items: List<QuestionItem>
):Parcelable
@Parcelize
data class QuestionItem(
    val tags: List<String>,
    val owner: Owner,
    val is_answered: Boolean,
    val view_count: Int,
    val answer_count: Int,
    val score: Int,
    val last_activity_date: Long,
    val creation_date: Long,
    val question_id: Int,
    val content_license: String,
    val link: String,
    val title: String
):Parcelable

@Parcelize
data class Owner(
    val account_id: Int,
    val reputation: Int,
    val user_id: Int,
    val user_type: String,
    val profile_image: String,
    val display_name: String,
    val link: String
):Parcelable
