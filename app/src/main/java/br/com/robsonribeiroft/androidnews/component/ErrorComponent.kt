package br.com.robsonribeiroft.androidnews.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import br.com.robsonribeiroft.androidnews.R
import br.com.robsonribeiroft.androidnews.extensions.empty

@Composable
fun ErrorComponent(
    @StringRes errorMessageId: Int,
    modifier: Modifier = Modifier,
    icon: ImageVector = Icons.Default.Error,
){
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentSize()
            .padding(dimensionResource(R.dimen.large)),
        verticalArrangement = Arrangement.spacedBy(
            space = dimensionResource(R.dimen.regular),
            alignment = Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(dimensionResource(R.dimen.very_large)),
            imageVector = icon,
            contentDescription = String.empty,
        )

        Text(
            text = stringResource(errorMessageId),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
    }
}