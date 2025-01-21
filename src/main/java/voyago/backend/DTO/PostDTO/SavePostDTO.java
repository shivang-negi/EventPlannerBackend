package voyago.backend.DTO.PostDTO;

public class SavePostDTO {
	private String userID;
	private String postContent;
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getPostContent() {
		return postContent;
	}
	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}
	
}
