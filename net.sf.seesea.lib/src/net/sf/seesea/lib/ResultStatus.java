package net.sf.seesea.lib;

import org.eclipse.core.runtime.Status;

public class ResultStatus<T> extends Status implements IResultStatus<T> {

	private T result;

	public ResultStatus(T result, int severity, String pluginId, int code,
			String message, Throwable exception) {
		super(severity, pluginId, code, message, exception);
		this.result = result;
	}

	public ResultStatus(T result, int severity, String pluginId, String message,
			Throwable exception) {
		super(severity, pluginId, message, exception);
		this.result = result;
	}

	public ResultStatus(T result, int severity, String pluginId, String message) {
		super(severity, pluginId, message);
		this.result = result;
	}

	public ResultStatus(int severity, String pluginId, String message,
			Throwable exception) {
		super(severity, pluginId, message, exception);
	}

	public ResultStatus(int severity, String pluginId, String message) {
		super(severity, pluginId, message);
	}

	@Override
	public T getResult() {
		return result;
	}


}
