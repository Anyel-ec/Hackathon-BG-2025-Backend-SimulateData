package com.guayaquil.hackathon.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

/*
 * Author: Anyel EC
 * Github: https://github.com/Anyel-ec
 * Creation date: 10/01/2025
 */

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Hackathon BG 2025",
                version = "1.0",
                description = """
                        Hackathon BG 2025 es un evento de innovación en el que se generan datos de redes sociales 
                        como Facebook, Google y LinkedIn para desarrollar herramientas financieras basadas en datos.

                        📌 **Descripción de los Controladores**:
                        Los endpoints permiten:
                        ✅ Generar datos ficticios de usuarios en diversas plataformas.
                        ✅ Consultar perfiles con paginación (5 resultados por página).
                        ✅ Buscar usuarios por correo electrónico o ID.

                        📍 **Principales Endpoints**:
                        - `/api/fake-facebook-data` → Genera datos ficticios de Facebook.
                        - `/api/facebook-users` → Consulta y busca usuarios de Facebook.
                        - `/api/user-data` → Obtiene datos de usuarios de Google con paginación.
                        - `/api/professional-profiles` → Maneja perfiles profesionales de LinkedIn.
                        
                        """,
                license = @License(name = "Apache License 2.0", url = "https://github.com/Anyel-ec/SecurityMonitoring/blob/main/LICENSE")
        ),
        servers = @Server(url = "http://localhost:8080")
)
public class OpenApiConfig {
}
