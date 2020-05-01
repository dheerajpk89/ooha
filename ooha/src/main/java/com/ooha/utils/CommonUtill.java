package com.ooha.utils;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import org.bson.types.ObjectId;

public class CommonUtill {
	public static Date getCurrentUTCTime() {
		OffsetDateTime utc = OffsetDateTime.now(ZoneOffset.UTC);
		return Date.from(utc.toInstant());
	}

	public static String getObjectId() {
		return ObjectId.get().toString();
	}

}
