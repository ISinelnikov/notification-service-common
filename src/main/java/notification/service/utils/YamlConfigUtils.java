package notification.service.utils;

import java.nio.file.Paths;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.YamlMapFactoryBean;
import org.springframework.beans.factory.config.YamlProcessor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

public final class YamlConfigUtils {
    private YamlConfigUtils() {
    }

    private static final Logger logger = LoggerFactory.getLogger(YamlConfigUtils.class);

    public static <T> T loadYamlConfig(String configPath, Class<T> type) {
        Objects.requireNonNull(configPath, "Config path can't be null.");
        YamlMapFactoryBean factoryBean = new YamlMapFactoryBean();
        factoryBean.setResolutionMethod(YamlProcessor.ResolutionMethod.OVERRIDE_AND_IGNORE);

        String specialConfigPath = Paths.get(configPath).toAbsolutePath().toString();
        Resource specialConfigFile = new FileSystemResource(specialConfigPath);
        logger.info("Found external config file path {}, resource exist {}", specialConfigPath, specialConfigFile.exists());
        factoryBean.setResources(specialConfigFile);

        T configObject = JsonUtils.convertObjectWithType(factoryBean.getObject(), type);
        logger.info("Success loaded application properties {}", JsonUtils.convertObjectToJson(configObject));
        return configObject;
    }
}

