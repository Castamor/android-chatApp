# Aplicación de mensajería en tiempo real
Aplicación desarrollada para sistemas operativos android que permite realizar conversaciones en tiempo real, visualmente es parecida a WhatsApp pero ya en temas de funcionalidad es más parecida a un servidor de Discord. Creada con el lenguaje de programación Kotlin y el editor Android Studio de JetBrains.

## Funcionamiento
Este proyecto utiliza la tecnología de [Firebase](https://firebase.google.com/?hl=es-419) proporcionada por Google, en especifico se hace uso de los servicios de [Autenticación](https://firebase.google.com/products/auth?hl=es-419) y de [Base de Datos](https://firebase.google.com/products/realtime-database?hl=es-419).

### Inicio de sesión
La primer pantalla que observas al iniciar la aplicación es la de **Inicio de Sesión**, contiene validación tanto por parte de código al no dejar ingresar datos incorrectos o vacíos como por parte de los servicios de Autenticación al no dejar ingresar a usuarios que previamente no se hayan registrado.

![Inicio sesión](https://github.com/Castamor/android-chatApp/assets/141193208/acaf4782-96e8-4ffd-83fa-429c72d923b1)

### Registro de usuarios
Está actividad es la que interactua directamente con los servicios de Autenticación y de Base de datos. Al momento de registrarse de nuevo se validan los campos para evitar campos vacíos, además si surge algún otro problema por parte de la base de datos se muestra un Toast informando de dicho error para así guiar al usuario. Al registrarse tu nombre se añadirá automaticamente a la lista de contactos disponibles y cualquier otra persona en la aplicación podrá enviarte mensaje.

![Registro](https://github.com/Castamor/android-chatApp/assets/141193208/7fc5d1b1-44fe-4cff-8a10-b5795746a875)

### Pantalla principal
Aquí se muestra la información más relevante:
- Historial de chats
- Contactos a los cuales se les puede enviar mensaje
- Botón para cerrar sesión

Al darle click a cualquier chat o contacto te enviará a su respectivo chat para poder iniciar o continuar una conversación.

![Pantalla principal](https://github.com/Castamor/android-chatApp/assets/141193208/b9e37b07-3ca9-43bb-969e-fef8e6601f72)

Además, en dónde están enlistados los contactos también aparece el nombre del propio usuario que está utilizando la aplicación pero a diferencia de otro contacto ajeno al darle click este te llevará a otra actividad que te permite actualizar el propio nombre del usuario, al actualizarlo se cambiará también para todas las personas.

![Actualizar nombre](https://github.com/Castamor/android-chatApp/assets/141193208/85f101a3-0e4e-408a-8422-0610c31459c0)
![Actualizar nombre - actividad](https://github.com/Castamor/android-chatApp/assets/141193208/43a78729-46eb-4943-9199-40159ed6052a)

### Chat privados
La pantalla de los chats es bastante similar a una aplicación como WhatsApp y el funcionamiento del mismo es básicamente que al momento de enviar un mensaje se activa un evento en la base de datos que de inmediato actualiza la información, permitiendo así la conversación en tiempo real.

![Chat Angel](https://github.com/Castamor/android-chatApp/assets/141193208/bb868e20-eca5-4b54-b2d1-bbda375c4f03)
![Chat Silvia](https://github.com/Castamor/android-chatApp/assets/141193208/fefc99b7-9765-4000-9f1b-f518eed4ded2)

###### README.md - Version 1 (corrección de palabras)
