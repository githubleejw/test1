package com.jsp.dto;

import java.util.Date;

public class NoticeVO {
	
	private int nno; //게시판 번호
	private String title=""; //제목
	private String writer; //작성자(회원)
	private String content=""; //내용(html)
	private int viwecnt=0; //조회수
	private Date regDate=new Date(); //등록날짜
	
	public int getNno() {
		return nno;
	}
	public void setNno(int nno) {
		this.nno = nno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getViwecnt() {
		return viwecnt;
	}
	public void setViwecnt(int viwecnt) {
		this.viwecnt = viwecnt;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	

}
