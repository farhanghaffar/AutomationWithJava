package com.innroad.inncenter.webelements;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.innroad.inncenter.properties.OR;

public class WebElements_TaskList {

	WebDriver driver;
	public static Logger app_logs = Logger.getLogger("devpinoyLogger");

	public WebElements_TaskList(WebDriver driver2) {
		this.driver = driver2;
		PageFactory.initElements(this.driver, this);

	}
	// Date
	@FindBy(xpath = OR.TaskPageSize0)
	public String TaskPageSize0;

	@FindBy(xpath = OR.TaskPageSize1)
	public String TaskPageSize1;
	
	@FindBy(xpath = OR.TaskList_Date)
	public WebElement TaskList_Date;

	// Action
	@FindBy(xpath = OR.TaskList_ActionType)
	public WebElement TaskList_ActionType;
	// Task List Note Type

	@FindBy(id = OR.TaskList_Note_Internal)
	public WebElement TaskList_Note_Internal;

	@FindBy(id = OR.TaskList_Note_HouseKeeping)
	public WebElement TaskList_Note_HouseKeeping;

	@FindBy(id = OR.TaskList_Note_Request)
	public WebElement TaskList_Note_Request;

	@FindBy(id = OR.TaskList_Note_WakeupCall)
	public WebElement TaskList_Note_WakeupCall;

	@FindBy(id = OR.TaskList_Note_GuestNote)
	public WebElement TaskList_Note_GuestNote;

	@FindBy(id = OR.TaskList_Note_Complaint)
	public WebElement TaskList_Note_Complaint;

	@FindBy(id = OR.TaskList_Note_Message)
	public WebElement TaskList_Note_Message;

	@FindBy(id = OR.TaskList_Note_DepositRequired)
	public WebElement TaskList_Note_DepositRequired;

	@FindBy(id = OR.TaskList_Note_Email)
	public WebElement TaskList_Note_Email;

	@FindBy(id = OR.TaskList_Note_RoomMove)
	public WebElement TaskList_Note_RoomMove;

	@FindBy(id = OR.TaskList_Note_EarlyArrival)
	public WebElement TaskList_Note_EarlyArrival;

	@FindBy(id = OR.TaskList_Note_LateArrival)
	public WebElement TaskList_Note_LateArrival;

	@FindBy(id = OR.TaskList_Note_LateDeparture)
	public WebElement TaskList_Note_LateDeparture;

	@FindBy(id = OR.TaskList_Note_Cancellation)
	public WebElement TaskList_Note_Cancellation;

	@FindBy(id = OR.TaskList_Note_FrontDesk)
	public WebElement TaskList_Note_FrontDesk;

	@FindBy(id = OR.TaskList_Note_ParkingInformation)
	public WebElement TaskList_Note_ParkingInformation;

	@FindBy(id = OR.TaskList_Note_ADA)
	public WebElement TaskList_Note_ADA;

	@FindBy(id = OR.TaskList_IncludePastDueItems)
	public WebElement TaskList_IncludePastDueItems;

	@FindBy(id = OR.TaskList_SelectALlNoteTypes)
	public WebElement TaskList_SelectALlNoteTypes;

	@FindBy(xpath = OR.TaskList_Notes_Details)
	public List<WebElement> TaskList_Notes_Details;

	@FindBy(id = OR.TaskList_Select_Subject_Click)
	public WebElement TaskList_Select_Subject_Click;

	@FindBy(id = OR.TaskList_GoButton)
	public WebElement TaskList_GoButton;

	@FindBy(id = OR.TaskList_SaveButton)
	public WebElement TaskList_SaveButton;

	
	@FindBy(xpath = OR.TaskList_NewButton1)
	public WebElement TaskList_NewButton1;

	@FindBy(id = OR.TaskList_CancelButton)
	public WebElement TaskList_CancelButton;

	@FindBy(xpath = OR.TaskList_PageHyperLink)
	public WebElement TaskList_PageHyperLink;

	// New TaskList Elements

	@FindBy(id = OR.NewTaskList_Type)
	public WebElement NewTaskList_Type;

	@FindBy(id = OR.NewTaskList_Subject)
	public WebElement NewTaskList_Subject;

	@FindBy(id = OR.NewTaskList_SubjectPicker)
	public WebElement NewTaskList_SubjectPicker;

	@FindBy(xpath = OR.NewTaskList_Acc_Res)
	public WebElement NewTaskList_Acc_Res;

	@FindBy(id = OR.NewTaskList_Room_ResBtn)
	public WebElement NewTaskList_Room_ResBtn;

	@FindBy(id = OR.NewTaskList_Room_AccountBtn)
	public WebElement NewTaskList_Room_AccountBtn;

	@FindBy(id = OR.NewTaskList_DueDate)
	public WebElement NewTaskList_DueDate;

	@FindBy(id = OR.NewTaskList_ActionComplete)
	public WebElement NewTaskList_ActionComplete;

	@FindBy(id = OR.NewTaskList_ActionCancel)
	public WebElement NewTaskList_ActionCancel;

	@FindBy(id = OR.NewTaskList_ActionDelete)
	public WebElement NewTaskList_ActionDelete;

	@FindBy(id = OR.Room_Res_FirstRadioButton)
	public List<WebElement> Room_Res_FirstRadioButton;

	@FindBy(id = OR.Room_Res_SelectBtn)
	public WebElement Room_Res_SelectBtn;

	@FindBy(id = OR.Room_Res_NewResBtn)
	public WebElement Room_Res_NewResBtn;

	@FindBy(xpath = OR.Room_Res_GoBtn)
	public WebElement Room_Res_GoBtn;

	@FindBy(xpath = OR.Account_AccPicker_FirstRadioButton)
	public WebElement Account_AccPicker_FirstRadioButton;

	@FindBy(id = OR.Account_AccPicker_GoButton)
	public WebElement Account_AccPicker_GoButton;

	@FindBy(id = OR.Account_AccPicker_NameInput)
	public WebElement Account_AccPicker_NameInput;

	@FindBy(id = OR.TaskList_PrintButton)
	public WebElement TaskList_PrintButton;

	@FindBy(id = OR.TaskList_PDFReport)
	public WebElement TaskList_PDFReport;

	@FindBy(xpath = OR.AddTask_Button)
	public WebElement AddTask_Button;

	@FindBy(xpath = OR.AddTask_Popup)
	public WebElement AddTask_Popup;

	@FindBy(xpath = OR.SelectTask)
	public WebElement SelectTask;

	@FindBy(xpath = OR.TypeSearch)
	public WebElement TypeSearch;

	@FindBy(xpath = OR.SearchTaskButton)
	public WebElement SearchTaskButton;

	@FindBy(xpath = OR.SearchTaskInput)
	public WebElement SearchTaskInput;

	@FindBy(xpath = OR.FirstTaskInTaskListCheckBox)
	public WebElement FirstTaskInTaskListCheckBox;

	@FindBy(xpath = OR.FirstTaskInTaskListEditButton)
	public WebElement FirstTaskInTaskListEditButton;

	@FindBy(xpath = OR.TaskList_DeleteTaskButton)
	public WebElement TaskList_DeleteTaskButton;

	@FindBy(xpath = OR.TaskList_InfoPanelCloseButton)
	public WebElement TaskList_InfoPanelCloseButton;

	@FindBy(xpath = OR.BulkAction_ProceedButton)
	public WebElement BulkAction_ProceedButton;

	@FindBy(xpath = OR.TaskCategory)
	public WebElement TaskCategory;

	@FindBy(xpath = OR.CategoryType)
	public WebElement CategoryType;

	@FindBy(xpath = OR.Task_Detail)
	public WebElement Task_Detail;

	@FindBy(xpath = OR.Task_Remarks)
	public WebElement Task_Remarks;

	@FindBy(xpath = OR.Task_Assignee)
	public WebElement Task_Assignee;

	@FindBy(xpath = OR.Task_Save)
	public WebElement Task_Save;

	@FindBy(xpath = OR.Report)
	public WebElement Report;

	@FindBy(xpath = OR.TaskList_Report)
	public WebElement TaskList_Report;

	@FindBy(xpath = OR.ClickDateFilter)
	public WebElement ClickDateFilter;

	@FindBy(xpath = OR.Report_AllAssignees)
	public WebElement Report_AllAssignees;

	@FindBy(xpath = OR.Report_ByAssignees)
	public WebElement Report_ByAssignees;

	@FindBy(xpath = OR.Number_of_Tasks)
	public WebElement Number_of_Tasks;

	@FindBy(xpath = OR.TaskList_Filter)
	public WebElement TaskList_Filter;

	@FindBy(xpath = OR.Filter_TaskCategory)
	public WebElement Filter_TaskCategory;

	@FindBy(xpath = OR.SelectFirstTaskCategory)
	public WebElement SelectFirstTaskCategory;

	@FindBy(xpath = OR.SelectFilter_TaskCategory)
	public WebElement SelectFilter_TaskCategory;

	@FindBy(xpath = OR.FirstTaskType_Name)
	public WebElement FirstTaskType_Name;

	@FindBy(xpath = OR.TaskType_CheckBox)
	public WebElement TaskType_CheckBox;

	@FindBy(xpath = OR.BulkAction_Button)
	public WebElement BulkAction_Button;

	@FindBy(xpath = OR.TaskList_DatePicker)
	public WebElement TaskList_DatePicker;

	@FindBy(xpath = OR.Assign_Staff)
	public WebElement Assign_Staff;

	@FindBy(xpath = OR.Change_Status)
	public WebElement Change_Status;

	@FindBy(xpath = OR.AssignStaff_AssignTo)
	public WebElement AssignStaff_AssignTo;

	@FindBy(xpath = OR.AssignStaff_Proceed)
	public WebElement AssignStaff_Proceed;

	@FindBy(xpath = OR.ChangeStatus_SelectStatus)
	public WebElement ChangeStatus_SelectStatus;

	@FindBy(xpath = OR.ChangeStatus_Proceed)
	public WebElement ChangeStatus_Proceed;

	@FindBy(xpath = OR.ChangeStatus_Close)
	public WebElement ChangeStatus_Close;

	@FindBy(xpath = OR.TaskList_TODO)
	public WebElement TaskList_TODO;

	@FindBy(xpath = OR.TaskList_Inspection)
	public WebElement TaskList_Inspection;

	@FindBy(xpath = OR.TaskList_Done)
	public WebElement TaskList_Done;

	@FindBy(xpath = OR.TaskList_All)
	public WebElement TaskList_All;

	@FindBy(xpath = OR.TaskListReportPage_ByAssignee)
	public WebElement TaskListReportPage_ByAssignee;

	@FindBy(xpath = OR.TaskType_SelectStatusDropDown)
	public WebElement TaskType_SelectStatusDropDown;

	@FindBy(xpath = OR.TaskType_StatusDropDown_SaveBtn)
	public WebElement TaskType_StatusDropDown_SaveBtn;

	@FindBy(xpath = OR.TaskType_StatusDropDownButton)
	public WebElement TaskType_StatusDropDownButton;
	
	@FindBy(xpath = OR.TaskType_StatusDropDownButton1)
	public WebElement TaskType_StatusDropDownButton1;

	@FindBy(xpath = OR.TaskListFirstElementDate)
	public WebElement TaskListFirstElementDate;

	@FindBy(xpath = OR.SearchRoomAccAssign)
	public WebElement SearchRoomAccAssign;

	@FindBy(xpath = OR.TaskList_SearchButton)
	public WebElement TaskList_SearchButton;

	@FindBy(xpath = OR.EditTaskPopup)
	public WebElement EditTaskPopup;

	@FindBy(xpath = OR.Taskpopup_GuestName)
	public WebElement Taskpopup_GuestName;

	@FindBy(xpath = OR.Taskpopup_TaskDescription)
	public WebElement Taskpopup_TaskDescription;

	@FindBy(xpath = OR.Taskpopup_Close)
	public WebElement Taskpopup_Close;
	
	@FindBy(xpath = OR.selectAction)
	public WebElement selectAction;
	
	@FindBy(xpath = OR.NoteAction)
	public WebElement NoteAction;
	
	@FindBy(xpath = OR.NotesSave)
	public WebElement NotesSave;
	
	@FindBy(xpath = OR.SaveTaskList)
	public WebElement SaveTaskList;
	
	@FindBy(xpath = OR.includePastDueItems)
	public WebElement includePastDueItems;
	
	@FindBy(xpath = OR.Internal)
	public WebElement Internal;
	
	@FindBy(xpath = OR.Request)
	public WebElement Request;
	
	@FindBy(xpath = OR.WakeUpCall)
	public WebElement WakeUpCall;
	
	@FindBy(xpath = OR.GuestNote)
	public WebElement GuestNote;
	
	@FindBy(xpath = OR.Complaint)
	public WebElement Complaint;
	
	@FindBy(xpath = OR.RoomMove)
	public WebElement RoomMove;
	
	@FindBy(xpath = OR.Message)
	public WebElement Message;
	
	@FindBy(xpath = OR.DepositRequired)
	public WebElement DepositRequired;
	
	@FindBy(xpath = OR.Email)
	public WebElement Email;
	
	@FindBy(xpath = OR.LastArrival)
	public WebElement LastArrival;
	
	@FindBy(xpath = OR.LateDeparture)
	public WebElement LateDeparture;
	
	@FindBy(xpath = OR.Cancellation)
	public WebElement Cancellation;
	
	@FindBy(xpath = OR.FrontDesk)
	public WebElement FrontDesk;
	
	@FindBy(xpath = OR.ParkingInformation)
	public WebElement ParkingInformation;
	
	@FindBy(xpath = OR.ADA)
	public WebElement ADA;
	
	@FindBy(xpath = OR.taskListGoButton)
	public WebElement taskListGoButton;
	
	@FindBy(xpath = OR.NoteActionRequired)
	public WebElement NoteActionRequired;
	
	
	@FindBy(xpath = OR.taskListRows)
	public List<WebElement> taskListRows;
	
	@FindBy(xpath = OR.taskListCols)
	public List<WebElement> taskListCols;
	
	@FindBy(xpath = OR.HouseKeeping)
	public WebElement HouseKeeping;
	
	@FindBy(xpath = OR.EarlyArrival)
	public WebElement EarlyArrival;
	
	@FindBy(xpath = OR.TaskListNotesType)
	public WebElement TaskListNotesType;
	
	@FindBy(xpath = OR.TaskList_Action)
	public WebElement TaskList_Action;
	
	@FindBy(xpath = OR.TaskList_ActionRequired)
	public WebElement TaskList_ActionRequired;
	
	@FindBy(xpath = OR.TaskListPopUp_CancelButton)
	public WebElement TaskListPopUp_CancelButton;
	
	@FindBy(xpath = OR.ToastCloseButton)
	public WebElement ToastCloseButton;
	
	@FindBy(xpath = OR.SelectAllTask)
	public WebElement SelectAllTask;
	
	@FindBy(xpath = OR.DeleteTask_Button)
	public WebElement DeleteTask_Button;
	
	@FindBy(xpath = OR.TaskDelete_ProcessButton)
	public WebElement TaskDelete_ProcessButton;
	
	@FindBy(xpath = OR.NoTaskFound)
	public List<WebElement> NoTaskFound;
	
	
	@FindBy(className = OR.Toaster_Message)
	public WebElement Toaster_Message;
	
	@FindBy(xpath = OR.TaskPopup_Title)
	public WebElement TaskPopup_Title;
	
	@FindBy(xpath = OR.TaskPopup_Details)
	public WebElement TaskPopup_Details;
	
	@FindBy(xpath = OR.TaskPopup_CloseButton)
	public WebElement TaskPopup_CloseButton;
	
	@FindBy(id = OR.TaskList_NewButton)
	public WebElement TaskList_NewButton;
	
	@FindBy(xpath = OR.TaskList_NewButton_GS)
	public WebElement TaskList_NewButton_GS;
	
	@FindBy(xpath = OR.Taskpopup_Status)
	public WebElement Taskpopup_Status;
	
	@FindBy(xpath = OR.Taskpopup_Assignee)
	public WebElement Taskpopup_Assignee;
	
	@FindBy(xpath = OR.Taskpopup_RoomCtegory)
	public WebElement Taskpopup_RoomCtegory;
	
	@FindBy(xpath = OR.StatusBar_Report)
	public WebElement StatusBar_Report;
	
	@FindBy(xpath = OR.StatusBar)
	public WebElement StatusBar;

	@FindBy(xpath = OR.StatusDrodown)
	public WebElement StatusDrodown;

	@FindBy(xpath = OR.Verify_Bulk_Delete_popup)
	public WebElement Verify_Bulk_Delete_popup;
	
	@FindBy(xpath = OR.TaskLIst_TODoStatusDropDownBox)
	public WebElement TaskList_ToDoStatusDropDownBOx;


	@FindBy(xpath = OR.TaskLIst_DoneStatusDropDownBox)
	public WebElement TaskList_DoneStatusDropDownBOx;
	
	@FindBy(xpath = OR.TaskLIst_InspectionStatusDropDownBox)
	public WebElement TaskList_InspectionStatusDropDownBOx;
	
	@FindBy(xpath = OR.TaskLIst_StatusDropDownBoxFilterOption)
	public WebElement TaskList_StatusDropDownBoxFilterOption;
	
	@FindBy(xpath = OR.TaskLIst_StatusDropDownBoxButton)
	public WebElement TaskList_StatusDropDownBOxButton;



	@FindBy(xpath = OR.TaskForDropdown)
	public WebElement TaskForDropdown;
	
	@FindBy(xpath = OR.clickOnTaskReports)
	public WebElement clickOnTaskReports;
	
	@FindBy(id = OR.taskDueOn)
	public WebElement taskDueOn;
	
	@FindBy(xpath = OR.enterCustomRangeStartDate)
	public WebElement enterCustomRangeStartDate;
	
	@FindBy(xpath = OR.enterCustomRangeEndDate)
	public WebElement enterCustomRangeEndDate;
	
	@FindBy(xpath = OR.customRangeTodayDate)
	public WebElement customRangeTodayDate;
	
	@FindBy(xpath = OR.ClickOnAddNewTask)
	public WebElement clickOnAddNewTask;
	
	
	
	
}
