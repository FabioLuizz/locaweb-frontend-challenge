package br.com.locaweb.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

@Composable
fun CompanyBox(
    label: String,
    onRowClick: () -> Unit,
    @DrawableRes imageRes: Int
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(Color(android.graphics.Color.parseColor("#dfdfdf")))
            .clickable(onClick = onRowClick)
            .padding(end = 10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 10.dp, end = 10.dp, top = 10.dp)
        ) {
            Box(
                modifier = Modifier
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
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(end = 5.dp, top = 30.dp, start = 5.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }

    Spacer(modifier = Modifier.height(6.dp))
}
