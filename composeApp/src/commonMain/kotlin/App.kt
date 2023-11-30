import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.bottomSheet.BottomSheetNavigator
import di.AppModule
import org.jetbrains.compose.resources.ExperimentalResourceApi
import ui.screen.AddItemScreen
import ui.screen.GetItemScreen

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun App(appModule: AppModule) {
    MaterialTheme {
        BottomSheetNavigator {
            Navigator(
                GetItemScreen(
                    appModule
                )
            )
        }
    }
}