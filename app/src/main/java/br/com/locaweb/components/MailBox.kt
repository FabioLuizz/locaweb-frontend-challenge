package br.com.locaweb.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.locaweb.R

@Composable
fun MailBox(
    label: String,
    placeholder: String,
    subTitle: String,
    onRowClick: () -> Unit,
    @DrawableRes imageRes: Int,
    isUnread: Boolean
) {

    val backgroundColor = if (isUnread) {
        Color(android.graphics.Color.parseColor("#bbbbbb"))
    } else {
        Color(android.graphics.Color.parseColor("#e5e5e5"))
    }

    Row (modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)
        .background(backgroundColor)
        .padding(end = 10.dp)
        .clickable(onClick = onRowClick)
    ) {

        Box(modifier = Modifier
            .padding(start = 10.dp, end = 10.dp, top = 10.dp)
            .size(80.dp)
            .clip(RoundedCornerShape(4.dp))
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.width(5.dp))

        Column {

            Text(
                text = label,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(end = 5.dp, top = 10.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = subTitle,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = placeholder,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 16.sp
            )

        }


    }

    Spacer(modifier = Modifier.height(6.dp))

}