package model.response;

public class ServerResponse {
	private String responseMessage;
	private boolean isError;
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	public boolean isError() {
		return isError;
	}
	public void setError(boolean isError) {
		this.isError = isError;
	}
	
	public ServerResponse(){
		this.isError = false;
		
	}
}
