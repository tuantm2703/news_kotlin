package com.tuantmd2703.newskotlin.presentation.onboard

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.tuantmd2703.newskotlin.presentation.Dimens.mediumPadding2
import com.tuantmd2703.newskotlin.presentation.Dimens.pageIndicatorWidth
import com.tuantmd2703.newskotlin.presentation.common.NewsButton
import com.tuantmd2703.newskotlin.presentation.common.NewsTextButton
import com.tuantmd2703.newskotlin.presentation.onboard.components.OnboardPage
import com.tuantmd2703.newskotlin.presentation.onboard.components.OnboardPagerIndicator
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        val pagerState = rememberPagerState(initialPage = 0)
        val buttonState = remember {
            derivedStateOf {
                when (pagerState.currentPage) {
                    0 -> listOf("", "Next")
                    1 -> listOf("Back", "Next")
                    2 -> listOf("Back", "Get Started")
                    else -> listOf("", "")
                }
            }
        }
        HorizontalPager(pageCount = onBoardPages.size, state = pagerState) { index ->
            OnboardPage(page = onBoardPages[index])
        }
        Spacer(modifier = Modifier.weight(1f))
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = mediumPadding2)
                .navigationBarsPadding(),
            verticalAlignment = Alignment.CenterVertically ,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            OnboardPagerIndicator(
                modifier = Modifier.width(pageIndicatorWidth),
                pagesSize = onBoardPages.size,
                selectedPage = pagerState.currentPage
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                val scope = rememberCoroutineScope()
                if (buttonState.value.first().isNotEmpty()) {
                    NewsTextButton(
                        text = buttonState.value.first(),
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(
                                    page = pagerState.currentPage - 1
                                )
                            }

                        }
                    )
                }
                NewsButton(
                    text = buttonState.value.last(),
                    onClick = {
                        scope.launch {
                            if (pagerState.currentPage == 3) {
                                //Navigate to the main screen and save a value in datastore preferences
                            } else {
                                pagerState.animateScrollToPage(
                                    page = pagerState.currentPage + 1
                                )
                            }
                        }
                    }
                )
            }
        }
        Spacer(modifier = Modifier.weight(0.5f))
    }
}