package com.excilys.formation.utils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public abstract class DateConverter {

	static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd");

	public static Object convert(String s) {
		Date t = null;
		if (s != null) {
			if (s.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}")) {
				try {
					t = new Date(simpleDateFormat.parse(s).getTime());
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
