package com.ssafy.happyhouse.model;

public class GuestBookDto {
	
	private int articleno;
	private String userid;
	private String subject;
	private String content;
	private String regtime;
	
	

	@Override
	public String toString() {
		return "GuestBookDto [articleno=" + articleno + ", userid=" + userid + ", subject=" + subject + ", content="
				+ content + ", regtime=" + regtime + "]";
	}

	public int getArticleno() {
		return articleno;
	}

	public void setArticleno(int articleno) {
		this.articleno = articleno;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegtime() {
		return regtime;
	}

	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}

}
