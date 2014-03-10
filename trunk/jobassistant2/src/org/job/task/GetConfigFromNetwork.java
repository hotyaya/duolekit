package org.job.task;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.commons.net.ftp.FTPClient;

public class GetConfigFromNetwork {
	static String localfilename = "./dw.tmp";
	Properties netProperties = null;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GetConfigFromNetwork df = new GetConfigFromNetwork();
		df.download();
		df.read(localfilename);
		df.deleteFile(localfilename);
	}
	
	public void execute(){
		download();
		read(localfilename);
		deleteFile(localfilename);
	}

	private void download() {
		FTPClient ftpclient = new FTPClient();
		FileOutputStream fos = null;
		try {
			ftpclient.connect("10.64.2.116");
			ftpclient.login("sgdd", "sgdd");
			//File file = new File("d:" + File.separator + "dw.tmp");
			File file = new File(localfilename);
			// ftpclient.changeWorkingDirectory("/");
			ftpclient.setBufferSize(1024);
			ftpclient.setControlEncoding("UTF-8");
			ftpclient.setFileType(FTPClient.BINARY_FILE_TYPE);
			fos = new FileOutputStream(file);
			ftpclient.retrieveFile("dbgwhelper.cfg", fos);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void read(String filename) {
		InputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(new File(filename)));
			netProperties = new Properties();
			netProperties.load(in);
			Enumeration<Object> enu = netProperties.keys();
			while (enu.hasMoreElements()) {
				String key = enu.nextElement().toString().trim();
				//System.out.println(key + ":" + netProperties.getProperty(key));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

    /**
     * 删除单个文件
     * @param   sPath    被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public boolean deleteFile(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }

	public Properties getNetProperties() {
		return netProperties;
	}
}
