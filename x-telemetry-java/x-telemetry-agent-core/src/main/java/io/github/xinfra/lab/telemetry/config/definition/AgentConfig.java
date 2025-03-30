package io.github.xinfra.lab.telemetry.config.definition;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AgentConfig {

	private String serviceName;

	private String group;

	private String version;

	private boolean enable = true;

	private AgentLogConfig agentLogConfig;

	private PluginConfig pluginConfig;

	private MeterProviderConfig meterProviderConfig;

	private TracerProviderConfig tracerProviderConfig;

	private LoggerProviderConfig loggerProviderConfig;

}
