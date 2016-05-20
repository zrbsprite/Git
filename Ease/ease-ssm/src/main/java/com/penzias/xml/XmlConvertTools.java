package com.penzias.xml;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


public class XmlConvertTools {

	private String encoding="UTF-8";
	
	@SuppressWarnings("unchecked")
    public <T> T convertXml2Bean(Class<T> clazz, String xml) throws IOException, JAXBException {
        InputStream is = null;
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            is = new ByteArrayInputStream(xml.getBytes("UTF-8"));
            return (T) unmarshaller.unmarshal(is);
        } catch (IOException e) {
            throw e;
        } catch (JAXBException e) {
            throw e;
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }
	
	
	public <T> String convertObj2Xml(T bean) throws JAXBException, IOException {
		StringWriter writer = new StringWriter();
		try{
			JAXBContext context = JAXBContext.newInstance(bean.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);  
			marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);  
			marshaller.marshal(bean, writer);
			writer.flush();
			return writer.toString(); 
		} catch (JAXBException e){
			e.printStackTrace();
			throw e;
		}finally{
			writer.close();
		}
	}
	
	public String getEncoding(){
	
		return encoding;
	}
	
	public void setEncoding(String encoding){
	
		this.encoding = encoding;
	}
}
