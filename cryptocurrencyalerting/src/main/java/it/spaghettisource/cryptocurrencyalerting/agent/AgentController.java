package it.spaghettisource.cryptocurrencyalerting.agent;

import it.spaghettisource.cryptocurrencyalerting.exception.ExceptionFactory;

public class AgentController {
	
	private ExceptionFactory exceptionFactory;		
	private Agent agent;
	private Thread agentThread;
	private long refreshRate;
	
		
	public AgentController(long refreshRate, ExceptionFactory exceptionFactory) {
		super();
		this.refreshRate = refreshRate;
		this.exceptionFactory = exceptionFactory;
		
	}


	public void start() {
	
		agent = new Agent(refreshRate);
		
		agentThread = new Thread(agent,"Agent Thread");
		agentThread.start();
		
	}
	
	
	public void shutdown() {
		agent.shutdown();
	}
	
	
	public void pauseAgent(boolean pause) {
		agent.setPause(pause);
	}
	
}
