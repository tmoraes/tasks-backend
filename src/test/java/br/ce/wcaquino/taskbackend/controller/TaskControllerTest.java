package br.ce.wcaquino.taskbackend.controller;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.ce.wcaquino.taskbackend.model.Task;
import br.ce.wcaquino.taskbackend.repo.TaskRepo;
import br.ce.wcaquino.taskbackend.utils.ValidationException;


public class TaskControllerTest {

	@Mock
	TaskRepo taskRepo;
	
	@InjectMocks
	TaskController taskController;
	

	
    @Before 
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void testSave_TaskWithoutDescription_RaisesException() {
		
		Task todo = new Task(); 
		todo.setDueDate(LocalDate.now());
			
		try {
			taskController.save(todo);
			
			todo.setTask("");
			taskController.save(todo);
			Assert.fail("ValidationException was expected, but was not raised.");
			
		} catch (ValidationException e) {
			
			Assert.assertEquals("Fill the task description", e.getMessage());

		}
						
	}	
	
	@Test
	public void testSave_NullDueDate_RaisesException() {
		
		
		Task todo = new Task(); 		
		todo.setTask("Descrição");
		
		try {
			taskController.save(todo);
			
			Assert.fail("ValidationException was expected, but was not raised.");
			
		} catch (ValidationException e) {
			
			Assert.assertEquals("Fill the due date", e.getMessage());

		}
		
	}	
	
	@Test
	public void testSave_PastDueDate_RaisesException() {
		
		Task todo = new Task(); 		
		todo.setTask("Descrição");		
		todo.setDueDate(LocalDate.of(2010, 01, 01));
		
		try {
			taskController.save(todo);
			
			Assert.fail("ValidationException was expected, but was not raised.");
			
		} catch (ValidationException e) {
			
			Assert.assertEquals("Due date must not be in past", e.getMessage());

		}
				
	}
	
	@Test
	public void testSave_TaskSuccessful() throws ValidationException {
		
		Task todo = new Task(); 		
		todo.setTask("Descrição");		
		todo.setDueDate(LocalDate.now());
		

		taskController.save(todo);
		
		Mockito.verify(taskRepo).save(todo);
			
	}	
}

