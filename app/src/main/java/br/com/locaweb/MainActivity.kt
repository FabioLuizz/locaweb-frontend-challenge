package br.com.locaweb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.locaweb.components.FilterScreen
import br.com.locaweb.screens.*
import br.com.locaweb.ui.theme.LocawebTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LocawebTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val selectedDate = remember { mutableStateOf<String?>(null) }

                    NavHost(navController = navController, startDestination = "SigninScreen") {

                        // Tela principal
                        composable(route = "MainScreen") { MainScreen(navController) }


                        // Detalhes do email com ID passado como argumento
                        composable(
                            route = "EmailScreen/{emailId}",
                            arguments = listOf(navArgument("emailId") {
                                type = NavType.LongType
                            })
                        ) { backStackEntry ->
                            val emailId = backStackEntry.arguments?.getLong("emailId")
                            if (emailId != null) {
                                EmailScreenLinkedinExample(navController, emailId)
                            }
                        }


                        // Caixa de entrada e mensagens
                        composable(route = "MessageScreen") { MessageScreen(navController) }

                        // Outros componentes como empresas, calendário, filtro e recursos
                        composable(route = "CompanyMailBoxes") {
                            CompanyMailBoxesScreen(
                                navController
                            )
                        }
                        composable(route = "calendarScreen") {
                            CalendarViewComponent(
                                navController = navController,
                                selectedDate = selectedDate.value,
                                onDateSelected = { date ->
                                    selectedDate.value = date
                                }
                            )
                        }
                        composable(route = "FilterScreen") { FilterScreen(navController) }
                        composable(route = "ResourceScreen") { ResourceScreen(navController) }

                        // Telas de autenticação e perfil
                        composable(route = "SigninScreen") { SigninScreen(navController) }
                        composable(route = "RegisterScreen") { RegisterScreen(navController) }
                        composable(route = "ProfileScreen") { ProfileScreen(navController) }

                        // Emails enviados
                        composable(route = "SentScreen") { SentEmails(navController) }
                        composable(route = "SpamScreen") { SpamScreen(navController) }
                    }
                }
            }
        }
    }
}