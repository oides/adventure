package adventure.gcm;

import java.util.ArrayList;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

public class AdventureGCMUtil {

	public static void sendMessage(String menssagem) {

		try {

			Sender sender = new Sender("AIzaSyCGw46898cYA9Yu5g3QfYcdkxprp46iYJA"); // google APIkey
			
			// use this to send message with payload data
			Message message = new Message.Builder().collapseKey("message")
					.timeToLive(3).delayWhileIdle(true)
					.addData("message", "Welcome to Push Notifications") // you
																			// can
																			// get
																			// this
																			// message
																			// on
																			// client
																			// side
																			// app
					.build();

			System.setProperty("https.proxyHost", "192.168.1.1"); // write your
																	// own
																	// proxyHost
			System.setProperty("https.proxyPort", "8080"); // write your own
															// proxyHost

			// Use this code to send notification message to a single device
			Result result = sender
					.send(message,
							"APA91bEC0ZwWeWgtaWIIarY8M_1lVN1GGyQuwwWikkuTmtRyibUHuAsiMb4ctfHyQZlkM018hI-BgbPGeZbbbQmDfLScPUdEZp2pEwJPh9LNDS-knetjQHCkgXFNKY5hOKAcRdSdPOR2",
							1);

			System.out.println("Message Result: " + result.toString()); // Print
																		// message
																		// result
																		// on
																		// console

			// Use this code to send notification message to multiple devices
			ArrayList<String> devicesList = new ArrayList<String>();

			// add your devices RegisterationID, one for each device
			devicesList
					.add("APA91bEC0ZwWeWgtaWIIarY8M_1lVN1GGyQuwwWikkuTmtRyibUHuAsiMb4ctfHyQZlkM018hI-BgbPGeZbbbQmDfLScPUdEZp2pEwJPh9LNDS-knetjQHCkgXFNKY5hOKAcRdSdPOR2");
			devicesList
					.add("APA91bE2w5kK_LTmbm0vUL9VvaXfT5mqdIo9a719K_U18M1bbK2cTbbnQVhMsogxczRpoPEjeyExCkyPI19L1bJz2fBln-k_5yJA3T9-XRBceMyjai9cPYbEKVwBRbEuurpR0ki1LJfP");

			// Use this code for multicast messages
			MulticastResult multicastResult = sender.send(message, devicesList,
					0);
			sender.send(message, devicesList, 0);
			System.out.println("Message Result: " + multicastResult.toString());// Print
																				// multicast
																				// message
																				// result
																				// on
																				// console

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
