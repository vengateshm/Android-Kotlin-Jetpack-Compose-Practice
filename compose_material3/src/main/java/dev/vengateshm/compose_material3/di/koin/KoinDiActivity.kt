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

    private lateinit var userScope: Scope
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        println("SessionManager: ${sessionManager.hashCode()}")

        userScope = getKoin().createScope("user_scope_id", UserScope)
        user = userScope.get()

        setContent {
            MaterialTheme {
                Surface {
                    CenterAlignedContent {
                        Text(text = "Hello ${user.userName}")
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        onCloseScope()
        userScope.close()
        super.onDestroy()
    }

    override val scope: Scope by activityScope()
}