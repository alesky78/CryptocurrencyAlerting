package it.spaghettisource.cryptocurrencyalerting.agent;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.spaghettisource.cryptocurrencyalerting.alert.PriceVariationGlobalMarketAlert;
import it.spaghettisource.cryptocurrencyalerting.exception.ExceptionFactory;
import it.spaghettisource.cryptocurrencyalerting.repository.PriceVariationGlobalMarketAlertRepository;
import it.spaghettisource.cryptocurrencyalerting.services.ServiceLocator;

public class Agent implements Runnable {

	static Logger log = LoggerFactory.getLogger(Agent.class);

	private boolean pause;
	private boolean shutdown;

	//refresh time to check again all the allerts
	private long refreshPeriodMillisecond;

	//store the time when run the last trigger
	private long timeLastTriggerMilliseconds;

	private ExceptionFactory exceptionFactory;

	private PriceVariationGlobalMarketAlertRepository repository;

	public Agent(long refreshAlertCheckMillisecond){
		pause = false;
		shutdown = false;
		timeLastTriggerMilliseconds = System.currentTimeMillis();
		this.refreshPeriodMillisecond = refreshAlertCheckMillisecond;

		//all the repository here
		exceptionFactory = ServiceLocator.getInstance().getExceptionFactory();
		repository = new PriceVariationGlobalMarketAlertRepository(exceptionFactory);
	}


	public void setPause(boolean pause) {
		this.pause = pause;
	}


	public void shutdown() {
		this.shutdown = true;
	}


	public void run() {

		long timeSinceLastLoop;
		log.info("agent start: refresh rate configured:" +refreshPeriodMillisecond);

		while(!shutdown){
			if(!pause){

				try {

					timeSinceLastLoop  = (System.currentTimeMillis()-timeLastTriggerMilliseconds);

					if( refreshPeriodMillisecond < timeSinceLastLoop){

						timeLastTriggerMilliseconds = System.currentTimeMillis();
						
						log.debug("start trigger");

						//check all the alerts
						List<PriceVariationGlobalMarketAlert> alerts =  repository.getAll();
						for (PriceVariationGlobalMarketAlert alert : alerts) {
							alert.checkAlert();
						}


					}else {
						//sleep for the time of refresh period so next time we could already run the execution
						log.debug("sleep for "+refreshPeriodMillisecond);
						Thread.sleep(refreshPeriodMillisecond);
					}

				} catch (Exception e) {
					log.error("error executing the loop",e);
				}
				
			}else{
				//in pause

			}

		}
		
		log.info("agent shutdown");	
		
	}



}
