package test.kh.swing;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonReader {
	public JSONObject connectionUrlToJson(String turn) {
		JSONObject jObj = null;
		try {
			//https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=112
			URL url = new URL("https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo="+turn);
			HttpsURLConnection conn = null;
			HostnameVerifier hnv = new HostnameVerifier() {
				@Override
				public boolean verify(String hostname, SSLSession session) {
					return true; //return false;
				}
			};
			HttpsURLConnection.setDefaultHostnameVerifier(hnv);
			conn = (HttpsURLConnection) url.openConnection();
			// 가용가능한 범위의 buffer를 사용할 수 있어서 읽어 들인 것을 BufferedReader 에 넣음.
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//			System.out.println(br.readLine());
			String iLine = br.readLine();
			JSONParser ps = new JSONParser();
			jObj = (JSONObject) ps.parse(iLine);
		} catch( MalformedURLException e) {
			System.out.println("접속 실패");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jObj;
	}
}
































