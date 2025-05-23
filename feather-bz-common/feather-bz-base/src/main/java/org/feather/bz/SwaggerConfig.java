package org.feather.bz;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @projectName: feather-bz-server
 * @package: org.feather.bz.config
 * @className: SwaggerConfig
 * @author: feather
 * @description:
 * @since: 2025-04-23 20:57
 * @version: 1.0
 */
@Configuration
public class SwaggerConfig {

        @Bean
        public OpenAPI customOpenAPI() {
            return new OpenAPI()
                    .info(new Info()
                            .title("Feather BZ API")
                            .version("1.0")
                            .description("Feather 项目接口文档"))
                    .schemaRequirement("Sa-Token", new SecurityScheme()
                            .type(SecurityScheme.Type.APIKEY)
                            .name("feather-token-id")
                            .in(SecurityScheme.In.HEADER));
        }


}
