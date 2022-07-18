/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jxf.config;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 * @author achmad.ha
 */
@Configuration
//@PropertySource("file:///D:/jdbc.properties")
@PropertySource({"classpath:database.properties","file:${jxconfig}"})
public class AppConfig {
    
    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;
    
    @Value("${jdbc.driver}")
    private String driver;
    
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        try {
//            String realPass = CryptoSecurity.decrypt(password);
            dataSource.setPassword(password);
        } catch (Exception ex) {
            Logger.getLogger(AppConfig.class.getName()).log(Level.SEVERE, null, ex);
        }             
        return dataSource;
    }
    
    @Value("${jdbc.url.hr}")
    private String urlHr;

    @Value("${jdbc.username.hr}")
    private String usernameHr;

    @Value("${jdbc.password.hr}")
    private String passwordHr;

    @Value("${jdbc.driver.hr}")
    private String driverHr;

    @Bean
    public DataSource dataSourceHr() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverHr);
        dataSource.setUrl(urlHr);
        dataSource.setUsername(usernameHr);
        try {
            dataSource.setPassword(passwordHr);
        } catch (Exception ex) {
            Logger.getLogger(AppConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dataSource;
    }
        
}
