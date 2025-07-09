# Receita Fácil

Aplicativo Android desenvolvido em Kotlin utilizando Jetpack Compose, Hilt, Ktor, DataStore, MLKit, Coil, entre outras bibliotecas modernas.

## Requisitos

- **Android Studio Giraffe ou superior**
- **JDK 17**
- **Gradle 8+**
- **Dispositivo ou emulador com Android 7.0 (API 24) ou superior**

## Configuração Inicial

1. **Clonar o repositório**
2. **Criar o arquivo `apiKey.properties` na raiz do projeto** com o seguinte conteúdo (exemplo):

   ```
   BASE_URL="https://sua-api.com/"
   BASE_WEBSOCKET_CONNECTION="wss://seu-websocket.com/"
   ```

   Esses valores são obrigatórios para o app funcionar corretamente.

3. **Sincronizar o projeto com o Gradle**  
   O projeto já está configurado para buscar as chaves do arquivo `apiKey.properties` e gerar as constantes necessárias.

## Principais Dependências

- **Jetpack Compose** (BOM 2024.11.00): UI declarativa moderna para Android
- **Hilt** (2.51.1): Injeção de dependências
- **Ktor Client** (3.0.0): Comunicação HTTP/REST e WebSockets
- **Kotlin Coroutines** (1.9.0): Programação assíncrona
- **DataStore** (1.1.2): Persistência de dados
- **Coil** (2.6.0): Carregamento de imagens
- **MLKit** (18.3.1+): Leitura de código de barras e reconhecimento de texto
- **Timber** (5.0.1): Logging
- **SLF4J** (1.7.36): Logging
- **Mockito, JUnit, Turbine, Truth**: Testes unitários e instrumentados

## Plugins Utilizados

- **com.android.application** (8.2.2)
- **org.jetbrains.kotlin.android** (2.0.0)
- **com.google.dagger.hilt.android** (2.51.1)
- **com.google.devtools.ksp** (2.0.0-1.0.21)
- **org.jetbrains.kotlin.plugin.compose** (2.0.0)
- **org.jetbrains.kotlin.plugin.serialization** (2.0.0)

## Observações

- O projeto utiliza o padrão de injeção de dependências com Hilt. Certifique-se de que o arquivo `AndroidManifest.xml` está referenciando corretamente a classe `MyApplication`.
- O arquivo `apiKey.properties` **não deve ser versionado** (adicione ao `.gitignore`).
- Para rodar os testes, utilize as tasks padrão do Gradle (`test`, `connectedAndroidTest`).

## Como rodar

1. Configure o arquivo `apiKey.properties` conforme instruído acima.
2. Abra o projeto no Android Studio.
3. Sincronize o Gradle.
4. Execute o app em um dispositivo ou emulador.

---

Se quiser personalizar mais alguma seção ou adicionar instruções específicas, me avise!

