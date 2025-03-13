package com.igrejasobrenatural.sistemas;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SistemaApplication {

//    @Bean
//    public CommandLineRunner run(@Autowired UsuarioRepository repository) {
//        return args -> {
//            Usuario usuario = Usuario.builder().username("admin").password("admin").build();
//            repository.save(usuario);
//        };
//    }
    public static void main(String[] args) {
        SpringApplication.run(SistemaApplication.class, args);
    }
}