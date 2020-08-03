package com.innroad.inncenter.pageobjects;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.innroad.inncenter.interfaces.ITaskManagement;
import com.innroad.inncenter.pages.Page_TaskManagement;
import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;
import com.innroad.inncenter.webelements.Elements_TaskManagement;

public class TaskManagement implements ITaskManagement {

	public static Logger TaskManagementLogger = Logger.getLogger("TaskManagement");

	public String createTaskCategory(WebDriver driver, String categoryName,ArrayList<String> test_steps) throws InterruptedException {

		Elements_TaskManagement TaskManagement = new Elements_TaskManagement(driver);
		Wait.explicit_wait_visibilityof_webelement_150(TaskManagement.NewCategory, driver);
		Utility.ScrollToElement(TaskManagement.NewCategory, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,-300)");
		Wait.wait2Second();
		TaskManagement.NewCategory.click();
		test_steps.add("Click New Category Button");
		Utility.app_logs.info("Click New Category Button");
		assertTrue(TaskManagement.NewCategory_Section.isDisplayed(), "Failed: New Category Section");
		TaskManagement.AddTaskCategory.sendKeys(categoryName);
		test_steps.add("Enter Category Name: <b>" + categoryName+"</b>");
		Utility.app_logs.info("Enter Category Name: " + categoryName);
		Wait.wait2Second();
		try {
			TaskManagement.SaveCategory.click();
			Wait.wait1Second();
			/*assertTrue(driver.findElement(By.xpath(OR.Toaster_Message)).getText()
					.contains(categoryName + " has been created."), "Failed: Create Category");*/
			String toasterMsg=driver.findElement(By.className(OR.Toaster_Message)).getText();
			assertTrue(toasterMsg.contains(categoryName + " has been created."), "Failed: Create Category");
			TaskManagement.Toaster_Message_Close.click();
			Utility.app_logs.info("Click Toaster Close Button");
			test_steps.add("Created Category: <b>" + categoryName+"</b>");
			Utility.app_logs.info("Created Category: " + categoryName);

		} catch (Exception e) {
			if (TaskManagement.CategoryAlreadyExist.isDisplayed())
				
				{
				assertTrue(false, "Category already exist");
			} else {
				assertTrue(false);
			}

		}
		List<WebElement> CategoriesName = driver.findElements(By.xpath(OR.CategoriesNames));
		int count = driver.findElements(By.xpath(OR.CategoriesNames)).size();
		boolean found = false;
		for (WebElement category : CategoriesName) {
			String name = category.getText();
			// System.out.println(name);
			if (name.replaceAll("\\s", "").equals(categoryName.replaceAll("\\s", ""))) {
				// TaskManagementLogger.info("Category " + categoryName +
				// "Exist");
				found = true;
				break;
			}
		}
		assertTrue(found, "Failed: Category does not Exist");
		return categoryName;
	}

	public String createTask(WebDriver driver, String taskName, String categoryName) throws InterruptedException {
		Elements_TaskManagement TaskManagement = new Elements_TaskManagement(driver);		
		//taskName = taskName + Utility.getTimeStamp();

		// finding that category and clicking its create task type button

		List<WebElement> CategoriesName = driver.findElements(By.xpath(OR.CategoriesNames));
		int count = 1;
		boolean found = false;
		for (WebElement category : CategoriesName) {
			String name = category.getText();
			if (name.replaceAll("\\s", "").equals(categoryName.replaceAll("\\s", ""))) {
				// TaskManagementLogger.info("Category " + categoryName + "
				// Exist");
				String path = "(" + OR.CategoriesNames + ")[" + count
						+ "]//ancestor::h2//following-sibling::div//div[@class='create-new-task-type']/a";
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(path)));
				Wait.wait2Second();
				driver.findElement(By.xpath(path)).click();
				found = true;
				break;
			}
			count++;
		}
		Wait.explicit_wait_xpath(OR.CreateTaskType_Section, driver);
		assertTrue(TaskManagement.CreateTaskType_Section.isDisplayed(), "Failed: Create Task Type Section");
		TaskManagement.TaskTypeName.sendKeys(taskName);
		Wait.wait2Second();
		try {
			TaskManagement.SaveTask.click();
			/*	Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(OR.Toaster_Message)), driver);
			Utility.app_logs.info(driver.findElement(By.xpath(OR.Toaster_Message)).getText());
			assertTrue(driver.findElement(By.xpath(OR.Toaster_Message)).getText()
					.contains(taskName + " has been created."), "Failed: Create Task");*/
		//	Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.className(OR.Toaster_Message)), driver);
			Wait.wait2Second();
			Utility.app_logs.info(driver.findElement(By.className(OR.Toaster_Message)).getText());
			assertTrue(driver.findElement(By.className(OR.Toaster_Message)).getText()
					.contains(taskName + " has been created."), "Failed: Create Task");
			TaskManagement.Toaster_Message_Close.click();
			Utility.app_logs.info("Click Toaster Close Button");
		} catch (Exception e) {
			if (TaskManagement.TaskAlreadyExist.isDisplayed()) {
				assertTrue(false, "Task already exist");
			} else {
				assertTrue(false);
			}

		}

		return taskName;
	}

	public void DeleteTask_1(WebDriver driver, String taskName) throws InterruptedException {
		Elements_TaskManagement TaskManagement = new Elements_TaskManagement(driver);
		WebElement task = driver.findElement(By.xpath("//span[text()='" + taskName + "']"));
		Actions builder = new Actions(driver);
		builder.moveToElement(task).build().perform();
		WebDriverWait wait1 = new WebDriverWait(driver, 5);
		String CloseIcon = "//span[text()='" + taskName
				+ "']//ancestor::li//following-sibling::li[@class='task-options']//span[@class='closeIcon']";
		wait1.until(ExpectedConditions.elementToBeClickable(By.xpath(CloseIcon)));
		driver.findElement(By.xpath(CloseIcon)).click();
		Wait.wait2Second();
		Wait.waitUntilPresenceOfElementLocated(OR.Dialog, driver);
		TaskManagement.Delete.click();
		Wait.wait2Second();
		assertTrue(driver.findElement(By.xpath(OR.Toaster_Message)).getText().contains(taskName + " has been deleted."),
				"Failed: Task Delete");
		TaskManagement.Toaster_Message_Close.click();
		Utility.app_logs.info("Click Toaster Close Button");
	}

	public void DeleteCategory(WebDriver driver, String categoryName) throws InterruptedException {

		Elements_TaskManagement TaskManagement = new Elements_TaskManagement(driver);
		List<WebElement> CategoriesName = driver.findElements(By.xpath(OR.CategoriesNames));
		int count = 1;
		boolean found = false;
		for (WebElement category : CategoriesName) {
			String name = category.getText();
			if (name.replaceAll("\\s", "").equals(categoryName.replaceAll("\\s", ""))) {
				String path = "(" + OR.CategoriesNames + ")[" + count
						+ "]//ancestor::h2//following-sibling::span[@class='pull-right text-right options']//span";
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(path)));
				driver.findElement(By.xpath(path)).click();
				found = true;
				break;
			}
			count++;
		}
		if (found) {
			Wait.wait2Second();
			Wait.waitUntilPresenceOfElementLocated(OR.Dialog, driver);
			TaskManagement.Delete.click();
			Wait.wait2Second();
			assertTrue(driver.findElement(By.xpath(OR.Toaster_Message)).getText()
					.contains(" has been deleted."), "Failed: Category Delete");
			TaskManagement.Toaster_Message_Close.click();
			Utility.app_logs.info("Click Toaster Close Button");
		} else {
			TaskManagementLogger.info("Category to be deleted:" + categoryName + " not Exist");
		}
	}

	public void DeleteAllCategories(WebDriver driver, String categoryName) throws InterruptedException {

		Elements_TaskManagement TaskManagement = new Elements_TaskManagement(driver);
		while (true) {
			List<WebElement> CategoriesName = driver.findElements(By.xpath(OR.CategoriesNames));
			int count = 1;
			boolean found = false;
			for (WebElement category : CategoriesName) {
				String name = category.getText();
				if (name.replaceAll("\\s", "").startsWith(categoryName.replaceAll("\\s", ""))) {
					found = true;
					String path = "(" + OR.CategoriesNames + ")[" + count
							+ "]//ancestor::h2//following-sibling::span[@class='pull-right text-right options']//span";
					JavascriptExecutor jse = (JavascriptExecutor) driver;
					Utility.ScrollToElement(driver.findElement(By.xpath(path)), driver);
					driver.findElement(By.xpath(path)).click();

					Wait.wait2Second();
					Wait.waitUntilPresenceOfElementLocated(OR.Dialog, driver);
					TaskManagement.Delete.click();
					Wait.wait2Second();
					assertTrue(driver.findElement(By.xpath(OR.Toaster_Message)).getText()
							.contains(" has been deleted."), "Failed: Category Delete");
					TaskManagement.Toaster_Message_Close.click();
					Utility.app_logs.info("Click Toaster Close Button");
					found = true;
					break;
				}
				count++;
			}
			if (!found) {
				break;
			} 
		}
	}

	public void MoveTask(WebDriver driver, String taskName, String categoryName1, String categoryName2)
			throws InterruptedException {
		Elements_TaskManagement TaskManagement = new Elements_TaskManagement(driver);
		WebElement task = driver.findElement(By.xpath("//span[text()='" + taskName + "']"));
		Actions builder = new Actions(driver);
		builder.moveToElement(task).build().perform();
		WebDriverWait wait1 = new WebDriverWait(driver, 5);
		String ChangeCategory = "//span[text()='" + taskName
				+ "']//ancestor::li//following-sibling::li[@class='task-options']/span/div/button";
		wait1.until(ExpectedConditions.elementToBeClickable(By.xpath(ChangeCategory)));
		driver.findElement(By.xpath(ChangeCategory)).click();
		Wait.wait2Second();
		String Categories = "//span[text()='" + taskName
				+ "']//ancestor::li//following-sibling::li[@class='task-options']/span/div/ul/li/a";
		Wait.waitUntilPresenceOfElementLocated(Categories, driver);
		List<WebElement> CategoriesName = driver.findElements(By.xpath(Categories));
		boolean found = false;
		for (WebElement category : CategoriesName) {
			String name = category.getText();
			if (name.replaceAll("\\s", "").equals(categoryName2.replaceAll("\\s", ""))) {
				category.click();
				found = true;
				break;
			}
		}
		if (found) {
			Wait.wait2Second();
			assertTrue(
					driver.findElement(By.xpath(OR.Toaster_Message)).getText().contains(taskName + " has been moved."),
					"Failed: Move Task ");
			TaskManagement.Toaster_Message_Close.click();
			Utility.app_logs.info("Click Toaster Close Button");
			// TaskManagementLogger.info("Task : " + taskName + " has been moved
			// from Category : " + categoryName1
			// + " to Category: " + categoryName2);
			// TestCore.test_steps.add("Task : " + taskName + " has been moved
			// from Category : " + categoryName1
			// + " to Category : " + categoryName2);
		} else {
			assertTrue(false, "Task Category :" + categoryName2 + " not found");
		}

	}

	public void setInspectionCleaningToggle(WebDriver driver, boolean ToggleState) throws InterruptedException {
		Elements_TaskManagement TaskManagement = new Elements_TaskManagement(driver);
		WebElement ToggleOXPath = driver.findElement(By.xpath("(//div[@class='col-md-3'])[1]//span"));
		String ToggleOn = ToggleOXPath.getAttribute("class");
		String Toggleon = ToggleOn.split(" ")[2];
		if (Toggleon.equals("off") & ToggleState) {
			TaskManagement.InspectionFullCleaning_Toggle.click();
			Wait.wait10Second();
			String ToggleOn1 = ToggleOXPath.getAttribute("class");
			String Toggleon1 = ToggleOn1.split(" ")[2];
			assertTrue(Toggleon1.equals("on"), "Failed: Inspection toggle not enabled");

		} else if (Toggleon.equals("on") & !ToggleState) {
			TaskManagement.InspectionFullCleaning_Toggle.click();
			Wait.wait10Second();
			String ToggleOn2 = ToggleOXPath.getAttribute("class");
			String Toggleon2 = ToggleOn2.split(" ")[2];
			assertTrue(Toggleon2.equals("off"), "Failed: Inspection toggle enabled");
		}

	}

	public void SetRoomsToDirtyFlag(WebDriver driver, boolean ToggleState) throws InterruptedException {
		Elements_TaskManagement TaskManagement = new Elements_TaskManagement(driver);
		WebElement ToggleOXPath = TaskManagement.SetRoomsToDirty_Toggle;
		String ToggleOn = ToggleOXPath.getAttribute("class");
		String Toggleon = ToggleOn.split(" ")[2];
		if (Toggleon.equals("off") & ToggleState) {
			TaskManagement.SetRoomsToDirty_Toggle.click();
			Wait.wait10Second();
			String ToggleOn1 = ToggleOXPath.getAttribute("class");
			String Toggleon1 = ToggleOn1.split(" ")[2];
			assertTrue(Toggleon1.equals("on"), "Failed: Inspection toggle not enabled");

		} else if (Toggleon.equals("on") & !ToggleState) {
			TaskManagement.SetRoomsToDirty_Toggle.click();
			Wait.wait10Second();
			String ToggleOn2 = ToggleOXPath.getAttribute("class");
			String Toggleon2 = ToggleOn2.split(" ")[2];
			assertTrue(Toggleon2.equals("off"), "Failed: Inspection toggle enabled");
		}

	}

	public void getToggleStatus_CheckInCheckOut(WebDriver driver)
	{
		Elements_TaskManagement TaskManagement = new Elements_TaskManagement(driver);
		WebElement ToggleOXPath = TaskManagement.SetRoomsToDirty_Toggle;
		String ToggleOn = ToggleOXPath.getAttribute("class");
		String Toggleon = ToggleOn.split(" ")[2];
		if (Toggleon.equals("off")) {
			{
				Utility.toggle=false;
			}
		}
		else if (Toggleon.equals("on"))
		{
			Utility.toggle=true;
		}
	}
	
	public void enableAutoCreate(WebDriver driver, String taskName, boolean b) throws InterruptedException {

		Elements_TaskManagement TaskManagement = new Elements_TaskManagement(driver);
		String AutoCreate = "//span[text()='" + taskName
				+ "']//ancestor::li//following-sibling::li[@class='text-center'][1]/a";
		WebElement Button= driver.findElement(By.xpath(AutoCreate));
		Wait.explicit_wait_visibilityof_webelement(Button, driver);
		Utility.ScrollToElement(Button, driver);
		Button.click();
		Wait.wait2Second();
  		Utility.app_logs.info("Toaster Message " +driver.findElement(By.className(OR.Toaster_Message)).getText());
		assertTrue(driver.findElement(By.className(OR.Toaster_Message)).getText().contains(taskName + " has been updated."),
				"Failed: Task Update");
		TaskManagement.Toaster_Message_Close.click();
		Utility.app_logs.info("Click Toaster Close Button");
	
	}

	public void scheduleOnCheckOut(WebDriver driver, String taskName, boolean b) throws InterruptedException {
		Elements_TaskManagement TaskManagement = new Elements_TaskManagement(driver);
		String CheckBox = "//span[text()='" + taskName
				+ "']//ancestor::li//following-sibling::li[@class='text-center'][2]//span";
        Wait.WaitForElement(driver, CheckBox);
        Utility.ScrollToElement(driver.findElement(By.xpath(CheckBox)), driver);
		driver.findElement(By.xpath(CheckBox)).click();
		Wait.wait2Second();
		assertTrue(driver.findElement(By.className(OR.Toaster_Message)).getText().contains(taskName + " has been updated."),
				"Failed: Task Update");
		TaskManagement.Toaster_Message_Close.click();
		Utility.app_logs.info("Click Toaster Close Button");
	}

	public void setFrequency(WebDriver driver, String frequency, String taskName) throws InterruptedException {
		Elements_TaskManagement TaskManagement = new Elements_TaskManagement(driver);
		String Frequency = "//span[text()='" + taskName + "']//ancestor::li//following-sibling::li[3]//input";
		Wait.WaitForElement(driver, Frequency);
        Utility.ScrollToElement(driver.findElement(By.xpath(Frequency)), driver);
		driver.findElement(By.xpath(Frequency)).click();
		String SelectFrequency = "(//span[text()='" + taskName
				+ "']//ancestor::li//following-sibling::li[3])//following-sibling::li[contains(text(),'" + frequency
				+ "')]";
		Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(SelectFrequency)), driver);
		driver.findElement(By.xpath(SelectFrequency)).click();
		Wait.wait2Second();
		assertTrue(driver.findElement(By.className(OR.Toaster_Message)).getText().contains(taskName + " has been updated."),
				"Failed: Task Update");
		TaskManagement.Toaster_Message_Close.click();
		Utility.app_logs.info("Click Toaster Close Button");

	}
	public void ClickOnTaskManagement(WebDriver driver) {

		Elements_TaskManagement taskManagement = new Elements_TaskManagement(driver);
		Wait.WaitForElement_ID(driver, Page_TaskManagement.TaskManagement_tab);
		Wait.explicit_wait_visibilityof_webelement_120(taskManagement.TaskManagement_tab, driver);
		taskManagement.TaskManagement_tab.click();

	}

	public String CreateNawTask(WebDriver driver, String taskName) throws InterruptedException {

		Elements_TaskManagement taskManagement = new Elements_TaskManagement(driver);
		Wait.explicit_wait_visibilityof_webelement_120(taskManagement.AddNewTask, driver);
		Utility.ScrollToElement(taskManagement.AddNewTask, driver);
		taskManagement.AddNewTask.click();

		Wait.explicit_wait_visibilityof_webelement(taskManagement.Enter_NewTaskName, driver);
		Utility.ScrollToElement(taskManagement.Enter_NewTaskName, driver);
		taskManagement.Enter_NewTaskName.sendKeys(taskName);
		taskManagement.TaskSaveButton.click();
		Wait.wait1Second();
		Wait.explicit_wait_visibilityof_webelement_120(taskManagement.Toaster_Message, driver);
		String message = taskManagement.Toaster_Message.getText();
		System.out.println("message : " + message);
		assertEquals(message, taskName + " has been created.");
		taskManagement.ToastCloseButton.click();
		return message;
	}

	public String UpdateTask(WebDriver driver, String taskName, String updateTaskName) throws InterruptedException {

		Elements_TaskManagement taskManagement = new Elements_TaskManagement(driver);
		String path = "//label[@title='Click to Edit Task Type']//div//span[text()='Wake up Call']";
		Wait.WaitForElement(driver, path);
		WebElement element = driver.findElement(By.xpath(path));
		Wait.explicit_wait_visibilityof_webelement(element, driver);
		Utility.ScrollToElement(element, driver);
		Wait.wait1Second();
		WebElement enterTaskName = driver
				.findElement(By.xpath("//label[@title='Click to Edit Task Type']//div//span[text()='" + taskName + "']//..//..//div//input"));
		enterTaskName.click();
		Utility.ScrollToElement(enterTaskName, driver);
		enterTaskName.clear();
		enterTaskName.sendKeys(updateTaskName);
		taskManagement.FrontDesk_Title.click();
		Wait.wait1Second();
		Wait.explicit_wait_visibilityof_webelement_120(taskManagement.Toaster_Message, driver);
		String message = taskManagement.Toaster_Message.getText();
		System.out.println("message : " + message);
		assertEquals(message, updateTaskName + " has been updated.");
		taskManagement.ToastCloseButton.click();
		return message;
	}
	
public String Verify_DeleteTask(WebDriver driver, String taskName) throws InterruptedException {
		
		Elements_TaskManagement TaskManagement = new Elements_TaskManagement(driver);
		
		String path = "//span[text()='" + taskName + "']";
		String toaster_message = "No";
	
		List<WebElement> task_size = driver.findElements(By.xpath("//span[text()='" + taskName + "']"));
		if (task_size.size()>0) {
			WebElement task = driver.findElement(By.xpath("//span[text()='" + taskName + "']"));
	
		Wait.WaitForElement(driver, path);
		Utility.ScrollToElement(task, driver);
		Wait.wait1Second();
		
		Actions builder = new Actions(driver);
		builder.moveToElement(task).build().perform();
		WebDriverWait wait1 = new WebDriverWait(driver, 5);
		String CloseIcon = "//span[text()='" + taskName
				+ "']//ancestor::li//following-sibling::li[@class='task-options']//span[@class='closeIcon']";
		
		Wait.WaitForElement(driver, CloseIcon);
		driver.findElement(By.xpath(CloseIcon)).click();
		Wait.wait1Second();
		TaskManagement.Delete.click();
		Wait.explicit_wait_visibilityof_webelement_120(TaskManagement.Toaster_Message, driver);
		 toaster_message = TaskManagement.Toaster_Message.getText();
		assertTrue(toaster_message.contains(taskName + " has been deleted."),
				"Failed: Task Delete");
		TaskManagement.Toaster_Message_Close.click();
		Utility.app_logs.info("Click Toaster Close Button");
		}
		return toaster_message;
	}

public String DeleteTask(WebDriver driver, String taskName) throws InterruptedException {
	
	Elements_TaskManagement TaskManagement = new Elements_TaskManagement(driver);
	String path = "//span[text()='" + taskName + "']";
	Wait.WaitForElement(driver, path);
	WebElement task = driver.findElement(By.xpath("//span[text()='" + taskName + "']"));
	Utility.ScrollToElement(task, driver);
	Wait.wait1Second();
	
	Actions builder = new Actions(driver);
	builder.moveToElement(task).build().perform();
	WebDriverWait wait1 = new WebDriverWait(driver, 5);
	String CloseIcon = "//span[text()='" + taskName
			+ "']//ancestor::li//following-sibling::li[@class='task-options']//span[@class='closeIcon']";
	
	Wait.WaitForElement(driver, CloseIcon);
	driver.findElement(By.xpath(CloseIcon)).click();
	Wait.wait1Second();
	TaskManagement.Delete.click();
	Wait.explicit_wait_visibilityof_webelement_120(TaskManagement.Toaster_Message, driver);
	String toaster_message = TaskManagement.Toaster_Message.getText();
	assertTrue(toaster_message.contains(taskName + " has been deleted."),
			"Failed: Task Delete");
	TaskManagement.Toaster_Message_Close.click();
	Utility.app_logs.info("Click Toaster Close Button");
	return toaster_message;
}


public void setInspectionCleaningToggleFlagStatus(WebDriver driver) {
	WebElement ToggleOXPath = driver.findElement(By.xpath("(//div[@class='col-md-3'])[1]//span"));
	String ToggleOn = ToggleOXPath.getAttribute("class");
	String Toggleon = ToggleOn.split(" ")[2];
	if (Toggleon.equals("off")) {
		{
			Utility.toggle = false;
		}
	} else if (Toggleon.equals("on")) {
		Utility.toggle = true;
	}
}


public void deleteAllCategories(WebDriver driver, String categoryName, ArrayList<String> test_steps ) throws InterruptedException {

	Elements_TaskManagement TaskManagement = new Elements_TaskManagement(driver);
	while (true) {
		List<WebElement> CategoriesName = driver.findElements(By.xpath(OR.CategoriesNames));
		int count = 1;
		boolean found = false;
		for (WebElement category : CategoriesName) {
			String name = category.getText();
			if (name.replaceAll("\\s", "").startsWith(categoryName.replaceAll("\\s", ""))) {
				found = true;
				String path = "(" + OR.CategoriesNames + ")[" + count
						+ "]//ancestor::h2//following-sibling::span[@class='pull-right text-right options']//span";
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				Utility.ScrollToElement(driver.findElement(By.xpath(path)), driver);
				driver.findElement(By.xpath(path)).click();

				Wait.wait2Second();
				Wait.waitUntilPresenceOfElementLocated(OR.Dialog, driver);
				TaskManagement.Delete.click();
				test_steps.add("Delete Category: <b>" + categoryName+ "</b>");
				Utility.app_logs.info("Delet Category: " + categoryName);
				Wait.wait2Second();
				/*assertTrue(driver.findElement(By.xpath(OR.Toaster_Message)).getText()
						.contains(" has been deleted."), "Failed: Category Delete");*/
				assertTrue(driver.findElement(By.className(OR.Toaster_Message)).getText()
						.contains(" has been deleted."), "Failed: Category Delete");
				TaskManagement.Toaster_Message_Close.click();
				test_steps.add("Click Toaster Close Button");
				Utility.app_logs.info("Click Toaster Close Button");
				found = true;
				break;
			}
			count++;
		}
		if (!found) {
			test_steps.add("Category Not found");
			Utility.app_logs.info("Category Not found");
			break;
		} 
	}
}


public String getTaskDueTime(WebDriver driver, String taskName) throws InterruptedException {
	String frequency = "//span[text()='" + taskName + "']//ancestor::li//following-sibling::li[1]//input";
	WebElement element= driver.findElement(By.xpath(frequency));
	JavascriptExecutor executor = (JavascriptExecutor) driver;
	String duetime = (String) executor.executeScript("return arguments[0].value",
			element);
	Utility.app_logs.info("Task Due Time is: "+ duetime);
	return duetime;

}
	
}
