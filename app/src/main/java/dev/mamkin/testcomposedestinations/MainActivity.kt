package dev.mamkin.testcomposedestinations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.generated.NavGraphs
import com.ramcosta.composedestinations.generated.destinations.GreetingDestination
import com.ramcosta.composedestinations.navigation.dependency
import dev.mamkin.testcomposedestinations.ui.theme.TestComposeDestinationsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestComposeDestinationsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        DestinationsNavHost(
                            navGraph = NavGraphs.root,
                            dependenciesContainerBuilder = {
                                destination(GreetingDestination) {
                                    dependency("test")
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Destination<RootGraph>(
    start = true
)
fun Greeting() {
    Text(
        text = "Hello!",
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TestComposeDestinationsTheme {
        Greeting()
    }
}
