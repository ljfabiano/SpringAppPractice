package tiy.webapp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestWebChatClient {


	//static ToDoDatabase todoDatabase;

	@Before
	public void setUp() throws Exception {
//		if (todoDatabase == null) {
//			todoDatabase = new ToDoDatabase();
//			todoDatabase.init();
//		}
	}

	@After
	public void tearDown() throws Exception {
	}

//	@Test
//	public void contextLoads() {
//	}

	@Test
	public void testSendMessage()
	{
		String message = "The test message";//create a simple chat client here, and run it.
		String returnMessage = "";
		WebChatClient testClient = new WebChatClient();//.sendMessage(message);
		testClient.establishConnection();
		returnMessage = testClient.sendSingleMessage(message);
		assertEquals(message.toString(), returnMessage.toString());
		testClient.closeConnection();
	}

}
