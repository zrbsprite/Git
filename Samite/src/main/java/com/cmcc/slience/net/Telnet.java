package com.cmcc.slience.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author ruibo
 *	测试端口是否开放
 */
public class Telnet {

	public static void main(String[] args) {
		String str = args[0];
		int i = Integer.valueOf(args[1]).intValue();
		Socket localSocket = null;
		try {
			localSocket = new Socket();
			InetSocketAddress localInetSocketAddress = new InetSocketAddress(str, i);
			localSocket.connect(localInetSocketAddress, 5000);
			System.out.println("telnet successful");
		} catch (IOException localException2) {
			System.out.println("telnet fail");
		} finally {
			if (localSocket != null)
				try {
					localSocket.close();
				} catch (Exception e) {
					 e.printStackTrace();
				}
		}
	}
}
