package net.sf.seesea.rendering.chart.editpart;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

public class DownloadCacheTilesCommand extends Command {

	private final String url;
	private final long id;

	public DownloadCacheTilesCommand(long id, String url) {
		this.id = id;
		this.url = url;
	}
	
	@Override
	public void execute() {
		IProgressService progressService = PlatformUI.getWorkbench().getProgressService();
		try {
			progressService.run(true, true, new IRunnableWithProgress() {
				
				public void run(IProgressMonitor monitor) throws InvocationTargetException,
						InterruptedException {
					HttpClient httpClient = new HttpClient();
					try {
						GetMethod method = new GetMethod(url);
						int executeMethod = httpClient.executeMethod(method);
						long fileSize = method.getResponseContentLength();
						ZipInputStream zis = new ZipInputStream(method.getResponseBodyAsStream());
						ZipEntry entry;
						while ((entry = zis.getNextEntry()) != null) {
			                System.out.println("Unzipping: " + entry.getName());
			 
			                int size;
			                byte[] buffer = new byte[16384];
			 
			                // FIXME: base cache directory
//			                new File()
			                FileOutputStream fos = new FileOutputStream(entry.getName());
			                BufferedOutputStream bos = new BufferedOutputStream(fos, buffer.length);
			 
			                if(monitor.isCanceled()) {
				                bos.flush();
				                bos.close();
				                return;
			                }
			                while ((size = zis.read(buffer, 0, buffer.length)) != -1) {
			                    bos.write(buffer, 0, size);
			                }
			                bos.flush();
			                bos.close();
			            }
					} catch (HttpException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
			});
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
