package br.com.leomaia.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

//@Configuration
class WebServerConfiguration {

    fun addCorsConfig(): WebMvcConfigurer{
        return object : WebMvcConfigurer{

        }
    }
}