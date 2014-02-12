package org.job.task;

import jodd.mail.Email;
import jodd.mail.EmailMessage;
import jodd.mail.SendMailSession;
import jodd.mail.SmtpSslServer;
import jodd.util.MimeTypes;

public class MailProcess implements Runnable {

	public MailProcess(String info) {
		super();
		this.info = info;
	}

	private String info;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		mail(info);
	}

	private void mail(String text) {
		try{
			System.out.print("m");
			Email email = Email.create();
			EmailMessage textMessage = new EmailMessage(text, MimeTypes.MIME_TEXT_PLAIN);
			//email.subject("单位新文件提醒");
			email.addMessage(textMessage);
			//email.addText("收到电报如下：\n"+text);
			email.from("hotyaya@qq.com").to("hotyaya@126.com");
			email.subject("新文件 "+text);
			SendMailSession mailSession = new SmtpSslServer("smtp.qq.com","hotyaya@qq.com", "Bdesdk2759").createSession();
			mailSession.open();
			mailSession.sendMail(email);
			mailSession.close();
			if (text.equals("-")){
				System.out.print("-");
			}else{
				System.out.print("*发送mail成功!*");
			}
		}catch(Exception ex){
			System.out.print("x");
		}
	}
}
