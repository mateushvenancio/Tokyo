package br.com.mateusvenancio.tokyo

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import br.com.mateusvenancio.tokyo.services.OperationsService
import br.com.mateusvenancio.tokyo.ui.presenter.screens.HomeScreen

class HomeActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val operationService = OperationsService()

        setContent {
            Surface {

            }
        }
    }

    private fun goToOperationForm() {
        val intent = Intent(this, OperationsFormActivity::class.java)
        startActivity(intent)
    }
}


//@Composable
//@Preview
//fun Preview() {
//    HomeScreen()
//}