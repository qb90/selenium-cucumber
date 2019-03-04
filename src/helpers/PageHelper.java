package helpers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PageHelper {

	public static String cityNameExtractor(String cityName) {
		String extractedCityName = null;
		String pattern = "\\:(.+)\\[";

		if (cityName != "") {
			Pattern p = Pattern.compile(pattern);
			Matcher m = p.matcher(cityName);

			if (m.find()) {
				String cityNameMatch = m.group(0);
				String tempCity = cityNameMatch.replace(": ", "");
				extractedCityName = tempCity.replace(" [", "");
			}
		}

		return extractedCityName;
	}

}
