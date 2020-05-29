package com.ooha.utils;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.omg.CORBA.portable.ApplicationException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CommonUtill {

	/** The Constant EMPTY_STRING. */
	private static final String EMPTY_STRING = "";

	/** MM/DD/YYYY DATE FORMAT. */
	public static final String MM_DATE_FORMAT = "MM-dd-yyyy";

	private static final char ALPHA = '^';
	private static final char NUMERIC = '#';
	private static final char ALPHA_NUMERIC = '*';
	private static final char CONSTANT = '&';
	private static final char BREAK = ' ';

	public static String getObjectId() {
		return ObjectId.get().toString();
	}

	/**
	 * Converts a list of strings to comma separated values.
	 *
	 * @param list the list
	 * @return String - comma separated string
	 */
	public static String convertListToCommaSeparatedString(List<String> list) {

		String retString = null;
		if (null != list
				&& !list.isEmpty()) {

			for (int index = 0; index < list.size(); index++) {
				if (null != retString) {
					retString = retString
							+ ",";
				} else {
					retString = "";
				}
				retString = retString
						+ list.get(index);
			}
		}

		return retString;
	}

	/**
	 * Round.
	 *
	 * @param places the places
	 * @param value the value
	 * @return the double
	 */
	public static Double round(int places, double value) {

		if (places > 0) {
			BigDecimal decimalValue = BigDecimal.valueOf(value);
			value = decimalValue.setScale(places, RoundingMode.HALF_UP).doubleValue();
		}

		return value;
	}

	/**
	 * Round.
	 *
	 * @param precision the precision
	 * @param value the value
	 * @param mode the mode
	 * @return the big decimal
	 */
	public static BigDecimal round(int precision, BigDecimal value, RoundingMode mode) {

		if (null != value) {
			value = value.setScale(precision, mode);
		}

		return value;
	}

	/**
	 * Converts a comma separated values to list of strings .
	 *
	 * @param inputStr the input str
	 * @return the list
	 */
	public static List<String> convertCommaSeparatedStringToList(String inputStr) {

		List<String> retList = null;

		if (null != inputStr
				&& !inputStr.isEmpty()) {
			String[] inputStrArray = inputStr.split(",");
			retList = Arrays.asList(inputStrArray);
		}

		return retList;
	}

	/**
	 * Validates whether the value is empty or not .
	 *
	 * @param value            - the String
	 * @return the boolean
	 * @return- true if the string is empty or null.
	 */
	public static final boolean isEmpty(String value) {
		return null == value
				|| value.trim().isEmpty();
	}

	/**
	 * Validates whether the value is empty or not .
	 *
	 * @param value            - the String
	 * @param defaultValue the default value
	 * @return the boolean
	 * @return- true if the string is empty or null.
	 */
	public static String isEmpty(String value, String defaultValue) {
		if (null == value
				|| value.trim().isEmpty()) {
			value = defaultValue;
		}
		return value;
	}

	/**
	 * Checks if is empty.
	 *
	 * @param <T> the generic type
	 * @param list the list
	 * @return the boolean
	 */
	public static <T> boolean isEmpty(List<T> list) {
		return null == list
				|| list.isEmpty();
	}

	/**
	 * Validates whether the value is empty or not .
	 *
	 * @param value  - the Object
	 * @return the boolean
	 * @return- true if the string is empty or null.
	 */
	public static boolean isEmpty(Object value) {
		return null == value;
	}

	/**
	 * Checks if JSON Array is empty.
	 *
	 * @param value the value
	 * @return the boolean
	 */
	public static boolean isEmpty(JSONArray value) {
		return null == value
				|| value.length() == 0;
	}

	/**
	 * Checks if the value is not null and not empty, if the passed value is null or empty then it will return the default value.
	 *
	 * @param value the value
	 * @param defaultValue the default value
	 * @return the string value
	 */
	public static Object getObjectValue(Object value, Object defaultValue) {
		Object returnValue = defaultValue;

		if (value != null) {
			returnValue = value;
		}

		return returnValue;
	}

	/**
	 * Validates whether the value is empty or not .
	 *
	 * @param value            - Integer
	 * @return the boolean
	 * @return- true if the Integer is empty or null.
	 */
	public static boolean isEmpty(Integer value) {
		return value == null;
	}

	/**
	 * Validates whether the value is empty or not .
	 *
	 * @param value            - Long
	 * @return the boolean
	 * @return- true if the Long is empty or null.
	 */
	public static boolean isEmpty(Long value) {
		return value == null;
	}

	/**
	 * Validates whether the value is empty or not .
	 *
	 * @param value            - BigInteger
	 * @return the boolean
	 * @return- true if the BigInteger is empty or null.
	 */
	public static Boolean isEmpty(BigInteger value) {
		return value == null;
	}

	/**
	 * Validate whether the date is empty or null.
	 *
	 * @param date the date
	 * @return the boolean
	 */
	public static boolean isDateEmpty(Date date) {
		return date == null;
	}

	/**
	 * Validate whether the date is empty or null.
	 *
	 * @param fromvalue the fromvalue
	 * @param toValue the to value
	 * @return the boolean
	 */
	public static boolean validateField(String fromvalue, String toValue) {
		if (isEmpty(fromvalue)
				|| isEmpty(toValue)) {
			return false;
		}

		if (fromvalue.equalsIgnoreCase(toValue)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Cast to a collection.
	 *
	 * @param <T> the generic type
	 * @param clazz the clazz
	 * @param c the c
	 * @return the list
	 */
	public static <T> List<T> castList(Class<? extends T> clazz, Collection<?> c) {
		List<T> r = new ArrayList<>(c.size());
		for (Object o : c)
			r.add(clazz.cast(o));
		return r;
	}

	/**
	 * Gets the string value.
	 *
	 * @param value the value
	 * @param defaultValue the default value
	 * @return the string value
	 */
	public static String getStringValue(Object value, String defaultValue) {

		String returnValue = defaultValue;

		if (value != null) {
			returnValue = value.toString();
		}

		return returnValue;
	}

	/**
	 * Gets the booleanvalue.
	 *
	 * @param value the value
	 * @return the booleanvalue
	 */
	public static boolean getBooleanValueFromInt(Integer value) {

		boolean booleanValue = false;

		if (value != null
				&& value == 1) {
			booleanValue = true;
		}

		return booleanValue;
	}

	/**
	 * Gets the boolean object.
	 *
	 * @param value the value
	 * @return the boolean object
	 */
	public static Object getBooleanObject(Object value) {

		String stringValue = (String) value;
		if (Boolean.FALSE.toString().equals(stringValue)
				|| Boolean.TRUE.toString().equals(stringValue)) {
			value = Boolean.parseBoolean(stringValue);
		}

		return value;
	}

	/**
	 * Gets the long value.
	 *
	 * @param value the value
	 * @return the long value
	 */
	public static Long getLongValue(String value) {

		Long returnValue = null;

		if (value != null
				&& !value.isEmpty()) {
			returnValue = Long.valueOf(value);
		}

		return returnValue;
	}

	/**
	 * Gets the date in MS.
	 *
	 * @param value the value
	 * @return the date in MS
	 * @throws Exception the application exception
	 */
	public static Long getDateInMS(String value) throws Exception {

		Long returnValue = null;

		if (!isEmpty(value)) {
			try {
				returnValue = Long.valueOf(value);
			} catch (Exception e) {
				throw new Exception(e);
			}
		}

		return returnValue;
	}

	/**
	 * Gets the big decimal value.
	 *
	 * @param value the value
	 * @return the big decimal value
	 */
	public static BigDecimal getBigDecimalValue(String value) {

		BigDecimal returnValue = null;

		if (value != null
				&& !value.isEmpty()) {
			returnValue = BigDecimal.valueOf(Double.valueOf(value));
		}

		return returnValue;
	}

	/**
	 * Gets the integer value.
	 *
	 * @param value the value
	 * @return the integer value
	 */
	public static Integer getIntegerValue(String value) {

		Integer returnValue = null;

		if (value != null
				&& !value.isEmpty()) {
			returnValue = Integer.valueOf(value);
		}

		return returnValue;
	}

	/**
	 * Gets the date from string.
	 *
	 * @param value the value
	 * @param format the format
	 * @return the date from string
	 */
	public static Date getDateFromString(String value, String format) {

		Date returnValue = null;

		if (value != null
				&& !value.isEmpty()) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				returnValue = sdf.parse(value);
			} catch (Exception e) {
				returnValue = null;
			}
		}

		return returnValue;
	}

	/**
	 * Cast the object to the defined class type.
	 *
	 * @param <T> the generic type
	 * @param from Object
	 * @param castObject - if this is true it will cast the object to the class type.
	 * @param to the to
	 * @return the t
	 */
	public static <T> T castObject(Object from, Boolean castObject, Class<T> to) {

		if (castObject
				&& from != null
				&& to.isAssignableFrom(from.getClass())) {

			return to.cast(from);
		}

		return null;

	}

	/**
	 *  Cast the object to the defined class type.
	 *
	 * @param <T> the generic type
	 * @param key - property name
	 * @param props - contains information about the properties in key value pair form.
	 * @param to - cast to this class.
	 * @return the t
	 */
	public static <T> T castObject(String key, Map<String, Object> props, Class<T> to) {
		return castObject(props.get(key), props.containsKey(key), to);
	}

	/**
	 * Generate JSON format String for a given key value.
	 *
	 * @param key the key
	 * @param value the value
	 * @return the JSON format str
	 */
	public static String getJSONFormatStr(String key, Object value) {
		return String.format("{\"%s\":%s}", key, value);
	}

	/**
	 * Retrun the value in lower case.
	 *
	 * @param value the value
	 * @return the string
	 */
	public static String toLowerCaseString(Object value) {
		if (value == null) {
			return null;
		} else {
			return value.toString().toLowerCase();
		}
	}

	/**
	 * Retrieve value from the request.
	 *
	 * @param key the key
	 * @param httpServletRequest the http servlet request
	 * @return the value from request
	 */
	public static String getValueFromRequest(String key, HttpServletRequest httpServletRequest) {

		String value;

		if (httpServletRequest != null
				&& httpServletRequest.getParameter(key) != null) {
			value = httpServletRequest.getParameter(key);
		} else {
			value = null;
		}

		return value;
	}

	/**
	 * Retrieve value from the request.
	 *
	 * @param key the key
	 * @param httpServletRequest the http servlet request
	 * @return the array of values from request
	 */
	public static String[] getValuesFromRequest(String key, HttpServletRequest httpServletRequest) {
		String[] value;

		if (httpServletRequest.getParameter(key) != null) {
			value = httpServletRequest.getParameterValues(key);
		} else {
			value = null;
		}

		return value;
	}

	/**
	 * Retrieve value from the request.
	 *
	 * @param <T> the generic type
	 * @param key the key
	 * @param httpServletRequest the http servlet request
	 * @param clz the clz
	 * @return the value from request and cast
	 */
	public static <T> T getValueFromRequestAndCast(String key, HttpServletRequest httpServletRequest, Class<T> clz) {

		T t;
		if (httpServletRequest.getParameter(key) != null) {
			t = castObject(httpServletRequest.getParameter(key), Boolean.TRUE, clz);
		} else {
			t = null;
		}

		return t;
	}

	/**
	 * Cast the object to the defined class type.
	 *
	 * @param <T> the generic type
	 * @param value -object to be casted
	 * @param to the to
	 * @return return the value after casting to the given type.
	 */
	public static <T> T castObject(Object value, Class<T> to) {
		if (value != null
				&& to.isAssignableFrom(value.getClass())) {
			return to.cast(value);
		}
		return null;

	}

	/**
	 * if props contains given key and its value in map is not null,then
	 * returns the date for that value.
	 *
	 * @param key the key
	 * @param props the props
	 * @return -returns the date for given hash
	 * @key-  map key
	 * @props - map which contains the key ,value from which we need to get long data value
	 */
	public static Date getDate(String key, Map<String, Object> props) {
		if (props.containsKey(key)
				&& props.get(key) != null) {
			Long dateString = (Long) props.get(key);
			if (dateString != null) {
				return new Date(dateString);
			}
		}
		return null;
	}

	/**
	 * Compare two dates and return the latest date.
	 *
	 * @param fromDate the from date
	 * @param toDate the to date
	 * @return - returns the latest date.
	 */
	public static Date getLatestDate(Date fromDate, Date toDate) {

		if (fromDate == null) {
			return toDate;
		}

		if (toDate == null) {
			return null;
		}

		if (fromDate.compareTo(toDate) <= 0) {
			return toDate;
		}

		return fromDate;
	}

	/**
	 * Gets the time in millies.
	 *
	 * @param timeInMillis the time in millis
	 * @param subDays - the days to substract from the current time.
	 * @return - the long value in milli seconds.
	 */
	public static long getTimeInMillies(Long timeInMillis, int subDays) {

		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(timeInMillis);
		cal.add(Calendar.DATE, -subDays);

		return cal.getTimeInMillis();
	}

	/**
	 * This method will create a parameter list for given array of objects.
	 *
	 * @param objects the objects
	 * @return the param list
	 */
	public static List<Object> getParamList(Object... objects) {

		List<Object> paramList = new ArrayList<>();

		for (Object object : objects) {
			paramList.add(object);
		}

		return paramList;
	}

	/**
	 * Fetch the current date without the seconds and milliseconds.
	 * @return the instance of Date.
	 */
	public static Date getCurrentDateWithoutMilliSeconds() {

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.SECOND, 0);

		return cal.getTime();
	}

	/**
	 * Convert Object of type T to String.
	 *
	 * @param <T> the generic type
	 * @param t - class of type T
	 * @return - the string in JSON format.
	 */
	public static <T> String convertObjectToJSONString(T t) {

		ObjectMapper mapper = new ObjectMapper();

		/** Object of type T to JSON in String. */
		String jsonInString = null;
		try {

			jsonInString = mapper.writeValueAsString(t);
		} catch (JsonProcessingException e) {

			return EMPTY_STRING;
		}

		return jsonInString;
	}

	/**
	 * Convert sring to json.
	 *
	 * @param json the json
	 * @return the JSON object
	 * @throws ApplicationException the application exception
	 */
	public static JSONObject convertSringToJson(String json) throws Exception {
		JSONObject jsonObject = null;
		try {

			jsonObject = new JSONObject(json);
		} catch (JSONException e) {

			List<Object> paramList = new ArrayList<>();
			paramList.add(json);

			throw new Exception(e);
		}
		return jsonObject;
	}

	/**
	 * Gets the request json from WIMS email.
	 *
	 * @param id the id
	 * @param messageType the message type
	 * @param locale the user locale
	 * @param to the to email ids
	 * @param cc the cc
	 * @param bcc the bcc
	 * @return the request json from WIMS email
	 */
	public static JSONObject prepareRequestJsonForWIMSEmail(String id, String messageType, String locale, String[] to, String[] cc,
			String[] bcc) {
		return prepareRequestJsonForWIMSEmail(id, messageType, locale, to, cc, bcc, null);
	}

	/**
	 * Gets the request json from WIMS email.
	 *
	 * @param id the id
	 * @param messageType the message type
	 * @param locale the user locale
	 * @param to the to email ids
	 * @param cc the cc
	 * @param bcc the bcc
	 * @param from the from
	 * @return the request json from WIMS email
	 */
	public static JSONObject prepareRequestJsonForWIMSEmail(String id, String messageType, String locale, String[] to, String[] cc,
			String[] bcc, String from) {

		JSONObject jsonObject = new JSONObject();

		/** Message type represent the email type, with this WIMS choose which template to map the data with. */
		jsonObject.put("messageType", messageType);
		/** Locale decides in which language the user should see the email. */
		jsonObject.put("participantLocale", locale);
		/** This is exactly not participant id, but holds a unique key with which the failed emails will be  
		 * tracked in WIMS side as the email sending part is asynchronous. */
		jsonObject.put("participantId", id);
		jsonObject.put("toEmail", to);
		jsonObject.put("ccEmail", cc);
		jsonObject.put("bccEmail", bcc);
		jsonObject.put("fromEmail", from);

		return jsonObject;
	}

	/**
	 * Convert String to  class of type  T.
	 *
	 * @param <T> the generic type
	 * @param json the json
	 * @param clz the clz
	 * @return - the CLASS T.
	 */
	public static <T> T convertJSONToObject(String json, Class<T> clz) {

		ObjectMapper mapper = new ObjectMapper();

		//Object of type T to JSON in String
		T t = null;
		try {
			if (json == null) {
				return null;
			}
			t = mapper.readValue(json, clz);

		} catch (IOException e) {

			return null;
		}

		return t;
	}

	/**
	 * Convert String to  class of type  T.
	 *
	 * @param <T> the generic type
	 * @param obj the obj
	 * @param clz the clz
	 * @return - the string in JSON format.
	 */
	public static <T> T convertMapToObject(Object obj, Class<T> clz) {

		ObjectMapper mapper = new ObjectMapper();

		//Object of type T to JSON in String
		T t = null;

		if (obj == null) {
			return null;
		}
		t = mapper.convertValue(obj, clz);

		return t;
	}

	/**
	 * Gets the complete request URI.
	 *
	 * @param httpServletRequest the http servlet request
	 * @return the complete request URI
	 */
	public static String getCompleteRequestURI(HttpServletRequest httpServletRequest) {
		String reqUrl = httpServletRequest.getRequestURI().toString();
		String queryString = httpServletRequest.getQueryString();
		if (queryString != null) {
			reqUrl += "?"
					+ queryString;
		}
		return reqUrl;
	}

	/**
	 * Validates the path against pattern.  
	 *
	 * @param from the String
	 * @param to the String
	 * @return the boolean
	 */
	public static Boolean isURIMatch(String[] from, String to) {

		String[] temp = to.split("/");
		Boolean flag = null;

		/**
		 * return False If the path length does not match against pattern.
		 */
		if (temp.length != from.length) {
			return Boolean.FALSE;
		}

		flag = validateAbsolutePathMatch(from, temp);

		return flag;
	}

	/**
	 * Validate the given "to" String against "from" string.
	 * @param from the String
	 * @param to the String
	 * @return the Boolean true/false
	 */
	private static boolean validateAbsolutePathMatch(String[] from, String[] to) {

		boolean flag = Boolean.FALSE;
		for (int i = 0; i < to.length; i++) {
			String val = to[i];
			String t = from[i];

			/**
			 * Check whether a string has \d format so that compare the part of the string.
			 * for example /v1 or v2 and the pattern is given as v\\d that mean compare just v and ignore reaming part of the string.  
			 */
			int index = t.indexOf("\\d");

			if (index != -1) {

				t = t.substring(0, index);
				val = val.substring(0, index);
				if (t.equals(val)) {
					flag = Boolean.TRUE;
				}
			} else if (val.equals(t)) {
				flag = Boolean.TRUE;
			}

			/**
			 * At point of time if String values does match then return the control. 
			 */
			if (!flag) {
				return Boolean.FALSE;
			} else {
				/**
				 * refresh the flag value.
				 */
				flag = Boolean.FALSE;
			}

		}

		return Boolean.TRUE;
	}

	/**
	 * Validate string.
	 *
	 * @param value the value
	 * @return the string
	 */
	public static String validateString(String value) {

		String updatedValue = "";

		if (!("null").equalsIgnoreCase(value)
				&& value != null) {
			updatedValue = value;
		}

		return updatedValue;
	}

	public static boolean isNumeric(String passedValue) {

		boolean isNumeric = false;

		if (null != passedValue
				&& !passedValue.trim().isEmpty()
				&& passedValue.trim().matches("[0-9]+")) {
			isNumeric = true;
		}

		return isNumeric;
	}

	/**
	 * Validates string and returns the key's value.
	 *
	 * @param json the json
	 * @param key the key
	 * @return the key value
	 */
	public static String getKeyValue(JSONObject json, String key) {
		return json.has(key) ? (String) json.get(key) : null;
	}

	/**
	 * Logs error with the given error message.
	 * 
	 * @param value
	 *            -the value for which null check has to be done.
	 * @param errorMessage
	 *            -the errormessage to be logged.
	 */
	public static void logErrorIfNull(String value, String errorMessage) {
		if (value == null
				|| value.trim().isEmpty()) {
		}
	}

	/**
	 * Get Epoch time.
	 *
	 * @param date the date
	 * @return the Epoch Time.
	 */
	public static Long getTimeInMillis(Date date) {

		if (date == null) {
			return null;
		}

		return date.getTime();
	}

	/**
	 * Get UTC Time. 
	 *
	 * @return the date
	 */
	public static Long getTimeInMillis() {

		Date currDate = getCurrentUTCTime();

		return currDate.getTime();
	}

	/**
	 * Gets the date from long.
	 *
	 * @param dateInLongFormat the date in long format
	 * @return the date from long
	 */
	public static Date getDateFromMillis(Long dateInLongFormat) {

		Date date = null;
		if (dateInLongFormat != null) {
			date = new Date(dateInLongFormat);
		}

		return date;
	}

	/**
	 * Gets the String value from json object for give key.
	 *
	 * @param jsonObject the json object
	 * @param key the key
	 * @return the value from json object
	 */
	public static String getValueFromJsonObject(JSONObject jsonObject, String key) {

		String value = null;

		if (jsonObject.has(key)) {
			value = (String) jsonObject.get(key);
		}

		return value;
	}

	/**
	 * Gets the current UTC time.
	 *
	 * @return the current UTC Date.
	 */
	public static Date getCurrentUTCTime() {
		OffsetDateTime utc = OffsetDateTime.now(ZoneOffset.UTC);
		return Date.from(utc.toInstant());
	}

	/**
	 * Validate number.
	 *
	 * @param number the number
	 * @return true, if successful
	 */
	public static boolean validateNumber(String number, int lenght) {
		boolean isValidNumber = false;

		if (!isEmpty(number)
				&& number.length() <= lenght) {
			Pattern pat = Pattern.compile("^[0-9()-]+[0-9()]$");
			Matcher mat = pat.matcher(number);
			isValidNumber = mat.matches();
		}

		return isValidNumber;
	}

	/**
	 * Validate number if its negative .
	 *
	 * @param number the number
	 * @return true, if successful
	 */
	public static boolean validateNegativeNumber(int number) {
		boolean isValidNumber = false;

		if (!isEmpty(number)
				&& (number >= 0)) {
			isValidNumber = true;
		}

		return isValidNumber;
	}

	/**
	 * Validate name.
	 *
	 * @param name the name
	 * @return true, if successful
	 */
	public static boolean validateName(String name) {
		return validateNamesWithLength(name, null);
	}

	/**
	 * Validate names With Specific Length.
	 *
	 * @param name the name
	 * @return true, if successful
	 */
	public static boolean validateNamesWithLength(String name, Integer length) {
		boolean isValidName = false;
		Pattern pat = null;

		if (!isEmpty(name)) {
			if (length != null) {
				isValidName = name.length() <= length;
			} else {
				pat = Pattern.compile("[^'<>%;()&+\"0-9]+");
				Matcher mat = pat.matcher(name);
				isValidName = mat.matches();
			}
		}
		return isValidName;
	}

	/**
	 * Validate email.
	 *
	 * @param email the email
	 * @return true, if successful
	 */
	public static boolean validateEmail(String email) {
		boolean isValidEmail = false;

		if (!isEmpty(email)) {
			String regex = "(^[a-zA-Z0-9]+[a-zA-Z0-9-_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z-.]+[a-zA-Z]{1,}+$)";
			Pattern emailPattern = Pattern.compile(regex);
			Matcher emailMatcher = emailPattern.matcher(email);
			isValidEmail = emailMatcher.matches();
		}

		return isValidEmail;
	}

	/**
	 * Validate password.
	 *
	 * @param password the password
	 * @return true, if successful
	 */
	public static boolean validatePassword(String password) {
		boolean isValidPassword = false;

		if (!isEmpty(password)) {
			Pattern passwordPattern1 = Pattern.compile("(?!.*([A-Za-z0-9])\\1{50,})((?=.*\\d)(?=.*?[a-zA-Z]).{6,51})");

			Matcher passwordMatcher1 = passwordPattern1.matcher(password);
			isValidPassword = !validatePasswordWithMaxOccurrence(password)
					&& passwordMatcher1.matches();
		}

		return isValidPassword;
	}

	/**
	 * Validate password with Max occurrence .
	 *
	 * @param password the password
	 * @return true, if successful
	 */
	public static boolean validatePasswordWithMaxOccurrence(String password) {
		boolean isValidPassword = false;

		if (null != password
				&& !password.trim().isEmpty()) {
			Character maxChar = findMaxOccurenceCharInString(password);
			isValidPassword = charCountInString(password, maxChar) > 50;
		}

		return isValidPassword;
	}

	/**
	 * It trims the string object if the value is not null.
	 *
	 * @param value the value
	 * @return the string
	 */
	public static String trim(String value) {
		if (value != null) {
			value = value.trim();
		}
		return value;
	}

	/**
	 * Gets the time in milliseconds.
	 * 
	 * @param dateObj the date obj
	 * @param dateFormat the date format
	 * @return the epoch time.
	 */
	public static Long getDateInMillis(Object dateObj, String dateFormat) {

		Long dateInMillis = null;

		if (dateObj != null) {
			Date date = null;
			if ((dateObj instanceof String)
					&& !dateObj.toString().trim().isEmpty()
					&& !dateObj.toString().contains("0000-00-00")) {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
				try {
					date = simpleDateFormat.parse(dateObj.toString());
				} catch (ParseException e) {
					System.out.println(e);
				}
			} else if (dateObj instanceof Date) {
				date = (Date) dateObj;
			}

			if (date != null) {
				dateInMillis = date.getTime();
			}

		}

		return dateInMillis;
	}

	/**
	 * It converts the LocalDate to Date
	 *
	 * @param localDate the local date
	 * @return the date
	 */
	public static Date asDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

	/** 
	 * returns the duration between Two Dates.
	 *
	 * @param firstDate the from date
	 * @param secondDate the to date
	 */
	public static long getDurationBetweenTwoDates(Date firstDate, Date secondDate) {
		long firstDateTime = firstDate.getTime();
		long secondDateTime = secondDate.getTime();

		if (firstDateTime > secondDateTime) {
			return 0;
		}

		long diffInMillies = Math.abs(secondDate.getTime()
				- firstDate.getTime());

		return TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
	}

	/** 
	 * returns the MaxOccurence of a char in a string.
	 *
	 * @param text the text
	 */
	public static Character findMaxOccurenceCharInString(String text) {
		HashMap<Character, Integer> map = new HashMap<>();
		Character maxChar = ' ';
		long max = Integer.MIN_VALUE;
		for (int i = 0; i < text.length(); i++) {
			Character current = text.charAt(i);
			if (map.containsKey(current)) {
				map.put(current, map.get(current)
						+ 1);
			} else {
				map.put(current, 1);
			}
			if (map.get(current) > max) {
				maxChar = current;
				max = map.get(current);
			}
		}
		return maxChar;
	}

	/** 
	 * returns the Char Count in a string.
	 *
	 * @param text the text
	 * @param maxchar the Character
	 */
	public static int charCountInString(String text, Character maxChar) {
		int charCount = 0;
		for (char ch : text.toCharArray()) {
			if (ch == maxChar) {
				charCount++;
			}
		}
		return charCount;
	}

	/**
	 * Validate the length of string, if the length of string is more than its specified value then ,
	 * the string will get trimmed to its specified length, otherwise the original value get returns.
	 *
	 * @param value the value
	 * @return the string
	 */
	public static String validateFieldLength(String value, int length) {

		if (null != value
				&& !value.isEmpty()) {
			value = value.trim();
			if (value.length() > length) {
				value = value.substring(0, length);
			}
		}

		return value;
	}

	/**
	 * 
	 * Validates the value against its mask.
	 * 
	 * 		For mask ##^^&CF& valid value will be like 11ABCF, For mask ##&XX&*** valid value will be like 99XXj8K
	 * 		Compares the value with its mask only when the length of mask[excluding constant i.e &] and  value are same.
	 *      ex - mask &AB&^# - constantCount = 2 ,value ABC12 -> mask.length() - constantCount = 4 and value.length() = 5 so valid = false
		    For mask &A&B    - constantCount = 2 ,value AB  -> mask.length() - constantCount 2= value.length() =2  valid = true,here the value
		    will get compared against its mask.
	 * @param value
	 * @param mask
	 * @return
	 */
	public static boolean validateValueWithMask(String value, String mask) {
		boolean valid = true;

		if (!isEmpty(mask)
				&& !isEmpty(value)) {

			char[] valCharArr = value.toCharArray();
			char[] maskCharArr = mask.toUpperCase().toCharArray();
			if (value.length() != mask.length()) {
				int constantCount = 0;

				for (int i = 0; i < maskCharArr.length; i++) {
					if (maskCharArr[i] == CONSTANT)
						constantCount++;
				}
				if ((mask.length()
						- constantCount) != value.length()) {
					valid = false;
				} else {
					valid = checkCharacter(valCharArr, maskCharArr);
				}

			} else {
				valid = checkCharacter(valCharArr, maskCharArr);
			}

		}
		return valid;
	}

	/**
	 * Check individual character against the mask characters.
	 *
	 * ^  	 - Alpha Characters [A-Z]
	 * #  	 - Numeric Characters [0-9]
	 * *  	 - Alpha Numeric Character[A-Za-Z0-9]
	 * && 	 - Data between ampersands are constants values
	 * space - Indicates break between data
	 * blank - free form text with no restrictions 
	 * 
	 * @param value char[]
	 * @param mask char[]
	 * @return boolean
	 */
	private static boolean checkCharacter(char[] valCharArr, char[] maskCharArr) {
		boolean valid = true;
		int startCons = -1;
		int endCons = -1;
		for (int maskIndex = 0, valIndex = 0; maskIndex < maskCharArr.length; maskIndex++, valIndex++) {
			if (endCons != -1) {
				startCons = -1;
				endCons = -1;
			}

			char maskChar = maskCharArr[maskIndex];

			if (maskChar == CONSTANT) {
				if (startCons == -1) {
					startCons = maskIndex;
				} else {
					endCons = maskIndex;
				}
				valIndex--;
				continue;
			}

			char valChar = valCharArr[valIndex];
			// this  will validate all the values between two constant(&) should be same in mask and values.
			if (startCons != -1
					&& endCons == -1) {
				valid = (valChar == maskChar);
			} else {
				switch (maskChar) {
					case ALPHA:
						valid = (valChar > 64
								&& valChar < 91);
						break;
					case ALPHA_NUMERIC:
						valid = ((valChar > 47
								&& valChar < 58)
								|| (valChar > 64
										&& valChar < 91)
								|| (valChar > 96
										&& valChar < 123));
						break;
					case NUMERIC:
						valid = (valChar > 47
								&& valChar < 58);
						break;
					case BREAK:
						valid = (valChar == ' ');
						break;
					case CONSTANT:
						if (startCons == -1) {
							startCons = maskIndex;
						} else {
							endCons = maskIndex;
						}
						break;
					default:
						valid = false;
						break;
				}
			}
			if (!valid) {
				break;
			}
		}
		return valid;
	}

	/**
	 * Format document number and adds zero padding to make it 10 digits
	 * 
	 * @param partner
	 * @return
	 */
	public static String formatdocumentNumber(String docNumber) {

		if (isEmpty(docNumber)) {
			// If partner is empty string then return the value as null.
			docNumber = null;
		} else if (docNumber.trim().length() == 8) {
			// For SAP now country we can have document of 8 digits, so before sending to PI we have to make it as 10 digit.
			docNumber = "00"
					+ docNumber;
		}

		return docNumber;

	}

	/**
	 * Checks if the boolean wrapper is empty and returns the true/false.
	 * @param val1 the Boolean
	 * @return the boolean
	 */
	public static boolean getBoolValue(Boolean val1) {
		if (!isEmpty(val1)) {
			return val1.booleanValue();
		}

		return false;
	}

	/**
	 * Validates whether the value or value1 is empty or not .
	 *
	 * @param value            - the String
	 * @return the boolean
	 * @return- true if the string is empty or null.
	 */
	public static final boolean checkIfEmpty(String value, String value1) {
		return (isEmpty(value)
				|| isEmpty(value1));
	}

	/**
	 * @param o
	 * @return String
	 */
	public static String getString(Object o) {
		return o != null ? o.toString() : null;

	}
}
