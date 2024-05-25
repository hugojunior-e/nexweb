package upload;

public class UploadStatus
{
  public static final int STATUS_IN_PROGRESS = 1;
  public static final int STATUS_OK = 2;
  public static final int STATUS_ERROR = 3;
  public static final int STATUS_RETRY = 4;
  private int status;
  private String message;
  private long bytesProcessed;
  private long sizeTotal;
  private long percentComplete;
  private double uploadRate;
  private long timeInSeconds;
  private double estimatedRuntime;
  
  public int getStatus()
  {
    return this.status;
  }
  
  public void setStatus(int status)
  {
    this.status = status;
  }
  
  public String getMessage()
  {
    return this.message;
  }
  
  public void setMessage(String message)
  {
    this.message = message;
  }
  
  public long getBytesProcessed()
  {
    return this.bytesProcessed;
  }
  
  public void setBytesProcessed(long bytesProcessed)
  {
    this.bytesProcessed = bytesProcessed;
  }
  
  public long getSizeTotal()
  {
    return this.sizeTotal;
  }
  
  public void setSizeTotal(long sizeTotal)
  {
    this.sizeTotal = sizeTotal;
  }
  
  public long getPercentComplete()
  {
    return this.percentComplete;
  }
  
  public void setPercentComplete(long percentComplete)
  {
    this.percentComplete = percentComplete;
  }
  
  public double getUploadRate()
  {
    return this.uploadRate;
  }
  
  public void setUploadRate(double uploadRate)
  {
    this.uploadRate = uploadRate;
  }
  
  public long getTimeInSeconds()
  {
    return this.timeInSeconds;
  }
  
  public void setTimeInSeconds(long timeInSeconds)
  {
    this.timeInSeconds = timeInSeconds;
  }
  
  public double getEstimatedRuntime()
  {
    return this.estimatedRuntime;
  }
  
  public void setEstimatedRuntime(double estimatedRuntime)
  {
    this.estimatedRuntime = estimatedRuntime;
  }

@Override
public String toString() {
	return "UploadStatus [status=" + status + ", message=" + message + ", bytesProcessed=" + bytesProcessed
			+ ", sizeTotal=" + sizeTotal + ", percentComplete=" + percentComplete + ", uploadRate=" + uploadRate
			+ ", timeInSeconds=" + timeInSeconds + ", estimatedRuntime=" + estimatedRuntime + "]";
}
}

