package com.peoplegroup.assignmentapp.utilities

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.peoplegroup.assignmentapp.R

@Composable
fun PersonCardView(person: Person) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            )
            .shadow(elevation = 8.dp, shape = CardDefaults.shape)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(120.dp)
                    .padding(vertical = 16.dp),
                model = person.picture?.large,
                contentDescription = ""
            )

            person.name?.let {
                Text(
                    text = nameBuilder(it),
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W500,
                        color = MaterialTheme.colorScheme.primary
                    )
                )
            }

            person.location?.let {
                Text(
                    modifier = Modifier.padding(
                        top = 16.dp,
                        start = 40.dp,
                        end = 40.dp
                    ),
                    text = addressBuilder(it),
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W400,
                        textAlign = TextAlign.Center
                    )
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ActionButton(painterResource = R.drawable.ic_decline) {

                }

                ActionButton(painterResource = R.drawable.ic_accept) {

                }
            }
        }
    }
}

@Composable
fun ActionButton(
    painterResource: Int,
    onClick: () -> Unit
) {
    IconButton(
        modifier = Modifier.border(
            width = 2.dp,
            color = MaterialTheme.colorScheme.primary,
            shape = CircleShape
        ),
        onClick = onClick,
        content = {
            Icon(
                painter = painterResource(id = painterResource),
                contentDescription = ""
            )
        }
    )
}