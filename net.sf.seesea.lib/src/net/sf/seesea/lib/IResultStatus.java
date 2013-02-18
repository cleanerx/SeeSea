package net.sf.seesea.lib;

import org.eclipse.core.runtime.IStatus;

public interface IResultStatus<T> extends IStatus {

	T getResult();
}
