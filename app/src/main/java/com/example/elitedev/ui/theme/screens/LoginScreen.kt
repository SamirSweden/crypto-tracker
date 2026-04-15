package com.example.elitedev.ui.theme.screens

import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onSuccess: () -> Unit
) {

    var email by remember { mutableStateOf("") }
    var otpCode by remember { mutableStateOf("") }
    var generatedCode by remember { mutableStateOf("") }
    var isOtpVisible by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var timeLeft by remember { mutableStateOf(0) }
    var canResend by remember { mutableStateOf(true) }

    var context = LocalContext.current
    var scope = rememberCoroutineScope()

    LaunchedEffect(timeLeft) {
        if(timeLeft > 0) {
            delay(1000)
            timeLeft--
        } else if(timeLeft == 0 && isOtpVisible) canResend = true
    }

    // animation
    val otpAlpha by animateFloatAsState(
        targetValue = if(isOtpVisible) 1f else 0f,
        animationSpec = tween(durationMillis = 300)
    )

    fun generateSixDigitCode(): String {
        val random = Random()
        return  (100000 + random.nextInt(900000)).toString()
    }


    suspend fun sendCodeToEmail(email: String , code: String): Boolean {
        delay(2000)

        Toast.makeText(
            context,
            "Your code: $code\nEmail: $email",
            Toast.LENGTH_LONG
        ).show()

        return true
    }

    suspend fun verifyOtpCode(inputCode: String , expectedCode: String): Boolean {
        delay(800)
        return inputCode == expectedCode && timeLeft > 0
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = "Login before invest",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it;
                errorMessage = null

                if(isOtpVisible){
                    isOtpVisible = false
                    otpCode = ""
                    timeLeft = 0
                }
            },
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            isError = errorMessage != null,
            modifier = Modifier.fillMaxWidth()
        )

        if(errorMessage != null && !isOtpVisible) {
            Text(errorMessage!!) // if (error) -> show text
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                scope.launch {
                    if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                        errorMessage = "Email is invalid"
                        return@launch
                    }

                    isLoading = true

                    val newCode = generateSixDigitCode()
                    generatedCode = newCode

                    val success = sendCodeToEmail(email , newCode)

                    if(success){
                        isOtpVisible = true
                        timeLeft = 60
                        canResend = false
                        otpCode = ""
                    } else {
                        errorMessage = "Error sending a code"
                    }
                    isLoading = false
                }
            },
            enabled = email.isNotEmpty() && !isOtpVisible,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(vertical = 16.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            )
        ) {
            Text(
                "receive code",
                fontWeight = FontWeight.Bold
            )

        }


        AnimatedVisibility(visible = isOtpVisible) {
            Column {
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = if (timeLeft > 0) "Time left $timeLeft"
                    else "Code has expired"
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = otpCode,
                    onValueChange = {
                        if(it.length <= 6 && it.all { c -> c.isDigit() }){
                            otpCode = it
                        }
                    },

                    label = {Text("Code")},
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        scope.launch {
                            if(timeLeft == 0) {
                                errorMessage = "code has expired"
                                return@launch
                            }

                            isLoading = true

                            val isValid = verifyOtpCode(otpCode , generatedCode)

                            if(isValid) {
                                isOtpVisible = false
                                timeLeft = 0
                                onSuccess()
                            }else{
                                errorMessage = "invalid code"
                            }
                            isLoading = false
                        }
                    },
                    enabled = otpCode.length == 6 && timeLeft > 0,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .padding(vertical = 32.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    )
                ){
                    Text("Login")
                }

                Spacer(modifier = Modifier.height(8.dp))

                TextButton(
                    onClick = {
                        scope.launch {
                            if(canResend){
                                val newCode = generateSixDigitCode()
                                generatedCode = newCode

                                sendCodeToEmail(email, newCode)

                                timeLeft = 60
                                canResend = false
                                otpCode = ""
                            }
                        }
                    },
                    enabled = canResend && timeLeft == 0
                ) {
                    Text("Resend again")
                }
            }
        }



    }

}





