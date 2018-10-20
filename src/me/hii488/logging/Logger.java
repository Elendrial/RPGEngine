package me.hii488.logging;

import java.io.PrintStream;

public class Logger {
	
	private PrintStream stream;
	private LogSeverity minimumToOutput;
	
	public Logger(PrintStream p) {
		stream = p;
		minimumToOutput = LogSeverity.WARNING;
	}
	
	public void print(LogSeverity severity, String output) {
		if(severity.level >= minimumToOutput.level) stream.println("[" + severity.name() + "] " + output);
	}
	
	public void print(LogSeverity severity, Object output) {
		print(severity, output.toString());
	}
	
	public void setMinOutput(LogSeverity s) {
		minimumToOutput = s;
	}
	
	public LogSeverity getMinOutput(LogSeverity s) {
		return minimumToOutput;
	}
	
	public void changePrintStream(PrintStream s) {
		stream = s;
	}
	
	
	private static Logger logger;
	
	public static void createDefaultLogger(PrintStream s) {
		logger = new Logger(s);
	}
	
	public static Logger getDefault() {
		return logger;
	}
	
}
