import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import coil.compose.rememberAsyncImagePainter
import com.yatish.presentation.R

@Composable
fun CustomImageView(
    imageUrl: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Image(
        painter = if (imageUrl != "") rememberAsyncImagePainter(model = imageUrl) else painterResource(
            id = R.drawable.person_icon
        ),
        contentDescription = description,
        modifier = modifier
    )
}