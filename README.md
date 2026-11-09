# Sistema de Gestión de Consultas Médicas

Aplicación web universitaria para la administración de consultas médicas mediante tres perfiles: administrador, médico y paciente. El administrador gestiona médicos, pacientes, cuentas y consultas; los médicos consultan su propia agenda; los pacientes consultan sus citas y pueden actualizar únicamente sus horarios.

## Tecnologías

- Java 21.
- Spring Boot 4.1.2.
- Spring MVC.
- Spring Data JPA.
- Spring Security.
- Thymeleaf.
- Jakarta Validation.
- MySQL 8.
- Springdoc OpenAPI 3.2.0.
- Maven Wrapper.

## Requisitos previos

Para ejecutar el proyecto se necesita:

- Java Development Kit (JDK) 21.
- MySQL Server 8.
- MySQL Workbench (opcional, para administrar la base de datos).

No es necesario instalar Maven, ya que el proyecto incluye Maven Wrapper.

## Base de datos

1. Inicie el servicio de MySQL.
2. Abra MySQL Workbench y conéctese al servidor.
3. Abra y ejecute el archivo `database/schema.sql`.
4. Compruebe que se haya creado la base de datos `consultas_medicas_db` con las siguientes tablas:

   - `roles`
   - `usuarios`
   - `medicos`
   - `pacientes`
   - `consultas`

Hibernate utiliza `ddl-auto=validate`, por lo que verifica la estructura existente sin modificarla ni crear tablas automáticamente.

## Configuración de conexión

Desde PowerShell, en la carpeta raíz del proyecto, configure las variables de entorno:

```powershell
$env:DB_URL="jdbc:mysql://localhost:3306/consultas_medicas_db"
$env:DB_USERNAME="root"
$env:DB_PASSWORD="SU_CONTRASEÑA_MYSQL"
```

Reemplace `SU_CONTRASEÑA_MYSQL` por la contraseña local del usuario de MySQL.

El archivo `.env.example` contiene una referencia de configuración. Spring Boot no carga automáticamente los archivos `.env`.

## Ejecución del proyecto

Desde PowerShell, ubicado en la carpeta raíz del proyecto:

**1. Ejecutar las pruebas:**

```powershell
.\mvnw.cmd clean test
```

**2. Iniciar la aplicación:**

```powershell
.\mvnw.cmd spring-boot:run
```

La aplicación estará disponible en:

http://localhost:8080/login

El inicializador registra los roles y las cuentas de demostración sin duplicar los registros existentes.

## Cuentas de demostración

| Perfil | Usuario | Contraseña |
|---|---|---|
| Administrador | `admin` | `Admin123*` |
| Médico | `medico1` | `Medico123*` |
| Paciente | `paciente1` | `Paciente123*` |

Las contraseñas se almacenan mediante BCrypt. Estas cuentas son exclusivamente para demostración académica y deben cambiarse en cualquier despliegue real.

## Funcionalidades por rol

### Administrador
- Consultar el resumen de administración.
- Registrar y gestionar médicos.
- Registrar pacientes y crear sus cuentas.
- Registrar, consultar, editar y eliminar consultas.
- Seleccionar el paciente y el médico asociados a cada consulta.

No se permite eliminar médicos que tengan consultas asociadas.

### Médico
- Consultar su información y especialidad.
- Visualizar exclusivamente las consultas asignadas a su cuenta.

### Paciente
- Consultar exclusivamente sus propias citas.
- Visualizar el médico, motivo, consultorio y horario.
- Actualizar únicamente la hora de inicio y la hora de finalización de sus citas.

## Seguridad

- Los formularios web utilizan autenticación mediante sesión y protección CSRF.
- La API REST utiliza autenticación HTTP Basic y sesiones deshabilitadas.
- Los permisos se controlan según el rol del usuario.
- La aplicación verifica la propiedad de las consultas para impedir el acceso o modificación de registros ajenos.
- Los endpoints protegidos responden con los códigos HTTP correspondientes, como 401 para usuarios no autenticados y 403 para usuarios sin permisos.

## API y documentación Swagger

La documentación de la API está disponible en:

- Swagger UI: http://localhost:8080/swagger-ui.html
- OpenAPI JSON: http://localhost:8080/v3/api-docs

Swagger permite consultar los endpoints, sus parámetros, los modelos de datos y las respuestas HTTP.

Para probar los endpoints protegidos:

1. Abra Swagger UI.
2. Seleccione **Authorize**.
3. Introduzca el usuario y la contraseña de una cuenta de demostración.
4. Ejecute las operaciones permitidas para ese perfil.

### Endpoints principales

| Método | Endpoint | Descripción |
|---|---|---|
| GET | `/api/admin/medicos` | Lista médicos. |
| POST | `/api/admin/medicos` | Registra un médico y su cuenta. |
| GET | `/api/admin/medicos/{id}` | Consulta un médico. |
| PUT | `/api/admin/medicos/{id}` | Actualiza un médico. |
| DELETE | `/api/admin/medicos/{id}` | Elimina un médico sin consultas asociadas. |
| GET | `/api/admin/consultas` | Lista todas las consultas. |
| POST | `/api/admin/consultas` | Registra una consulta. |
| GET | `/api/admin/consultas/{id}` | Consulta una cita. |
| PUT | `/api/admin/consultas/{id}` | Actualiza una consulta. |
| DELETE | `/api/admin/consultas/{id}` | Elimina una consulta. |
| GET | `/api/medico/consultas` | Consulta la agenda del médico autenticado. |
| GET | `/api/paciente/consultas` | Consulta las citas del paciente autenticado. |
| GET | `/api/paciente/consultas/{id}` | Consulta una cita propia. |
| PATCH | `/api/paciente/consultas/{id}/horario` | Actualiza únicamente el horario de una cita propia. |

## Guía de comprobación para el profesor

Se recomienda realizar las siguientes pruebas después de iniciar la aplicación:

1. Iniciar sesión como administrador y registrar un médico, un paciente y una consulta.
2. Iniciar sesión como médico y comprobar que aparece únicamente su agenda.
3. Iniciar sesión como paciente y comprobar que puede visualizar sus propias citas.
4. Actualizar el horario de una cita desde el perfil del paciente.
5. Comprobar que el paciente no puede modificar el médico, el motivo ni el consultorio de la consulta.
6. Acceder a Swagger y probar los endpoints utilizando las credenciales de cada rol.
7. Verificar que las operaciones restringidas no puedan ejecutarse con un usuario que no tenga los permisos correspondientes.

## Verificación del proyecto

Durante el desarrollo se realizaron las siguientes comprobaciones:

- Compilación del proyecto mediante Maven Wrapper.
- Ejecución de pruebas con `.\mvnw.cmd clean test`, obteniendo `BUILD SUCCESS`.
- Conexión con MySQL y validación de la estructura de la base de datos.
- Inicio de sesión y acceso a los paneles de los tres perfiles.
- Registro y consulta de citas.
- Comprobación de las funcionalidades principales y de la documentación Swagger.