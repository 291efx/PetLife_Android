package com.erick.petlife

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.erick.petlife.ui.nav.AppNavHost
import com.erick.petlife.ui.theme.PetLifeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { PetLifeTheme { AppNavHost() } }
    }
}
