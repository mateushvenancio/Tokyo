package br.com.mateusvenancio.tokyo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import br.com.mateusvenancio.tokyo.ui.presenter.screens.OperationsFormScreen

class OperationsFormActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Surface {

            }
        }
    }
}