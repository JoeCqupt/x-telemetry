package io.github.xinfra.lab.telemetry.config.definition;

import lombok.Data;

@Data
public class LoggerProviderConfig {

	private LoggerProviderType loggerProviderType;

	private OtlpConfig otlpConfig;

}
