package org.spring.board.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

public class MemberDTO {
	private String userid;
	private String passwd;
	private String email;
	private String zip;
	private String addr1;
	private String addr2;
	private String filename;
	private String thumbnail; //썸네일이름	
	private String admin;
	private String emailauth;
	private String simplejoin;
	private String regdate;
	private MultipartFile imgfile; //파일
	
	public MemberDTO() {
		super();
	}

	public MemberDTO(String userid, String passwd, String email, String zip, String addr1, String addr2,
			String filename, String thumbnail, String admin, String emailauth, String simplejoin, String regdate,
			MultipartFile imgfile) {
		super();
		this.userid = userid;
		this.passwd = passwd;
		this.email = email;
		this.zip = zip;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.filename = filename;
		this.thumbnail = thumbnail;
		this.admin = admin;
		this.emailauth = emailauth;
		this.simplejoin = simplejoin;
		this.regdate = regdate;
		this.imgfile = imgfile;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
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

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public String getEmailauth() {
		return emailauth;
	}

	public void setEmailauth(String emailauth) {
		this.emailauth = emailauth;
	}

	public String getSimplejoin() {
		return simplejoin;
	}

	public void setSimplejoin(String simplejoin) {
		this.simplejoin = simplejoin;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public MultipartFile getImgfile() {
		return imgfile;
	}

	public void setImgfile(MultipartFile imgfile) {
		this.imgfile = imgfile;
	}

	@Override
	public String toString() {
		return "MemberDTO [userid=" + userid + ", passwd=" + passwd + ", email=" + email + ", zip=" + zip + ", addr1="
				+ addr1 + ", addr2=" + addr2 + ", filename=" + filename + ", thumbnail=" + thumbnail + ", admin="
				+ admin + ", emailauth=" + emailauth + ", simplejoin=" + simplejoin + ", regdate=" + regdate
				+ ", imgfile=" + imgfile + "]";
	}


	
}
