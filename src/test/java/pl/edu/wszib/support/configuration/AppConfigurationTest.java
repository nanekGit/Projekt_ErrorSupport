package pl.edu.wszib.support.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "pl.edu.wszib.support.services.impl",
        "pl.edu.wszib.support.session"
})
public class AppConfigurationTest {

}