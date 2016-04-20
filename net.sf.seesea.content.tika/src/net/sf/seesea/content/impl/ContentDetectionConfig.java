package net.sf.seesea.content.impl;

@interface ContentDetectionConfig {

	String basePath();
	
	String[] whitelistUsers() ;
	
	String[] blacklistUsers() ;

	String[] processTrackIds() ;

}
