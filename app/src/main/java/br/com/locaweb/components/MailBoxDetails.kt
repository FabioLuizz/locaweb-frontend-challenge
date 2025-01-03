package br.com.locaweb.components

import android.util.Log
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.ReportGmailerrorred
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.locaweb.R
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import br.com.locaweb.api.RetrofitClient
import br.com.locaweb.model.EmailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun MailBoxDetails(
    emailId: Long,
    name: String,
    email: String,
    date: String,
    @DrawableRes imageRes: Int,
    onDeleteClick: () -> Unit,
    onSpamClick: () -> Unit
) {

    val context = LocalContext.current


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(Color(android.graphics.Color.parseColor("#dfdfdf")))
    ) {

        Box(
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp, top = 10.dp)
                .size(80.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(Color(android.graphics.Color.parseColor("#ff3a3a")))
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
                text = name,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(end = 5.dp, top = 10.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = email,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = date,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 16.sp
            )

        }

        Spacer(modifier = Modifier.width(80.dp))

        Icon(
            imageVector = Icons.Outlined.Delete,
            contentDescription = "Delete",
            modifier = Modifier
                .size(40.dp)
                .padding(top = 10.dp)
                .clickable { onDeleteClick() }
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.width(10.dp))

        Icon(
            imageVector = Icons.Outlined.ReportGmailerrorred,
            contentDescription = "Spam",
            modifier = Modifier
                .size(40.dp)
                .padding(top = 10.dp)
                .clickable { onSpamClick() }
        )

    }
}