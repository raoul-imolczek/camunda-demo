package com.imolczek.training.camunda.demo.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CalculateRiskScoreTask implements JavaDelegate {

	private Logger LOG = LoggerFactory.getLogger(CalculateRiskScoreTask.class);
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {

		LOG.info("Calculating risk score");
		
		execution.setVariable("approved", false);
		execution.setVariable("denied", false);
		
	}

}

