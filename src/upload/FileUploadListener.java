package upload;

import com.missiondata.fileupload.OutputStreamListener;

public class FileUploadListener implements OutputStreamListener {
	
	private FileUploadStats fileUploadStats = new FileUploadStats();

	public FileUploadListener(long totalSize) {
		this.fileUploadStats.setTotalSize(totalSize);
	}

	public void start() {
		this.fileUploadStats.setCurrentStatus("start");
	}

	public void bytesRead(int byteCount) {
		this.fileUploadStats.incrementBytesRead(byteCount);
		this.fileUploadStats.setCurrentStatus("reading");
	}

	public void error(String s) {
		this.fileUploadStats.setCurrentStatus("error");
	}

	public void done() {
		this.fileUploadStats.setBytesRead(this.fileUploadStats.getTotalSize());
		this.fileUploadStats.setCurrentStatus("done");
	}
    
	public FileUploadStats getFileUploadStats() {
		return this.fileUploadStats;
	}
	

	
}
