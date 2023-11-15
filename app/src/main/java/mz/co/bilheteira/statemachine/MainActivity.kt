package mz.co.bilheteira.statemachine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import mz.co.bilheteira.statemachine.ui.search.SearchScreen
import mz.co.bilheteira.statemachine.ui.theme.StateMachineTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StateMachineTheme {
                SearchScreen(onLocationSelected = { /* TODO*/ })
            }
        }
    }
}
