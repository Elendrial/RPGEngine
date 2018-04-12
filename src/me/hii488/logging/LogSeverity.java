package me.hii488.logging;

public enum LogSeverity {
	ERROR(5),
	WARNING(4),
	MESSAGE(3),
	DETAIL(2),
	DEBUG(1);
	
	int level;
	
	LogSeverity(int l){
		level = l;
	}
}
