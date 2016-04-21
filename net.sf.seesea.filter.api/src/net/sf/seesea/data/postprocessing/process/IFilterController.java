package net.sf.seesea.data.postprocessing.process;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import net.sf.seesea.track.api.data.ITrackFile;

/**
 * a filter controller resembles
 * @author jens
 *
 */
public interface IFilterController {

	
	void setTimeout(long i);

	/**
	 * processes this collection of track files.
	 * 
	 * @param singleTrackList
	 * @param b
	 * @throws FilterException
	 */
	void process(Collection<ITrackFile> singleTrackList, boolean b) throws FilterException;

	void setFilterProperties(List<Map<String, Object>> filterProperties);

}
