package upload;

import javax.servlet.http.HttpSession;

import uk.ltd.getahead.dwr.WebContext;
import uk.ltd.getahead.dwr.WebContextFactory;

public class UploadProxy
{
  public UploadStatus getStatus()
  {
    WebContext webCtx = WebContextFactory.get();
    HttpSession session = webCtx.getSession();
    
    UploadStatus status = (UploadStatus)session.getAttribute("uploadStatus");
    if (status != null)
    {
      session.setAttribute("uploadStatus", null);
      System.out.println(session.getAttribute("FILES_UPLOADED"));
      System.out.println(session.getAttribute("fileUploadStats"));
      System.out.println(status.toString());
      return status;
    }
    FileUploadStats fileUploadStats = (FileUploadStats)session.getAttribute("fileUploadStats");
    
    status = new UploadStatus();
    if (fileUploadStats != null)
    {
      long bytesProcessed = fileUploadStats.getBytesRead();
      long sizeTotal = fileUploadStats.getTotalSize();
      long percentComplete = (long) Math.floor(bytesProcessed / sizeTotal * 100.0D);
      long timeInSeconds = fileUploadStats.getElapsedTimeInSeconds();
      double uploadRate = bytesProcessed / (timeInSeconds + 1.0E-5D);
      double estimatedRuntime = sizeTotal / (uploadRate + 1.0E-5D);
      if (fileUploadStats.getBytesRead() < fileUploadStats.getTotalSize())
      {
        status.setStatus(1);
        status.setBytesProcessed(bytesProcessed);
        status.setEstimatedRuntime(estimatedRuntime);
        status.setPercentComplete(percentComplete);
        status.setSizeTotal(sizeTotal);
        status.setTimeInSeconds(timeInSeconds);
        status.setUploadRate(uploadRate);
      }
      else
      {
        status.setStatus(2);
      }
      if ((fileUploadStats != null) && (fileUploadStats.getBytesRead() == fileUploadStats.getTotalSize())) {
        status.setStatus(2);
      }
    }
    else
    {
      status.setStatus(4);
    }
    return status;
  }
}

