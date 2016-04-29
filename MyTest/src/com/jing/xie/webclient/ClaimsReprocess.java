/**
 * 
 */
package com.jing.xie.webclient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author jamesxieaudaexplorecom
 * 
 */
public class ClaimsReprocess {
	public static void readClaimsIDs(File file) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		while ((line = br.readLine()) != null) {
			if (line.trim().length() > 0) {
				callRestfulService(line);
			}
		}
		br.close();
	}

	public static void callRestfulService(String claimId) throws Exception {
		String data = "{\"customerStatus\" : 5}";
		URL url = new URL("https://mobile.audaexplore.com/api/Claims/"
				+ claimId);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("PUT");
		conn.setDoOutput(true);
		conn.setConnectTimeout(100000);
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("Accept", "application/json");
		conn.setRequestProperty("Authorization",
				"Bearer Z6IwGI8JrtNwHvTD731wt4By4ffgds4F");
		OutputStream os = conn.getOutputStream();
		os.write(data.getBytes());
		os.flush();
		if (conn.getResponseCode() != 200) {
			File errorClaimFile = new File("error_claims.txt");
			FileWriter fileWritter = new FileWriter(errorClaimFile.getName(),
					true);
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			bufferWritter.write(claimId);
			bufferWritter.close();
			System.out.println("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

		StringBuilder output = new StringBuilder();
		String line;
		while ((line = br.readLine()) != null) {
			output.append(line);
		}
		conn.disconnect();
		System.out.println(output.toString());
	}

	public static void main(String[] args) throws Exception {
		if (args.length > 0) {
			ClaimsReprocess.readClaimsIDs(new File(args[0]));
		} else {
			System.out.println("Please pass right argument");
		}
	}
}
