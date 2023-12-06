package ru.education.myproject1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserApplication {


    public static void main(String[] args) {

        SpringApplication application = new SpringApplication(UserApplication.class);
        application.setWebApplicationType(WebApplicationType.REACTIVE);
        application.run(args);
    }

    /*@Override
    protected ApplicationContext createApplicationContext() {
        return new AnnotationConfigApplicationContext();
    }

    */
    /*
     * Specify {@link Configuration @Configuration}
     * and/or {@link Component @Component}
     * classes that make up the application configuration. The config classes
     * are given to {@linkplain #createApplicationContext()}.
     */
    /*
    @Override
    protected Class<?>[] getConfigClasses() {
        return new Class[0];
    }*/
}
