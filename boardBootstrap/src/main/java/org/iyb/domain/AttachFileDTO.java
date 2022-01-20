package org.iyb.domain;//model  

public class AttachFileDTO {
	private String fileName;//파일 이름
	private String uploadPath;//파일 경로
	private String uuid;//랜덤값 : 기본키
	private boolean image;//true
	private int bno;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getUploadPath() {
		return uploadPath;
	}
	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public boolean isImage() {
		return image;
	}
	public void setImage(boolean image) {
		this.image = image;
	}
	
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	@Override
	public String toString() {
		return "AttachFileDTO [fileName=" + fileName + ", uploadPath=" + uploadPath + ", uuid=" + uuid + ", image="
				+ image + ", bno=" + bno + "]";
	}
}
