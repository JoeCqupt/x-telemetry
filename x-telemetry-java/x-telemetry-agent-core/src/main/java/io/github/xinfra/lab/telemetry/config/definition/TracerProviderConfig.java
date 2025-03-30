package io.github.xinfra.lab.telemetry.config.definition;

import lombok.Data;

@Data
public class TracerProviderConfig {

	private TracerProviderType tracerProviderType;

	private ZipkinConfig zipkinConfig;

}
