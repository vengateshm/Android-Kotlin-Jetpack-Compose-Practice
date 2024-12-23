package dev.vengateshm.compose_material3.di.koin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import dev.vengateshm.compose_material3.ui_components.CenterAlignedContent
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.inject
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.activityScope
import org.koin.core.scope.Scope

class KoinDiActivity : ComponentActivity(), AndroidScopeComponent {

    private val sessionManager: SessionManager by inject()
    private val configManager: ConfigManager by inject()

    private lateinit var userScope: Scope
    private lateinit var humanScope: Scope
    private lateinit var user: User
    private lateinit var human: Human

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        println("SessionManager: ${sessionManager.hashCode()}")
        println("ConfigManager: ${configManager.hashCode()}")

        userScope = getKoin().createScope("user_scope_id", UserScope)
        user = userScope.get()
        humanScope = getKoin().createScope<HumanScope>()
        human = humanScope.get<Human>()

        setContent {
            MaterialTheme {
                Surface {
                    CenterAlignedContent {
                        Text(text = "Hello ${user.userName}, ${human.name}")
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        onCloseScope()
        userScope.close()
        humanScope.close()
        super.onDestroy()
    }

    override val scope: Scope by activityScope()
}