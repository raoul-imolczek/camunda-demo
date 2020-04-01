package com.imolczek.training.camunda.demo.interfaces;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.imolczek.training.camunda.demo.model.Lead;

@RestController
public class RESTController {

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private FormService formService;

    @PostMapping(path = "/newlead")
    public void newLead(@RequestBody Lead lead) {
    
    	ProcessInstance instance = runtimeService.startProcessInstanceByKey("Loan_Leads_Management_Process");
    	
    	Task task = taskService
    		.createTaskQuery()
    		.processInstanceId(instance.getId())
    		.taskDefinitionKey("Create_Lead_Task")
    		.singleResult();
    	
    	Map<String, Object> properties = new HashMap<>();
    	properties.put("firstName", lead.getFirstName());
    	properties.put("lastName", lead.getLastName());
    	properties.put("email", lead.getEmail());
    	
		formService.submitTaskForm(task.getId(), properties);
    	
    }	
}


