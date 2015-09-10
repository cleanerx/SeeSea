package net.sf.seesea.data.postprocessing.process;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import net.sf.seesea.services.navigation.ITrackFile;


public interface IFilterController {

	void setTimeout(long i);

	void process(Collection<ITrackFile> singleTrackList, boolean b) throws FilterException;

	void setFilterProperties(List<Map<String, Object>> filterProperties);

}
