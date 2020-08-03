package com.innroad.inncenter.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.NoSuchProviderException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.SubjectTerm;

import org.openqa.selenium.WebDriver;

import com.innroad.inncenter.testcore.TestCore;

//	Utility for interacting with an email application

public class EmailUtils {

	private static String targetDirectory;
	private Folder folder;

	// Defining a private folder object which represents the current folder that
	// we are reading mails from.

	public enum EmailFolder {
		INBOX("Inbox"), SPAM("Junk Email");

		private String text;

		private EmailFolder(String text) {
			this.text = text;
		}

		public String getText() {
			return text;
		}
	}

	/**
	 * Uses username and password in properties file to read from a given folder
	 * of the email application
	 * 
	 * @param emailFolder
	 *            Folder in email application to interact with
	 * @throws MessagingException
	 */
	public EmailUtils(EmailFolder emailFolder) throws MessagingException {
		Properties props = System.getProperties();
		try {
			props.load(new FileInputStream(new File("resources/email.properties")));
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}

		String username = System.getProperty("email.username");
		String password = System.getProperty("email.password");
		String server = System.getProperty("mail.smtp.host");

		Session session = Session.getInstance(props);
		Store store = session.getStore("imaps");
		store.connect(server, username, password);

		folder = store.getFolder(emailFolder.getText());
		folder.open(Folder.READ_WRITE);
		System.out.println("Default TargetDirectory set to : " + targetDirectory);
	}

	/**
	 * Connects to email server with credentials provided to read from a given
	 * folder of the email application
	 * 
	 * @param username
	 *            Email username (e.g. guest@email.com)
	 * @param password
	 *            Email password
	 * @param server
	 *            Email server (e.g. smtp.email.com)
	 * @param emailFolder
	 *            Folder in email application to interact with
	 */
	public EmailUtils(String username, String password, String server, EmailFolder emailFolder)
			throws MessagingException {
		Properties props = System.getProperties();
		try {
			props.load(new FileInputStream(new File("resources/email.properties")));
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}

		Session session = Session.getInstance(props);
		Store store = session.getStore("imaps");
		store.connect(server, username, password);

		folder = store.getFolder(emailFolder.getText());
		folder.open(Folder.READ_WRITE);
	}

	// ************* Email Actions *******************

	public void openEmail(Message message) throws Exception {
		message.getContent();
	}

	public int getNumberOfMessages() throws MessagingException {
		return folder.getMessageCount();
	}

	public int getNumberOfUnreadMessages() throws MessagingException {
		return folder.getUnreadMessageCount();
	}

	// Gets a message by its position in the folder. The earliest message is
	// indexed at 1.

	public Message getMessageByIndex(int index) throws MessagingException {
		return folder.getMessage(index);
	}

	public Message getLatestMessage() throws MessagingException {
		return getMessageByIndex(getNumberOfMessages());
	}

	// Gets all messages within the folder

	public Message[] getAllMessages() throws MessagingException {
		return folder.getMessages();
	}

	/**
	 * @param maxToGet
	 *            maximum number of messages to get, starting from the latest.
	 *            For example, enter 100 to get the last 100 messages received.
	 */
	public Message[] getMessages(int maxToGet) throws MessagingException {
		Map<String, Integer> indices = getStartAndEndIndices(maxToGet);
		return folder.getMessages(indices.get("startIndex"), indices.get("endIndex"));
	}

	/**
	 * Searches for messages with a specific subject
	 * 
	 * @param subject
	 *            Subject to search messages for
	 * @param unreadOnly
	 *            Indicate whether to only return matched messages that are
	 *            unread
	 * @param maxToSearchmaximum
	 *            number of messages to search, starting from the latest. For
	 *            example, enter 100 to search through the last 100 messages.
	 */
	public Message[] getMessagesBySubject(String subject, boolean unreadOnly, int maxToSearch) throws Exception {
		Map<String, Integer> indices = getStartAndEndIndices(maxToSearch);

		Message messages[] = folder.search(new SubjectTerm(subject),
				folder.getMessages(indices.get("startIndex"), indices.get("endIndex")));

		if (unreadOnly) {
			List<Message> unreadMessages = new ArrayList<Message>();
			for (Message message : messages) {
				if (isMessageUnread(message)) {
					unreadMessages.add(message);
				}
			}
			messages = unreadMessages.toArray(new Message[] {});
		}

		return messages;
	}

	public Message[] getMessagesBySubjectRead(String subject, boolean unreadOnly, int maxToSearch) throws Exception {
		Map<String, Integer> indices = getStartAndEndIndices(maxToSearch);

		Message messages[] = folder.search(new SubjectTerm(subject),
				folder.getMessages(indices.get("startIndex"), indices.get("endIndex")));

		List<Message> unreadMessages = new ArrayList<Message>();
		for (Message message : messages) {
			if (isMessageUnread(message)) {
				unreadMessages.add(message);
			}
		}
		messages = unreadMessages.toArray(new Message[] {});
		return messages;
	}



	// Returns HTML of the email's content

	public String getMessageContent(Message message) throws Exception {
		StringBuilder builder = new StringBuilder();
		BufferedReader reader = new BufferedReader(new InputStreamReader(message.getInputStream()));
		String line;
		while ((line = reader.readLine()) != null) {
			builder.append(line);
		}
		return builder.toString();
	}

	// Returns all urls from an email message with the linkText specified

	public List<String> getUrlsFromMessage(Message message, String linkText) throws Exception {
		String html = getMessageContent(message);
		List<String> allMatches = new ArrayList<String>();
		Matcher matcher = Pattern.compile("(<a [^>]+>)" + linkText + "</a>").matcher(html);
		while (matcher.find()) {
			String aTag = matcher.group(1);
			allMatches.add(aTag.substring(aTag.indexOf("http"), aTag.indexOf("\">")));
		}
		return allMatches;
	}

	private Map<String, Integer> getStartAndEndIndices(int max) throws MessagingException {
		int endIndex = getNumberOfMessages();
		int startIndex = endIndex - max;

		// In event that maxToGet is greater than number of messages that exist
		if (startIndex < 1) {
			startIndex = 1;
		}

		Map<String, Integer> indices = new HashMap<String, Integer>();
		indices.put("startIndex", startIndex);
		indices.put("endIndex", endIndex);

		return indices;
	}

	// ************* BOOLEAN METHODS *******************

	// Searches an email message for a specific string

	public boolean isTextInMessage(Message message, String text) throws Exception {
		String content = getMessageContent(message);
		// Some Strings within the email have whitespace and some have break
		// coding. Need to be the same.
		//System.out.println(message.getContent().toString());
		content = content.replace("&nbsp;", " ");
		String str=content.toString();
		return content.contains(text);
	}

	public boolean isMessageInFolder(String subject, boolean unreadOnly) throws Exception {
		int messagesFound = getMessagesBySubject(subject, unreadOnly, getNumberOfMessages()).length;
		return messagesFound > 0;
	}

	public boolean isMessageUnread(Message message) throws Exception {
		return !message.isSet(Flags.Flag.SEEN);
	}

	// *************download attachments*************

	/**
	 * Sets the directory where attached files will be stored.
	 * 
	 * @param path
	 *            is absolute path of the directory
	 */
	public static void setTargetDirectory(String path) {
		targetDirectory = path;
		System.out.println("TargetDirectory changed to : " + targetDirectory);
		// verify that path value should not be empty
		if (path != "") {

			// Check for the folder path and create it if is not available
			File targetDir = new File(path);
			try {
				if (!targetDir.exists()) {
					try {
						targetDir.mkdirs();
					} catch (Exception e) {
						System.out.println("failed to created folder , " + e.getMessage());
					}
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.print("please provide the valid path name");
		}

	}

	// Downloads new messages and saves attachments to disk if any.

	public void downloadEmailAttachments(String subject, boolean unreadOnly, int maxToSearch) throws Exception {

		try {
			// fetches new messages from server
			Message[] arrayMessages = getMessagesBySubject(subject, unreadOnly, maxToSearch);

			for (int i = 0; i < arrayMessages.length; i++) {
				Message message = arrayMessages[i];
				Address[] fromAddress = message.getFrom();
				String from = fromAddress[0].toString();
				String msgSubject = message.getSubject();
				String sentDate = message.getSentDate().toString();

				String contentType = message.getContentType();
				Object messageContent = "";

				// store attachment file name, separated by comma
				String attachFiles = "";

				if (contentType.contains("multipart")) {
					// content may contain attachments
					Multipart multiPart = (Multipart) message.getContent();
					int numberOfParts = multiPart.getCount();
					for (int partCount = 0; partCount < numberOfParts; partCount++) {
						MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);
						if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
							// this part is attachment
							String fileName = part.getFileName();
							attachFiles += fileName + ", ";
							part.saveFile(targetDirectory + File.separator + fileName);
						} else {
							// this part may be the message content
							messageContent = part.getContent();
							System.out.println(messageContent);
							
							
							
						}
					}

					if (attachFiles.length() > 1) {
						attachFiles = attachFiles.substring(0, attachFiles.length() - 2);
					}
				} else if (contentType.contains("text/plain") || contentType.contains("text/html")) {
					Object content = message.getContent();
					if (content != null) {
						messageContent = content.toString();
					}
				}

				// print out details of each message
              Object msgContent =messageContent;
              
				System.out.println("Message #" + (i + 1) + ":");
				System.out.println("\t From: " + from);
				System.out.println("\t Subject: " + msgSubject);
				System.out.println("\t Sent Date: " + sentDate);
				//System.out.println("\t Message: " + messageContent);
				System.out.println("\t Attachments: " + attachFiles);

			}
		} catch (NoSuchProviderException ex) {
			System.out.println("No provider for pop3.");
			ex.printStackTrace();
		} catch (MessagingException ex) {
			System.out.println("Could not connect to the message store");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}


	public boolean verifyEmailAttachments(String subject, boolean unreadOnly, int maxToSearch) throws Exception {
		String attachFiles = "";
		try {
			// fetches new messages from server
			Message[] arrayMessages = getMessagesBySubject(subject, unreadOnly, maxToSearch);

			for (int i = 0; i < arrayMessages.length; i++) {
				Message message = arrayMessages[i];
				Address[] fromAddress = message.getFrom();
				String from = fromAddress[0].toString();
				String msgSubject = message.getSubject();
				String sentDate = message.getSentDate().toString();

				String contentType = message.getContentType();
				String messageContent = "";

				// store attachment file name, separated by comma


				if (contentType.contains("multipart")) {
					// content may contain attachments
					Multipart multiPart = (Multipart) message.getContent();
					int numberOfParts = multiPart.getCount();
					for (int partCount = 0; partCount < numberOfParts; partCount++) {
						MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);
						if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
							// this part is attachment
							String fileName = part.getFileName();
							attachFiles += fileName + ", ";
							//part.saveFile(targetDirectory + File.separator + fileName);
						} else {
							// this part may be the message content
							messageContent = part.getContent().toString();
						}
					}

					if (attachFiles.length() > 1) {
						attachFiles = attachFiles.substring(0, attachFiles.length() - 2);
					}
				} else if (contentType.contains("text/plain") || contentType.contains("text/html")) {
					Object content = message.getContent();
					if (content != null) {
						messageContent = content.toString();
					}
				}


			}
		} catch (NoSuchProviderException ex) {
			System.out.println("No provider for pop3.");
			ex.printStackTrace();
		} catch (MessagingException ex) {
			System.out.println("Could not connect to the message store");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		if (attachFiles.length() > 1) {
			return true;
		}else {
			return false;
		}

	}


	// Get Reset Password Link From gmail
	public void GetResetPasswordLink(WebDriver driver) throws Exception {

		EmailUtils emailUtils = new EmailUtils(EmailUtils.EmailFolder.INBOX);
		Message messages[] = emailUtils.getMessagesBySubject("Welcome to innRoad - User Info", false, 1);
		String envName = TestCore.envURL.substring(TestCore.envURL.indexOf('/') + 2, TestCore.envURL.indexOf('.'))
				.toUpperCase();
		for (Message message : messages) {

			String bodyContent = message.getContent().toString();
			System.out.println("bodyContent:" + bodyContent);
			String resetLink = "";
			if (envName.equals("PROD")) {
				System.out
				.println(bodyContent.substring(bodyContent.indexOf("https"), bodyContent.indexOf(">pass") - 1));
				resetLink = bodyContent.substring(bodyContent.indexOf("https"), bodyContent.indexOf(">pass") - 1);
			} else

			{
				System.out
				.println(bodyContent.substring(bodyContent.indexOf("http"), bodyContent.indexOf(">pass") - 1));
				resetLink = bodyContent.substring(bodyContent.indexOf("http"), bodyContent.indexOf(">pass") - 1);

			}
			driver.get(resetLink);
		}
	}

	public String GetClientInfoFormLink(WebDriver driver, boolean isClick) throws Exception {

		EmailUtils emailUtils = new EmailUtils(EmailUtils.EmailFolder.INBOX);
		Message messages[] = emailUtils.getMessagesBySubject("Welcome to innRoad Payments!", false, 1);
		String envName = TestCore.envURL.substring(TestCore.envURL.indexOf('/') + 2, TestCore.envURL.indexOf('.'))
				.toUpperCase();
		String resetLink = "";
		String bodyContent = "";
		for (Message message : messages) {
			DateFormat dateFormat = new SimpleDateFormat(
					"EEE MMM dd HH:mm:ss zzz yyyy");
			Date date = new Date();
			System.out.println("get date "+dateFormat.format(date));
			System.out.println("date : "+message.getReceivedDate());
			bodyContent = message.getContent().toString();
			System.out.println("bodyContent:" + bodyContent);

			System.out
			.println(bodyContent.substring(bodyContent.indexOf("http"), bodyContent.indexOf(">Click") - 1));
			resetLink = bodyContent.substring(bodyContent.indexOf("http"), bodyContent.indexOf(">Click") - 1);


		}
		if (isClick) {
			driver.get(resetLink);
		}
		return bodyContent;

	}

	public String GetClientInfoForm_OnPause(WebDriver driver) throws Exception {

		EmailUtils emailUtils = new EmailUtils(EmailUtils.EmailFolder.INBOX);
		Message messages[] = emailUtils.getMessagesBySubject("Your innRoad Payments account has been temporarily suspended", false, 1);

		String resetLink = "";
		String bodyContent = "";
		for (Message message : messages) {
			DateFormat dateFormat = new SimpleDateFormat(
					"EEE MMM dd HH:mm:ss zzz yyyy");
			Date date = new Date();
			System.out.println("get date "+dateFormat.format(date));
			System.out.println("date : "+message.getReceivedDate());
			bodyContent = message.getContent().toString();
			//System.out.println("bodyContent:" + bodyContent);

		}

		return bodyContent;

	}

	public String GetClientInfoForm_OnReactivate(WebDriver driver) throws Exception {

		EmailUtils emailUtils = new EmailUtils(EmailUtils.EmailFolder.INBOX);
		Message messages[] = emailUtils.getMessagesBySubject("Your innRoad Payments account has been reactivated", false, 1);

		String bodyContent = "";
		for (Message message : messages) {
			DateFormat dateFormat = new SimpleDateFormat(
					"EEE MMM dd HH:mm:ss zzz yyyy");
			Date date = new Date();
			System.out.println("get date "+dateFormat.format(date));
			System.out.println("date : "+message.getReceivedDate());
			bodyContent = message.getContent().toString();

		}

		return bodyContent;

	}
	
	
	public static void sendEmailWithAttachment(String attachment) throws Exception{

	      // Recipient's email ID needs to be mentioned.
	      String to = "surender.avula@innroad.com";

	      // Sender's email ID needs to be mentioned
	      String from ="innroadguest@gmail.com";

	      final String username = "innroadguest";//change accordingly
	      final String password = "Innroad@123";//change accordingly

	      // Assuming you are sending email through smtp.gmail.com
	      String host = "smtp.gmail.com";

	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "465");
	      props.put("mail.smtp.socketFactory.port", "true");
	      props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", "465");

	      // Get the Session object.
	      Session session = Session.getInstance(props,
	         new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	               return new PasswordAuthentication(username, password);
	            }
	         });
	
		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			// Set Subject: header field
			message.setSubject("Automation Execution Report");

			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart();

			// Now set the actual message
			messageBodyPart.setText("Automation Execution Report");

			// Create a multipar message
			Multipart multipart = new MimeMultipart();

			// Set text message part
			multipart.addBodyPart(messageBodyPart);

			// Part two is attachment
			messageBodyPart = new MimeBodyPart();
			// String filename = System.getProperty("user.dir") + File.separator + "extent-reports" + File.separator + attachmentFile;
			DataSource source = new FileDataSource(attachment);
			messageBodyPart.setDataHandler(new DataHandler(source));
			
			String reportName = attachment.substring(attachment.lastIndexOf("\\")+1);
			// System.out.println(reportName);
			
			messageBodyPart.setFileName(reportName);
			multipart.addBodyPart(messageBodyPart);

			// Send the complete message parts
			message.setContent(multipart);

			// Send message
			Transport.send(message);

			System.out.println("Sent message successfully....");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public static void deleteEmail(String reservation) throws MessagingException {

		Properties props = System.getProperties();
		try {
			props.load(new FileInputStream(new File("resources/email.properties")));
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}

		String username = System.getProperty("email.username");
		String password = System.getProperty("email.password");
		String server = System.getProperty("mail.smtp.host");

		Session session = Session.getInstance(props);
		Store store = session.getStore("imaps");
		store.connect(server, username, password);

		// opens the inbox folder
		Folder folderInbox = store.getFolder("INBOX");
		folderInbox.open(Folder.READ_WRITE);

		// fetches new messages from server
		Message[] arrayMessages = folderInbox.getMessages();

		for (int i = arrayMessages.length-1; i >=0; i--) {
			System.out.println(i);
			Message message = arrayMessages[i];
			String subject = message.getSubject();
			System.out.println(subject);
			if (subject.contains("Reservation #: "+reservation)) {
				message.setFlag(Flags.Flag.DELETED, true);
				System.out.println("Marked DELETE for message: " + subject);
				break;
			}else if(i==(arrayMessages.length-30)) {
				break;
			}

		}

		// expunges the folder to remove messages which are marked deleted
		boolean expunge = true;
		folderInbox.close(expunge);

		// another way:
		//folderInbox.expunge();
		//folderInbox.close(false);

		// disconnect
		store.close();


	}


	public static void UnreadEmail(String reservation) throws MessagingException {

		Properties props = System.getProperties();
		try {
			props.load(new FileInputStream(new File("resources/email.properties")));
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}

		String username = System.getProperty("email.username");
		String password = System.getProperty("email.password");
		String server = System.getProperty("mail.smtp.host");

		Session session = Session.getInstance(props);
		Store store = session.getStore("imaps");
		store.connect(server, username, password);

		// opens the inbox folder
		Folder folderInbox = store.getFolder("INBOX");
		folderInbox.open(Folder.READ_WRITE);

		// fetches new messages from server
		Message[] arrayMessages = folderInbox.getMessages();

		for (int i = arrayMessages.length-1; i >=0; i--) {
			System.out.println(i);
			Message message = arrayMessages[i];
			String subject = message.getSubject();
			System.out.println(subject);
			if (subject.contains(reservation)) {
				message.setFlag(Flags.Flag.SEEN, false);
				System.out.println("Marked UnSeen for message: " + subject);
				break;
			}else if(i==(arrayMessages.length-30)) {
				break;
			}

		}

		// expunges the folder to remove messages which are marked deleted
		boolean expunge = true;
		folderInbox.close(expunge);

		// another way:
		//folderInbox.expunge();
		//folderInbox.close(false);

		// disconnect
		store.close();


	}


	public static boolean waitTillGetMessagesBySubject(String reservation) throws MessagingException, InterruptedException {
		boolean flag=false;
		int count=0;
		outer : while(true) {
			Properties props = System.getProperties();
			try {
				props.load(new FileInputStream(new File("resources/email.properties")));
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(-1);
			}

			String username = System.getProperty("email.username");
			String password = System.getProperty("email.password");
			String server = System.getProperty("mail.smtp.host");

			Session session = Session.getInstance(props);
			Store store = session.getStore("imaps");
			store.connect(server, username, password);

			// opens the inbox folder
			Folder folderInbox = store.getFolder("INBOX");
			folderInbox.open(Folder.READ_WRITE);

			// fetches new messages from server
			Message[] arrayMessages = folderInbox.getMessages();

			for (int i = arrayMessages.length-1; i >=0; i--) {
				//System.out.println(i);
				Message message = arrayMessages[i];
				String subject = message.getSubject();
				System.out.println(subject);
				if (subject.contains(reservation)) {
					System.out.println(subject);
					flag=true;
					break outer;
				}else if(i==(arrayMessages.length-30)) {
					break;
				}

			}

			// expunges the folder to remove messages which are marked deleted
			boolean expunge = true;
			folderInbox.close(expunge);

			if(count==50) {
				break outer;
			}else {
				Thread.sleep(20000);
				System.out.println(count);
				count++;
			}

			// disconnect
			store.close();

		}
		return flag;
	}


}


