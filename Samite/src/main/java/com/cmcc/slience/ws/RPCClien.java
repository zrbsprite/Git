package com.cmcc.slience.ws;

import java.net.MalformedURLException;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.soap.SOAPException;

import org.apache.axis.client.Call;
import org.apache.axis.message.MessageElement;
import org.apache.axis.message.SOAPHeaderElement;
import org.apache.axis2.AxisFault;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

/**
 * 娴嬭瘯寰俊鏁板瓧id鐨剋ebservice
 * @author Administrator
 *
 */
public class RPCClien {
	public static void main(String[] args) {
		try {
			RPCServiceClient serviceClient = new RPCServiceClient();
			Options options = serviceClient.getOptions();
			EndpointReference targetEPR = new EndpointReference("http://localhost:8080/services/WXDgid?q.wsdl");
			options.setTo(targetEPR);
			Object[] op = new Object[]{"123","123"};
			Class[] clazz = new Class[]{String.class}; 
			QName opAddEntry = new QName("http://www.ab95569.com/services/WXDgid", "test");
			System.out.println(serviceClient.invokeBlocking(opAddEntry, op, clazz)[0]);
		} catch (AxisFault e) {
			e.printStackTrace();
		}
	}
	
	public static void wsClient(){
		String url = "";
		String mServParameter = "XMLInputString";
		String namespace = "";
		String localPort = "";
		try {
			Call client = new Call(url);
			client.addParameter(mServParameter, Constants.XSD_STRING, ParameterMode.IN);
			
			SOAPHeaderElement header = new SOAPHeaderElement(namespace, localPort); 
			MessageElement messageElement = new MessageElement("","");
			messageElement.setObjectValue("");
			header.addChildElement(messageElement);
			
			client.setReturnType(Constants.XSD_STRING);
			
			String mOutStr = (String) client.invoke(namespace, "", new String[]{ "" });
			System.out.println(mOutStr);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (org.apache.axis.AxisFault e) {
			e.printStackTrace();
		}
	}
}