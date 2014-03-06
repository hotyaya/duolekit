package org.job.util;

import java.io.*;
public class IEPath {

  private static final String REGQUERY_UTIL = "reg query ";
  private static final String REGSTR_TOKEN = "REG_SZ";
  private static final String REGDWORD_TOKEN = "REG_DWORD";

  private static final String HTTP_DEFAULT_BROWSER = REGQUERY_UTIL 
		  +"\"HKEY_LOCAL_MACHINE\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\App Paths\\IEXPLORE.EXE\""
		  +"";

  public static String getIEpath() {
	    try {
	    	
	      Process process = Runtime.getRuntime().exec(HTTP_DEFAULT_BROWSER);
	      StreamReader reader = new StreamReader(process.getInputStream());
	      
	      reader.start();
	      process.waitFor();
	      reader.join();

	      String result = reader.getResult();
	      int p = result.indexOf(REGSTR_TOKEN);

	      if (p == -1)
	         return null;

	      String result2 =result.substring(p + REGSTR_TOKEN.length()).trim();
	      int p2 = result2.indexOf("IEXPLORE.EXE");
	      if (p2 < 0 )
	    	  return null;
	      return result2.substring(0, p2+13).trim();
	    }
	    catch (Exception e) {
	      return null;
	    }
	  }
  
  static class StreamReader extends Thread {
    private InputStream is;
    private StringWriter sw;

    StreamReader(InputStream is) {
      this.is = is;
      sw = new StringWriter();
    }

    public void run() {
      try {
        int c;
        while ((c = is.read()) != -1)
          sw.write(c);
        }
        catch (IOException e) { ; }
      }

    String getResult() {
      return sw.toString();
    }
  }

  public static void main(String s[]) {
    System.out.println("browser path : " + IEPath.getIEpath().replaceAll("\\\\", "//") + "");
  }
}


