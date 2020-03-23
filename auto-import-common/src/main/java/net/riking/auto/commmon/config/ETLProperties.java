package net.riking.auto.commmon.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "net.riking.etl")
@Data
public class ETLProperties {

    private String sourcePath;

    private String backupsPath;

}
