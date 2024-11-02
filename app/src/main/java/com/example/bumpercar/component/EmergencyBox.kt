package com.example.bumpercar.component

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bumpercar.R
import com.example.bumpercar.ui.theme.emergencyBoxCallIconColor
import com.example.bumpercar.ui.theme.emergencyBoxUnderColor

@Composable
fun EmergencyBox(
    modifier: Modifier = Modifier,
    image: Int,
    phoneNumber: String
) {

    val context = LocalContext.current

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .size(150.dp, 78.dp)
            .background(Color.White)
            .clickable {
                val intent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:$phoneNumber")
                }
                context.startActivity(intent)
            }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(4f)
                    .background(Color.White)
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = image),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1.5f)
                    .background(emergencyBoxUnderColor)
            ) {
                Text(
                    modifier = Modifier
                        .padding(top = 2.dp)
                        .fillMaxWidth(),
                    text = phoneNumber,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.notosans_black)),
                        color = Color.White
                    ),
                    fontSize = 12.sp
                )
                Box(
                    modifier = Modifier
                        .offset(10.dp, -10.dp)
                        .clip(CircleShape)
                        .background(emergencyBoxUnderColor)
                        .size(22.dp), contentAlignment = androidx.compose.ui.Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxSize(),
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_call),
                        contentDescription = "",
                        tint = emergencyBoxCallIconColor
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EmergencyBoxPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
    ) {
        EmergencyBox(image = R.drawable.im_lotte, phoneNumber = "롯데 번호 넣기")
    }
}