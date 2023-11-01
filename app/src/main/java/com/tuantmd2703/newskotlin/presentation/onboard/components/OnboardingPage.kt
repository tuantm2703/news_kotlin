package com.tuantmd2703.newskotlin.presentation.onboard.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.tuantmd2703.newskotlin.presentation.Dimens.mediumPadding1
import com.tuantmd2703.newskotlin.presentation.Dimens.mediumPadding2
import com.tuantmd2703.newskotlin.R
import com.tuantmd2703.newskotlin.presentation.onboard.Page
import com.tuantmd2703.newskotlin.presentation.onboard.onBoardPages
import com.tuantmd2703.newskotlin.ui.theme.NewsKotlinTheme

@Composable
fun OnboardPage(
    modifier: Modifier = Modifier,
    page: Page
) {
    Column(modifier = modifier) {
        Image(
            painter = painterResource(id = page.image), contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.6f),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(mediumPadding1))
        Text(
            text = page.title, modifier = Modifier.padding(horizontal = mediumPadding2),
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
            color = colorResource(id = R.color.display_small),
        )
        Text(
            text = page.description, modifier = Modifier.padding(horizontal = mediumPadding2),
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            color = colorResource(id = R.color.text_medium),
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun OnboardPagePreview(){
    NewsKotlinTheme {
        OnboardPage(page = onBoardPages.first())
    }
}