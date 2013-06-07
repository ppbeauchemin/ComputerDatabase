package com.excilys.formation.utils;

public abstract class ParamConverter {

	public static String getStringParameterOrDefault(Object parameter, String defaut) {
		String s = defaut;
		if (parameter != null) {
			s = (String) parameter;
		}
		return s;
	}
	
	public static Integer getIntParameterOrDefault(Object parameter, int defaut) {
		Integer i = defaut;
		if (parameter != null) {
			i = Integer.parseInt((String) parameter);
		}
		return i;
	}
	
	public static void setAllParams(Params params, int p, String s, String o, String f) {
		params.concatPrevUrl("p=" + (p-1));
		params.concatNextUrl("p=" + (p+1));

		if (o.equals("asc")) {
			if (s.equals("computer.name")) {
				params.concatName("?o=desc");
				params.setNameHeader("headerSortUp");
			} else if (s.equals("introduced")) {
				params.concatIntroduced("&o=desc");
				params.setIntroducedHeader("headerSortUp");
				params.concatPrevUrl("&s=" + s);
				params.concatNextUrl("&s=" + s);
			} else if (s.equals("discontinued")) {
				params.concatDiscontinued("&o=desc");
				params.setDiscontinuedHeader("headerSortUp");
				params.concatPrevUrl("&s=" + s);
				params.concatNextUrl("&s=" + s);
			} else if (s.equals("company.name")) {
				params.concatCompanyName("&o=desc");
				params.setCompanyNameHeader("headerSortUp");
				params.concatPrevUrl("&s=" + s);
				params.concatNextUrl("&s=" + s);
			}
		} else if (o.equals("desc")) {
			params.concatPrevUrl("&o=" + o);
			params.concatNextUrl("&o=" + o);
			if (s.equals("computer.name")) {
				params.setNameHeader("headerSortDown");
			} else if (s.equals("introduced")) {
				params.setIntroducedHeader("headerSortDown");
			} else if (s.equals("discontinued")) {
				params.setDiscontinuedHeader("headerSortDown");
			} else if (s.equals("company.name")) {
				params.setCompanyNameHeader("headerSortDown");
			}
		}

		if (!f.isEmpty()) {
			if (o.equals("asc") && s.equals("computer.name")) {
				params.concatName("&f=" + f);
			} else {
				params.concatName("?f=" + f);
			}
			params.concatIntroduced("&f=" + f);
			params.concatDiscontinued("&f=" + f);
			params.concatCompanyName("&f=" + f);
			params.concatPrevUrl("&f=" + f);
			params.concatNextUrl("&f=" + f);
		}
	}

}
