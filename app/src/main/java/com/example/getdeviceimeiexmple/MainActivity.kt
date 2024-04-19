package com.example.getdeviceimeiexmple

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Context.TELEPHONY_SERVICE
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.telephony.TelephonyManager
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import com.example.getdeviceimeiexmple.ui.theme.GetDeviceIMEIExmpleTheme

class MainActivity : ComponentActivity() {

    private var requestSinglePermission = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { result ->
        if (result) {
            Toast.makeText(this, "Permission granted: $result", Toast.LENGTH_SHORT).show()

        } else {
            Toast.makeText(this, "Permission granted: $result", Toast.LENGTH_SHORT).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestSinglePermission.launch(Manifest.permission.READ_PHONE_STATE)

        setContent {
            GetDeviceIMEIExmpleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DisplayIMEI(LocalContext.current, LocalContext.current as Activity)
                }
            }
        }

    }
}

@SuppressLint("HardwareIds")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DisplayIMEI(context: Context, activity: Activity) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Android ID of Android Device",
            fontSize = 20.sp,
            modifier = Modifier.padding(5.dp),
            fontWeight = FontWeight.Bold
        )

        val andId = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        //todo not work android Q >
        //val telephonyManager = context.getSystemService(TELEPHONY_SERVICE) as TelephonyManager
        //val imei = telephonyManager.imei
        Text(
            text = andId,
            modifier = Modifier.padding(5.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )


    }
}