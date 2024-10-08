package dto;

public class IdeaDTO {
	
	private String ideaNum = null;
	private String title = null;
	private String text = null;
	private String ueserName = null;
	private String indate = null;
	
	
	@Override
	public String toString() {
		return "IdeaDTO [ideaNum=" + ideaNum + ", title=" + title + ", text=" + text + ", ueserName=" + ueserName
				+ ", indate=" + indate + "]";
	}
	
	public String toString2() {
		return "IdeaDTO [ideaNum=" + ideaNum + ", title=" + title + "]";
	}
	
	
	
	
	public String getIdeaNum() {
		return ideaNum;
	}
	public void setIdeaNum(String ideaNum) {
		this.ideaNum = ideaNum;
	}
	public String getIndate() {
		return indate;
	}
	public void setIndate(String indate) {
		this.indate = indate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getUeserName() {
		return ueserName;
	}
	public void setUeserName(String ueserName) {
		this.ueserName = ueserName;
	}

}
