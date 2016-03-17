package com.cmcc.slience.solr;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.ModifiableSolrParams;

import com.cmcc.slience.solr.bean.SolrTestBean;

public class HelloSolr {

	public static void insert(){
		HttpSolrClient server = new HttpSolrClient("http://localhost:9090/solr/collection");
		try {
			for (int i = 0; i < 1000; ++i) {
				SolrInputDocument doc = new SolrInputDocument();
				doc.addField("cat", "book");
				doc.addField("id", "book-" + i);
				doc.addField("name", "The Legend of Po part " + i);
				server.add(doc);
				if (i % 100 == 0)
					server.commit(); // periodically flush
			}
			server.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void select(){
		HttpSolrClient solr = new HttpSolrClient("http://localhost:9090/solr/collection");
		 
		ModifiableSolrParams params = new ModifiableSolrParams();
		//�ؼ���
		params.set("q", "book");
		params.set("defType", "edismax");
		//��ҳ����ǰҳ����ÿҳ��ʾ��Ŀ
		params.set("start", "0");
		//Ĭ��10
		params.set("rows", "10");
		//����
		params.set("sort", "id desc");
		//Ҫ��ʾ���ֶ�
		params.set("fl", "id,name");
		//Ĭ�ϼ�����field��text��ȫ��
		params.set("df", "cat");
		
 
		QueryResponse response = null;
		try {
			response = solr.query(params);
		} catch (SolrServerException | IOException e) {
			e.printStackTrace();
			return;
		}
		SolrDocumentList results = response.getResults();
		List<SolrTestBean> beans = response.getBeans(SolrTestBean.class);
		System.out.println(beans.size());
		for (int i = 0; i < results.size(); ++i) {
			System.out.println(results.get(i));
		}
	}
	
	
	public static void main(String[] args) {
		//insert();
		select();
	}
}