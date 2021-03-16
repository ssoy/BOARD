package org.spring.board.dto;

public class ReplyDTO {
	private int rnum;
	private int bnum;
	private String userid;
	private String content;
	private int likecnt;
	private int dislikecnt;
	private String ip;
	private int restep;
	private int relevel;
	private String regdate;
	private String modifydate;
	public ReplyDTO() {
		super();
	}
	public ReplyDTO(int rnum, int bnum, String userid, String content, int likecnt, int dislikecnt, String ip,
			int restep, int relevel, String regdate, String modifydate) {
		super();
		this.rnum = rnum;
		this.bnum = bnum;
		this.userid = userid;
		this.content = content;
		this.likecnt = likecnt;
		this.dislikecnt = dislikecnt;
		this.ip = ip;
		this.restep = restep;
		this.relevel = relevel;
		this.regdate = regdate;
		this.modifydate = modifydate;
	}
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	public int getBnum() {
		return bnum;
	}
	public void setBnum(int bnum) {
		this.bnum = bnum;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getLikecnt() {
		return likecnt;
	}
	public void setLikecnt(int likecnt) {
		this.likecnt = likecnt;
	}
	public int getDislikecnt() {
		return dislikecnt;
	}
	public void setDislikecnt(int dislikecnt) {
		this.dislikecnt = dislikecnt;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getRestep() {
		return restep;
	}
	public void setRestep(int restep) {
		this.restep = restep;
	}
	public int getRelevel() {
		return relevel;
	}
	public void setRelevel(int relevel) {
		this.relevel = relevel;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getModifydate() {
		return modifydate;
	}
	public void setModifydate(String modifydate) {
		this.modifydate = modifydate;
	}
	@Override
	public String toString() {
		return "ReplyDTO [rnum=" + rnum + ", bnum=" + bnum + ", userid=" + userid + ", content=" + content
				+ ", likecnt=" + likecnt + ", dislikecnt=" + dislikecnt + ", ip=" + ip + ", restep="
				+ restep + ", relevel=" + relevel + ", regdate=" + regdate + ", modifydate=" + modifydate + "]";
	}
	
	
}
