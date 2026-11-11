package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.model.ThemePreference
import com.example.ui.PortfolioApp
import com.example.ui.PortfolioLoadState
import com.example.ui.PortfolioViewModel
import com.example.ui.theme.MyPortfolioTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: PortfolioViewModel = viewModel()
            val loadState by viewModel.loadState.collectAsStateWithLifecycle()
            val themePreference = (loadState as? PortfolioLoadState.Success)
                ?.data
                ?.themePreference
                ?: ThemePreference.SYSTEM

            MyPortfolioTheme(themePreference = themePreference) {
                PortfolioApp(
                    loadState = loadState,
                    viewModel = viewModel
                )
            }
        }
    }
}
