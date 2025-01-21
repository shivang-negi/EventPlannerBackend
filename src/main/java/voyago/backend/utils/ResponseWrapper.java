package voyago.backend.utils;

public class ResponseWrapper<T> {
	private Status status;
	private String message;
	private T data;
	
	public ResponseWrapper() {}
	
	public ResponseWrapper(Status status, String message, T data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}
	
	public ResponseWrapper(Status status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
