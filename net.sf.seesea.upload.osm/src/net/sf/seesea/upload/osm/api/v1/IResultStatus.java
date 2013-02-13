package net.sf.seesea.upload.osm.api.v1;

import org.eclipse.core.runtime.IStatus;

public interface IResultStatus<T> extends IStatus {

	T getResult();
}
