package edu.javacourse.register.config;

import edu.javacourse.register.dao.PersonDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = {"classpath:/register.properties"}) //по-сути value - массив загружаемых проперти файлов, где эту аннотацию я могу прописать где угодно
public class MarriageConfig
{
    @Bean
    public PersonDao buildPersonDao() {
        System.out.println("PersonDao is created");
        return new PersonDao();
    }
}