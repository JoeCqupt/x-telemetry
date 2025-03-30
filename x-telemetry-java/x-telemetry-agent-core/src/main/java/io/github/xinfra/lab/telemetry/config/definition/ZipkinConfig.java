package io.github.xinfra.lab.telemetry.config.definition;

import io.opentelemetry.exporter.zipkin.ZipkinSpanExporter;
import lombok.Data;

@Data
public class ZipkinConfig {

	private String endpoint = ZipkinSpanExporter.DEFAULT_ENDPOINT;

}
