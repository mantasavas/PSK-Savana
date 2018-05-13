package lt.vu.service.importExportImpl;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@ComponentScan("lt.vu")
@EnableAsync
public class AsynConfiguration {
}
