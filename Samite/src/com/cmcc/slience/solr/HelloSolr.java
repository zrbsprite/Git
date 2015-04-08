package com.cmcc.slience.solr;

import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.ModifiableSolrParams;

import com.cmcc.slience.solr.bean.SolrTestBean;

public class HelloSolr {

	public static void insert(){
		HttpSolrServer server = new HttpSolrServer("http://localhost:9090/solr/collection");
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
		HttpSolrServer solr = new HttpSolrServer("http://localhost:9090/solr/collection");
		 
		ModifiableSolrParams params = new ModifiableSolrParams();
		//关键词
		params.set("q", "book");
		params.set("defType", "edismax");
		//分页，当前页，和每页显示数目
		params.set("start", "0");
		//默认10
		params.set("rows", "10");
		//排序
		params.set("sort", "id desc");
		//要显示的字段
		params.set("fl", "id,name");
		//默认检索的field，text表全部
		params.set("df", "cat");
		
 
		QueryResponse response = null;
		try {
			response = solr.query(params);
		} catch (SolrServerException e) {
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