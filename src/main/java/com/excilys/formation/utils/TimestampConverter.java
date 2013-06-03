package com.excilys.formation.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public abstract class TimestampConverter {

	static final SimpleDateFormat simpleDateTimeFormat = new SimpleDateFormat(
			"yyyy-MM-dd hh:mm:ss");
	static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd");

	public static Object convert(String s) {
		Timestamp t = null;
		if (s != null) {
			if (s.matches("[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}(\\.[0-9])?")) {
				try {
					t = new Timestamp(simpleDateTimeFormat.parse(s).getTime());
				} catch (ParseException e) {
					e.printStackTrace();
				}
			} else if (s.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}")) {
				try {
					t = new Timestamp(simpleDateFormat.parse(s).getTime());
				} catch (ParseException e) {
					e.printStackTrace();
				}
			} else if (!s.isEmpty()) {
				return Var.ERROR;
			}
		}
		return t;
	}

}
