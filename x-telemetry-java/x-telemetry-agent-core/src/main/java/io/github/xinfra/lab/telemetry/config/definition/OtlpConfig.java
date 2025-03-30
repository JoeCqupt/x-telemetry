package io.github.xinfra.lab.telemetry.config.definition;

import lombok.Data;

@Data
public class OtlpConfig {

	private String traceEndpoint;

	private String metricEndpoint;

	private String logEndpoint;

}
