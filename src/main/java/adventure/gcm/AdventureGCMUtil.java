package adventure.gcm;

import java.util.ArrayList;

import javax.inject.Inject;

import adventure.entity.GCMRegID;
import adventure.persistence.GCMRegIDDAO;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Sender;

public class AdventureGCMUtil {

	@Inject
	private GCMRegIDDAO gcmRegIDDAO;
	
	public void sendMessage(String menssagem) {

		try {

			Sender sender = new Sender("AIzaSyCGw46898cYA9Yu5g3QfYcdkxprp46iYJA"); // google APIkey
			
			Message message = new Message.Builder().collapseKey("message")
					.timeToLive(3).delayWhileIdle(true)
					.addData("message", menssagem)
					.build();

			ArrayList<String> devicesList = new ArrayList<String>();

			for (GCMRegID gcmRegID : gcmRegIDDAO.findAll()) {
				devicesList.add(gcmRegID.getRegId());
			}
			
			MulticastResult multicastResult = sender.send(message, devicesList, 0);
			sender.send(message, devicesList, 0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
