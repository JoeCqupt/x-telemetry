package io.github.xinfra.lab.telemetry.exception;

public class AgentException extends RuntimeException {

	public AgentException() {
		super();
	}

	public AgentException(String message) {
		super(message);
	}

	public AgentException(String message, Throwable cause) {
		super(message, cause);
	}

	public AgentException(Throwable cause) {
		super(cause);
	}

	protected AgentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
