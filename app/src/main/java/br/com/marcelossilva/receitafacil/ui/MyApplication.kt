package br.com.marcelossilva.receitafacil.ui

import android.app.Application
import br.com.marcelossilva.receitafacil.BuildConfig
import br.com.marcelossilva.receitafacil.core.util.logging.DebugTree
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

//Hilt é uma biblioteca de injeção de dependências para Android que facilita a
// criação e gerenciamento de dependências em aplicativos Android. Ele é baseado
// no Dagger, mas oferece uma API mais simples e fácil de usar, além de integração
// com o ciclo de vida do Android.

// O @AndroidEntryPoint é uma anotação do Hilt usada para informar ao framework
// que aquela classe (Activity, Fragment, Application, Service, etc.) pode receber
// dependências injetadas pelo Hilt. Sem essa anotação, o Hilt não consegue gerar
// o código necessário para fornecer as dependências automaticamente para a classe.
@HiltAndroidApp
class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            //Timber é uma biblioteca de logging que facilita o log de mensagens
            Timber.plant(DebugTree())
        }
    }
}