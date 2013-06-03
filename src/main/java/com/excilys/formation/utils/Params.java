package com.excilys.formation.utils;

public class Params {
	private String name;
	private String introduced;
	private String discontinued;
	private String companyName;
	private String nameHeader;
	private String introducedHeader;
	private String discontinuedHeader;
	private String companyNameHeader;
	private String prevUrl;
	private String prevDisabled;
	private String nextUrl;
	private String nextDisabled;

	public Params() {
		this.name = "";
		this.introduced = "?s=introduced";
		this.discontinued = "?s=discontinued";
		this.companyName = "?s=company.name";
		this.prevUrl = "href=\"computers?";
		this.nextUrl = "href=\"computers?";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void concatName(String name) {
		this.name += name;
	}

	public String getIntroduced() {
		return introduced;
	}

	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}

	public void concatIntroduced(String introduced) {
		this.introduced += introduced;
	}

	public String getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}

	public void concatDiscontinued(String discontinued) {
		this.discontinued += discontinued;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public void concatCompanyName(String companyName) {
		this.companyName += companyName;
	}

	public String getNameHeader() {
		return nameHeader;
	}

	public void setNameHeader(String nameHeader) {
		this.nameHeader = nameHeader;
	}

	public String getIntroducedHeader() {
		return introducedHeader;
	}

	public void setIntroducedHeader(String introducedHeader) {
		this.introducedHeader = introducedHeader;
	}

	public String getDiscontinuedHeader() {
		return discontinuedHeader;
	}

	public void setDiscontinuedHeader(String discontinuedHeader) {
		this.discontinuedHeader = discontinuedHeader;
	}

	public String getCompanyNameHeader() {
		return companyNameHeader;
	}

	public void setCompanyNameHeader(String companyNameHeader) {
		this.companyNameHeader = companyNameHeader;
	}

	public String getPrevUrl() {
		return prevUrl;
	}

	public void setPrevUrl(String prevUrl) {
		this.prevUrl = prevUrl;
	}

	public void concatPrevUrl(String prevUrl) {
		this.prevUrl += prevUrl;
	}

	public String getNextUrl() {
		return nextUrl;
	}

	public void setNextUrl(String nextUrl) {
		this.nextUrl = nextUrl;
	}

	public void concatNextUrl(String nextUrl) {
		this.nextUrl += nextUrl;
	}

	public String getPrevDisabled() {
		return prevDisabled;
	}

	public void setPrevDisabled(String prevDisabled) {
		this.prevDisabled = prevDisabled;
	}

	public String getNextDisabled() {
		return nextDisabled;
	}

	public void setNextDisabled(String nextDisabled) {
		this.nextDisabled = nextDisabled;
	}

	@Override
	public String toString() {
		return "Param [name=" + name + ", introduced=" + introduced
				+ ", discontinued=" + discontinued + ", companyName="
				+ companyName + ", nameHeader=" + nameHeader
				+ ", introducedHeader=" + introducedHeader
				+ ", discontinuedHeader=" + discontinuedHeader
				+ ", companyNameHeader=" + companyNameHeader + ", prevUrl="
				+ prevUrl + ", prevDisabled=" + prevDisabled + ", nextUrl="
				+ nextUrl + ", nextDisabled=" + nextDisabled + "]";
	}

}
