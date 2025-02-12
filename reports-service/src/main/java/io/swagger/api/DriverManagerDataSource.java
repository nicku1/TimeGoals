package io.swagger.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import javax.sql.DataSource;
//DO WYWALENIA
public class DriverManagerDataSource{
    @Autowired
    private static Environment env;

    @Bean(name ="MariaDB")
    @Primary
    public static DataSource MariaDBDataSource()
    {

        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.jdbc.mariadb.MariaDBDriver");
        dataSourceBuilder.url("jdbc:mariadb://192.168.221.133:3306/TimeGoals?user=kochammichalka&password=JARANIE420");
        return dataSourceBuilder.build();
    }

    //@Override
    public void setEnvironment( final Environment environment) {
        env=environment;
    }
}