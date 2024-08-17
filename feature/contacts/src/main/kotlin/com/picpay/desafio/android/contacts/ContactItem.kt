package com.picpay.desafio.android.contacts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.ui.theme.colorDetail

@Composable
fun ContactItem(user: User) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .padding(
                    end = 24.dp
                )
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(52.dp)
                    .clip(CircleShape),
                model = user.img,
//            error = painterResource(),
                contentDescription = "user image",
                filterQuality = FilterQuality.Low
            )
        }

        Column {
            Text(
                text = "@" + user.username,
                color = Color.White
            )

            Text(
                text = user.name,
                color = colorDetail
            )
        }


    }
}