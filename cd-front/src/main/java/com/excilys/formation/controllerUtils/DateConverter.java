package com.excilys.formation.controllerUtils;

import java.beans.PropertyEditorSupport;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateConverter extends PropertyEditorSupport {

	static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd");

	@Override
	public String getAsText() {
		Date date = (Date) getValue();
		if (date == null) {
			return null;
		}
		return String.valueOf(date);
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text != null && !text.trim().isEmpty()
				&& text.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}")) {
			try {
				setValue(new Date(simpleDateFormat.parse(text).getTime()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else if (text != null && !text.trim().isEmpty()) {
			setValue(text);
		}
	}
}
