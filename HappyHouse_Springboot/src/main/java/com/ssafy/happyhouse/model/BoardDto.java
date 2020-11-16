package com.ssafy.happyhouse.model;

public class BoardDto {
	
	private int boardno;
	private String userid;
	private String subject;
	private String content;
	private String regtime;
	
	
	
	@Override
	public String toString() {
		return "BoardDto [boardno=" + boardno + ", userid=" + userid + ", subject=" + subject + ", content=" + content
				+ ", regtime=" + regtime + "]";
	}
	
	public int getBoardno() {
		return boardno;
	}
	public void setBoardno(int boardno) {
		this.boardno = boardno;
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
