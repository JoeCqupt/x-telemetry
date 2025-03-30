package io.github.xinfra.lab.telemetry.config.definition;

import lombok.Data;

@Data
public class MeterProviderConfig {

	private MeterProviderType meterProviderType;

	private int meterIntervalMills = 10_000; // 10s

	private PrometheusConfig prometheusConfig;

}
