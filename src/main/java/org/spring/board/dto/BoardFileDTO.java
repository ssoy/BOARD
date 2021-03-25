package org.spring.board.dto;

public class BoardFileDTO {
	private int fnum;
	private int bnum;
	private String filename;  //원본파일이름
	private String thumbnail; //썸네일이름
	private String regdate;
	public BoardFileDTO() {
		super();
	}
	public BoardFileDTO(int fnum, int bnum, String filename, String thumbnail, String regdate) {
		super();
		this.fnum = fnum;
		this.bnum = bnum;
		this.filename = filename;
		this.thumbnail = thumbnail;
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
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "BoardFileDTO [fnum=" + fnum + ", bnum=" + bnum + ", filename=" + filename + ", thumbnail=" + thumbnail
				+ ", regdate=" + regdate + "]";
	}
	
	
}
