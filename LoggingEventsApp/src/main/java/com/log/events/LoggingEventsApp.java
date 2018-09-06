/**
 * 
 */
package com.log.events;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.logevents.details.EventDetails;
import com.logevents.details.LogEventsDetails;
import com.logevents.details.LogEventsList;
import com.logevents.repository.LogEventsData;

/**
 * @author Shrinivas
 *
 */
public class LoggingEventsApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		JSONParser parser = new JSONParser();
		LogEventsData logEventsData = new LogEventsData();

		try {

			Object obj = parser.parse(new FileReader("logevents.json"));
					
			JSONObject jsonObject = (JSONObject) obj;
			EventDetails eventDetails = new EventDetails();

			ObjectMapper mapper = new ObjectMapper();
			LogEventsList logeventList = mapper.readValue(jsonObject.toString(), LogEventsList.class);

			LogEventsDetails[] logEvents = logeventList.getLogevents();

			List<LogEventsDetails> logEventList = Arrays.asList(logEvents);

			Map<String, LogEventsDetails> logMap = new HashMap<String, LogEventsDetails>();
			for (LogEventsDetails logeventObject : logEventList) {
				if (logMap.get(logeventObject.getId()) == null) {
					logMap.put(logeventObject.getId(), logeventObject);
				} else {
					LogEventsDetails logevents = logMap.get(logeventObject.getId());
					long timeStamp = logeventObject.getTimestamp() - logevents.getTimestamp();
					timeStamp = (timeStamp < 0 ? -timeStamp : timeStamp);
					if (timeStamp > 4) {
						eventDetails.setEventId(logevents.getId());
						eventDetails.setEventDuration(Long.toString(timeStamp));
						 logEventsData.insertLogEvents(eventDetails.getEventId(),
						 Long.toString(timeStamp));
						
					}

				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

}
