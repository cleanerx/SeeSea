package net.sf.seesea.content.api;

/**
 * Implementors of this interface to uploaded data content detection
 *
 */
public interface IContentDetector {

	/**
	 * This method determines the content types of the uploaded track files. It does no postprocessing or statistical analysis.
	 * @throws ContentDetectionException if it fails to start content detection.
	 */
	void setContentTypes() throws ContentDetectionException;

}
