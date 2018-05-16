import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@ComponentScan("lt.vu")
@PropertySource("classpath:config.properties")
@EnableAsync
public class AsynConfiguration {
}
