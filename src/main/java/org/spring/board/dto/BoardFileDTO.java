package org.spring.board.dto;

public class BoardFileDTO {
	private int fnum;
	private int bnum;
	private String filename;
	private String regdate;
	public BoardFileDTO() {
		super();
	}
	public BoardFileDTO(int fnum, int bnum, String filename, String regdate) {
		super();
		this.fnum = fnum;
		this.bnum = bnum;
		this.filename = filename;
		this.regdate = regdate;
	}
	public int getFnum() {
		return fnum;
	}
	public void setFnum(int fnum) {
		this.fnum = fnum;
	}
	public int getBnum() {
		return bnum;
	}
	public void setBnum(int bnum) {
		this.bnum = bnum;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "BoardFileDTO [fnum=" + fnum + ", bnum=" + bnum + ", filename=" + filename + ", regdate=" + regdate
				+ "]";
	}
	
	
}
