package com.cmcc.slience.solr;

import java.io.File;
import java.io.IOException;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.AbstractUpdateRequest;
import org.apache.solr.client.solrj.request.ContentStreamUpdateRequest;
import org.apache.solr.client.solrj.response.QueryResponse;

/**
 * ��PDF��������
 * @author  Administrator
 * @version  [�汾��, 2014��3��18��]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class CreateIndexFromPdf {

	public static void main(String[] args)
    {
        String fileName = "D:\\JavaSoftHome\\solr\\File\\JM.pdf"; 
        String solrId = "JM.pdf"; 
        try
        {
            indexFilesSolrCell(fileName, solrId);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (SolrServerException e)
        {
            e.printStackTrace();
        }
        
    }
    
    /** ���ļ���������
     * <������ϸ����>
     * @param fileName
     * @param solrId
     * @see [�ࡢ��#��������#��Ա]
     */
    public static void indexFilesSolrCell(String fileName, String solrId) 
        throws IOException, SolrServerException
    {
        String urlString = "http://localhost:9090/solr/core-file-index";
        HttpSolrClient solr = new HttpSolrClient(urlString);
        ContentStreamUpdateRequest up = new ContentStreamUpdateRequest("/update/extract");
        
        String contentType="application/pdf";
        up.addFile(new File(fileName), contentType);
        up.setParam("literal.id", solrId);
        up.setParam("uprefix", "attr_");
        up.setParam("fmap.content", "attr_content");
        up.setAction(AbstractUpdateRequest.ACTION.COMMIT, true, true);     
        solr.request(up);
        QueryResponse rsp = solr.query(new SolrQuery("*:*"));
        System.out.println(rsp);
    }
}