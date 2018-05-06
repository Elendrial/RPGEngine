package me.hii488.logging;

public enum LogSeverity {
	// Some may be a bit redundant, but oh well.
	ERROR(6),
	GENERAL_INFO(5),
	WARNING(4),
	MESSAGE(3),
	DETAIL(2),
	DEBUG(1);
	
	int level;
	
	LogSeverity(int l){
		level = l;
	}
}
