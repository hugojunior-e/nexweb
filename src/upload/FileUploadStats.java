package upload;

public class FileUploadStats {
	private long totalSize = 0L;
	private long bytesRead = 0L;
	private long startTime = System.currentTimeMillis();
	private String currentStatus = "none";

	public long getTotalSize() {
		return this.totalSize;
	}

	public void setTotalSize(long totalSize) {
		this.totalSize = totalSize;
	}

	public long getBytesRead() {
		return this.bytesRead;
	}

	public long getElapsedTimeInSeconds() {
		return (System.currentTimeMillis() - this.startTime) / 1000L;
	}

	public String getCurrentStatus() {
		return this.currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public void setBytesRead(long bytesRead) {
		this.bytesRead = bytesRead;
	}

	public void incrementBytesRead(int byteCount) {
		this.bytesRead += byteCount;
	}

	public String toString() {
		return "FileUploadStats{totalSize=" + this.totalSize + ", bytesRead=" + this.bytesRead + ", startTime="
				+ this.startTime + ", currentStatus='" + this.currentStatus + '\'' + '}';
	}
}
