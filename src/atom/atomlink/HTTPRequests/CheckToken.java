package atom.atomlink.HTTPRequests;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.bukkit.craftbukkit.libs.org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CheckToken {

	public static boolean tokenExists(String token) {

		try {
			URL url = new URL("http://localhost:21262/checkToken");
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			http.setConnectTimeout(5000);
			http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			http.setRequestProperty("token", token.toString());
			http.setDoOutput(true);
			http.setDoInput(true);
			http.setRequestMethod("GET");

			// read the response
			InputStream in = new BufferedInputStream(http.getInputStream());
			String result = IOUtils.toString(in, "UTF-8");
			
			JSONParser parser = new JSONParser();
			JSONObject json = null;
			try {
				json = (JSONObject) parser.parse(result);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			if(json.get("status").toString().equals("1")) {
				return true;
			} else {
				return false;
			}


		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

	}

}
