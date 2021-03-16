package org.spring.board.dto;

public class LikeUserDTO {
	private String gubun; //1:게시글, 2:댓글
	private int num;
	private String userid;
	private String likegubun; //0:기본, 1:좋아요, 2:싫어요
	public LikeUserDTO() {
		super();
	}
	public LikeUserDTO(String gubun, int num, String userid, String likegubun) {
		super();
		this.gubun = gubun;
		this.num = num;
		this.userid = userid;
		this.likegubun = likegubun;
	}
	public String getGubun() {
		return gubun;
	}
	public void setGubun(String gubun) {
		this.gubun = gubun;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getLikegubun() {
		return likegubun;
	}
	public void setLikegubun(String likegubun) {
		this.likegubun = likegubun;
	}
	@Override
	public String toString() {
		return "LikeUserDTO [gubun=" + gubun + ", num=" + num + ", userid=" + userid + ", likegubun=" + likegubun + "]";
	}
	
	
	
}
