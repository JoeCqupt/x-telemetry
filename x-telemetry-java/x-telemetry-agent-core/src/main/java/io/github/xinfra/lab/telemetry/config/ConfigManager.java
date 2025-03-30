package io.github.xinfra.lab.telemetry.config;

import com.fasterxml.jackson.dataformat.javaprop.JavaPropsMapper;
import io.github.xinfra.lab.telemetry.AgentPath;
import io.github.xinfra.lab.telemetry.config.definition.AgentConfig;
import io.github.xinfra.lab.telemetry.logger.AgentLogManager;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Properties;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

public class ConfigManager {

	/**
	 * represent the global configuration of agent
	 */
	public static AgentConfig AGENT_CONFIG;

	public static final String AGENT_CONFIG_KEY_PREFIX = "x.agent.";

	public static final String AGENT_CONFIG_FILE_PATH = "x.agent.config.file";

	public static final String DEFAULT_CONFIG_FILE_NAME = "x-telemetry-agent.config";

	public static final String CONFIG_DIR_NAME = "config";

	public static void loadConfig(String agentArgs)
			throws InvocationTargetException, IllegalAccessException, IOException {
		Properties properties = new Properties();

		// load config file
		loadConfigFile(properties);

		// load system env
		loadSystemEnv(properties);

		// load system properties
		loadSystemProperties(properties);

		JavaPropsMapper mapper = new JavaPropsMapper();
		mapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
		AGENT_CONFIG = mapper.readPropertiesAs(properties, AgentConfig.class);
	}

	private static void loadSystemEnv(Properties properties) {
		Map<String, String> envs = System.getenv();
		envs.forEach((k, v) -> {
			if (k.startsWith(AGENT_CONFIG_KEY_PREFIX)) {
				properties.put(k.substring(AGENT_CONFIG_KEY_PREFIX.length()), v);
			}
		});
	}

	private static void loadSystemProperties(Properties properties) {
		Properties sysProps = System.getProperties();
		sysProps.forEach((ko, v) -> {
			String k = ko.toString();
			if (k.startsWith(AGENT_CONFIG_KEY_PREFIX)) {
				properties.put(k.substring(AGENT_CONFIG_KEY_PREFIX.length()), v);
			}
		});
	}

	private static void loadConfigFile(Properties properties) throws IOException {
		String envConfigFilePath = System.getenv(AGENT_CONFIG_FILE_PATH);
		String propConfigFilePath = System.getProperty(AGENT_CONFIG_FILE_PATH);

		// priority: prop > env
		String configFilePath = StringUtils.isNotBlank(propConfigFilePath) ? propConfigFilePath : envConfigFilePath;

		File configFile;
		if (StringUtils.isNotBlank(configFilePath)) {
			configFile = new File(configFilePath);
		}
		else {
			// default config file
			configFile = AgentPath.getAgentDirPath()
				.resolve(CONFIG_DIR_NAME)
				.resolve(DEFAULT_CONFIG_FILE_NAME)
				.toFile();
		}
		if (!configFile.exists()) {
			return;
		}

		Properties configProps = new Properties();
		try (FileInputStream fis = new FileInputStream(configFile)) {
			configProps.load(fis);
		}
		properties.putAll(configProps);
	}

	public static void refreshConfig() {
		// log config refresh
		AgentLogManager.refreshConfig(AGENT_CONFIG.getAgentLogConfig());
	}

}
