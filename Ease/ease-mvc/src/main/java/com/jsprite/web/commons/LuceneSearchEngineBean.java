/**
 * File Name:LuceneUtil.java
 * @Description: 
 * Copyright 2012 China Life Insurance Company Ltd.
 * @author:ZRB
 * @version:v1.0
 * Createdate:2015年5月7日下午4:01:13
 */
package com.jsprite.web.commons;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.LogByteSizeMergePolicy;
import org.apache.lucene.index.LogMergePolicy;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopFieldDocs;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.TokenSources;
import org.apache.lucene.search.similarities.DefaultSimilarity;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.util.Version;

import com.jsprite.web.utils.AnalyzerUtil;

/**
 * 描述：<br>
 * 作者：ZRB <br>
 * 修改日期：2015年5月7日下午4:01:13 <br>
 * E-mail: <br>
 */
public class LuceneSearchEngineBean {

	private Logger logger = Logger.getLogger(LuceneSearchEngineBean.class);

	private Integer totalNum = 0;

	//设置索引位置
	private String indexPath = "lucene-index";
	
	//设置数据类的class名称
	private String luceneDataClass;
	
	//使用自定义排序
	private boolean useCustomSorter = false;
	
	//排序字段及其排序规则
	private List<Map<String, Object>> sorterMapList;
	
	//默认只返回前100条记录
	private int returnSize = 100;
	
	//步长
	private int step = 0;
	
	private ServletContext servletContext;
	
	/**
	 * 创建索引
	 * 
	 * @param data
	 *  要放入索引的一条记录
	 * @return
	 */
	public synchronized boolean createIndex(BaseLuceneModel data) {
		IndexWriter indexWriter = null;
		Directory d = null;
		try {
			d = FSDirectory.open(new File(indexPath));
			IndexWriterConfig conf = new IndexWriterConfig(Version.LATEST, AnalyzerUtil.getIkAnalyzer());
			// 3.6以后不推荐用optimize,使用LogMergePolicy优化策略
			conf.setMergePolicy(optimizeIndex());
			// 创建索引模式：CREATE，覆盖模式； APPEND，追加模式
			File file = new File(indexPath);
			File[] f = file.listFiles();
			if (f.length == 0)
				conf.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
			else
				conf.setOpenMode(IndexWriterConfig.OpenMode.APPEND);

			indexWriter = new IndexWriter(d, conf);
			// 因为id是唯一的，如果之前存在就先删除原来的，在创建新的
			Term term = new Term("id", data.getId());
			indexWriter.deleteDocuments(term);

			Document doc = getDocument(data);
			indexWriter.addDocument(doc);

			logger.debug("索引结束,共有索引{"+indexWriter.numDocs()+"}个");
			indexWriter.commit();
			return true;
		} catch (CorruptIndexException e) {
			e.printStackTrace();
			logger.error("索引添加异常", e);
		} catch (LockObtainFailedException e) {
			e.printStackTrace();
			logger.error("索引添加异常", e);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("索引不存在", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("索引添加异常", e);
		} finally {
			if (indexWriter != null) {
				try {
					indexWriter.close();
				} catch (CorruptIndexException e) {
					e.printStackTrace();
					logger.error("索引关闭异常", e);
				} catch (IOException e) {
					e.printStackTrace();
					logger.error("索引关闭异常", e);
				}
			}
		}
		return false;
	}

	/**
	 * 更新索引
	 * 
	 * @param data
	 * @return
	 */
	public boolean updateIndex(BaseLuceneModel data) {
		IndexWriter indexWriter = null;
		Directory d = null;
		try {
			d = FSDirectory.open(new File(indexPath));
			while (d != null && IndexWriter.isLocked(d)) {// 如果文件锁住,等待解锁
				Thread.sleep(1000);
				logger.error("索引已经锁住，正在等待....");
			}
			IndexWriterConfig conf = new IndexWriterConfig(Version.LATEST, AnalyzerUtil.getIkAnalyzer());
			// 3.6以后不推荐用optimize,使用LogMergePolicy优化策略
			conf.setMergePolicy(optimizeIndex());

			indexWriter = new IndexWriter(d, conf);
			Term term = new Term("id", data.getId());
			// 不管更新与否，先删除原来的
			indexWriter.deleteDocuments(term);

			Document doc = getDocument(data);
			indexWriter.addDocument(doc);
			indexWriter.commit();
			logger.debug("更新索引，文章ID为{"+data.getId()+"}");
			logger.debug("共有索引{"+indexWriter.numDocs()+"}个");
			return true;
		} catch (CorruptIndexException e) {
			e.printStackTrace();
			logger.error("索引添加异常", e);
		} catch (LockObtainFailedException e) {
			e.printStackTrace();
			logger.error("索引添加异常", e);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("索引不存在", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("索引添加异常", e);
		} finally {
			if (indexWriter != null) {
				try {
					indexWriter.close();
				} catch (CorruptIndexException e) {
					e.printStackTrace();
					logger.error("索引关闭异常", e);
				} catch (IOException e) {
					e.printStackTrace();
					logger.error("索引关闭异常", e);
				}
			}
		}
		return false;
	}

	/**
	 * 根据id删除索引（id对应的那条document）
	 * @param id document的id
	 * @return
	 */
	public boolean deleteIndex(String id) {
		IndexWriter indexWriter = null;
		Directory d = null;
		try {
			d = FSDirectory.open(new File(indexPath));
			while (d != null && IndexWriter.isLocked(d)) {// 如果文件锁住,等待解锁
				Thread.sleep(1000);
				logger.error("索引已经锁住，正在等待....");
			}
			IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LATEST, AnalyzerUtil.getIkAnalyzer());
			indexWriter = new IndexWriter(d, indexWriterConfig);
			Term term = new Term("id", id);
			indexWriter.deleteDocuments(term);
			indexWriter.commit();
			logger.debug("删除文章ID:{}的索引..." + id);
			logger.debug("共有索引{}个" + indexWriter.numDocs());
			indexWriter.close();
			return true;
		} catch (CorruptIndexException e) {
			e.printStackTrace();
			logger.error("索引删除异常", e);
		} catch (LockObtainFailedException e) {
			e.printStackTrace();
			logger.error("索引删除异常", e);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("索引不存在", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("索引删除异常", e);
		} finally {
			if (indexWriter != null) {
				try {
					indexWriter.close();
				} catch (CorruptIndexException e) {
					e.printStackTrace();
					logger.error("索引关闭异常", e);
				} catch (IOException e) {
					e.printStackTrace();
					logger.error("索引关闭异常", e);
				}
			}
		}
		return false;
	}

	/**
	 * @param fileds 要查询的综合字段 ex【 new String[]{ "contentTitle", 
	 * 				 "contentContext","keywords"};】
	 * @param occurs
	 *  要查询的字段出现可能 ex【new Occur[] { Occur.SHOULD,
	 *  Occur.SHOULD,Occur.SHOULD };】
	 * @param keyWord 要查询的关键字
	 * @param page 当前页
	 * @param pageSize 分页数
	 * @return
	 */
	public ArrayList<BaseLuceneModel> search(String[] fileds, Occur[] occurs, String keyWord, Integer page, Integer pageSize) {
		return search(fileds, occurs, keyWord, "", "", page, pageSize);
	}

	/**
	 * @param fileds
	 *  要查询的综合字段 ex【 new String[]{ "contentTitle",
	 *  "contentContext","keywords"};】
	 * @param occurs
	 *  要查询的字段出现可能 ex【new Occur[] { Occur.SHOULD,
	 *  Occur.SHOULD,Occur.SHOULD };】
	 * @param keyWord
	 *   要查询的关键字
	 * @param subType
	 *   主类型
	 * @param type
	 *   主类型下的子类型
	 * @param page
	 *   当前页
	 * @param pageSize
	 *   分页数
	 * @return
	 */
	public ArrayList<BaseLuceneModel> search(String[] fileds, Occur[] occurs, String keyWord, String bigtype, String subType, Integer page, Integer pageSize) {
		try {
			
			IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(indexPath)));
			IndexSearcher searcher = new IndexSearcher(reader);
			
			//使用默认打分规则
			searcher.setSimilarity(new DefaultSimilarity());
			
			//QueryParser作用是把查询表达式转换为lucene内置的查询类型
			BooleanQuery booleanquery = new BooleanQuery();
			for(int i=0; i<fileds.length; i++){
				Term term = new Term(fileds[i], keyWord);
				TermQuery termQuery = new TermQuery(term);
				booleanquery.add(termQuery, occurs[i]);
			}

			if (!StringUtils.isEmpty(bigtype)) {
				Term term = new Term("bigtype", bigtype);
				TermQuery termQuery = new TermQuery(term);
				booleanquery.add(termQuery, Occur.MUST);
			}
			
			if (!StringUtils.isEmpty(subType)) {
				Term term = new Term("type", subType);
				TermQuery termQuery = new TermQuery(term);
				booleanquery.add(termQuery, Occur.MUST);
			}

			ScoreDoc[] docs = null;
			if(!useCustomSorter && this.sorterMapList!=null && this.sorterMapList.size()>0){
				if(page*pageSize>getReturnSize()){
					this.returnSize += this.step;
				}
				Sort sort = createMultipartSort(sorterMapList);
				TopFieldDocs tDocs =  searcher.search(booleanquery, getReturnSize(), sort);
				totalNum = tDocs.totalHits;
				ScoreDoc[] tempDocs = tDocs.scoreDocs;
				int pageStartIndex = (page - 1) * pageSize;
				int pageEndIndex = pageStartIndex + pageSize-1;
				docs = new ScoreDoc[pageSize];
				for(int i=pageStartIndex; i<=pageEndIndex; i++){
					docs[i-pageStartIndex] = tempDocs[i];
				}
			}else{
				//默认是按照相关度排好序的
				// 分页查询,lucene不支持分页查询，因为查询速度很快，所以我们就设置查询上限
				TopScoreDocCollector topCollector = TopScoreDocCollector.create(page * pageSize, true);// 上限
				searcher.search(booleanquery, topCollector);
				// 查询结果的总数量
				totalNum = topCollector.getTotalHits();
				// 返回所需数据
				docs = topCollector.topDocs((page - 1) * pageSize, pageSize).scoreDocs;
			}

			// 高亮显示
			SimpleHTMLFormatter simpleHtmlFormatter = new SimpleHTMLFormatter("<font color='red'>", "</font>");
			Highlighter highlighter = new Highlighter(simpleHtmlFormatter, new QueryScorer(booleanquery));
			highlighter.setTextFragmenter(new SimpleFragmenter(100));

			ArrayList<BaseLuceneModel> list = new ArrayList<BaseLuceneModel>();
			BaseLuceneModel data = null;
			for (ScoreDoc scdoc : docs) {
				Document document = searcher.doc(scdoc.doc);
				data = (BaseLuceneModel) Class.forName(luceneDataClass).newInstance();
				Field[] fields = Class.forName(luceneDataClass).getDeclaredFields();
				for(Field field : fields){
					String fieldName = field.getName();
					// 设置高壳
					TokenStream tokenStream = null;
					String name = document.get(fieldName);
					tokenStream = TokenSources.getAnyTokenStream(searcher.getIndexReader(), scdoc.doc, "name", AnalyzerUtil.getIkAnalyzer());
					String tempName = highlighter.getBestFragment(tokenStream, name);
					field.setAccessible(true);
					if (tempName != null){
						field.set(data, tempName);
					}else{
						field.set(data, name);
					}
				}
				list.add(data);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("搜索异常", e);
			return new ArrayList<BaseLuceneModel>();
		}
	}

	private Sort createMultipartSort(List<Map<String, Object>> list){
		//多字段排序，设置在前面的会优先排序 
		//true:降序 false:升序
		int size = list.size();
		SortField[] sortFields = new SortField[size];
		for(int index=0; index<size; index++){
			Map<String, Object> map = list.get(index);
			SortField field = new SortField((String)map.get("fieldName"), (SortField.Type)map.get("type"), (Boolean)map.get("isDesc"));
			sortFields[index] = field;
		}
		Sort sort = new Sort(sortFields);
		return sort;
	}
	
	/**
	 * 把传入的数据类型转换成Document
	 * @param data
	 * @return
	 */
	private Document getDocument(BaseLuceneModel data) {
		Document doc = new Document();
		Field[] fields = data.getClass().getDeclaredFields();
		for(Field field : fields){
			String fieldName = field.getName();
			field.setAccessible(true);
			String fieldValue = null;
			try {
				fieldValue = (String) field.get(data);
			} catch (Exception e) {
				logger.warn("获取Lucene对象属性["+fieldName+"]值失败，已跳过……");
				fieldValue = "";
			}
			doc.add(new TextField(fieldName, fieldValue, Store.YES));
		}
		return doc;
	}

	/**
	 * 优化索引，返回优化策略
	 * @return
	 */
	private LogMergePolicy optimizeIndex() {
		LogMergePolicy mergePolicy = new LogByteSizeMergePolicy();
		// 设置segment添加文档(Document)时的合并频率
		// 值较小,建立索引的速度就较慢
		// 值较大,建立索引的速度就较快,>10适合批量建立索引
		// 达到50个文件时就和合并
		mergePolicy.setMergeFactor(50);
		// 设置segment最大合并文档(Document)数
		// 值较小有利于追加索引的速度
		// 值较大,适合批量建立索引和更快的搜索
		mergePolicy.setMaxMergeDocs(5000);
		// 启用复合式索引文件格式,合并多个segment
		mergePolicy.setCalibrateSizeByDeletes(true);
		return mergePolicy;
	}
	
	/**
	 * 方法名称: getTermTotal<br>
	 * 描述：获取查询结果总数，需要在search方法调用完后调用<br/>
	 * 作者: ZRB<br/>
	 * 修改日期：2015年5月8日上午11:43:02<br/>
	 * @return
	 */
	public Integer getTermTotal(){
		return this.totalNum;
	}

	public void setIndexPath(String indexPath) {
		if(!StringUtils.isEmpty(indexPath)){
			String basePath = (String) servletContext.getAttribute("basepath");
			if(!basePath.endsWith("/") && !basePath.endsWith("\\")){
				basePath += File.separator;
			}
			this.indexPath = basePath + indexPath;
		}
	}

	public void setLuceneDataClass(String luceneDataClass) {
		this.luceneDataClass = luceneDataClass;
	}

	public void setUseCustomSorter(boolean useCustomSorter) {
		this.useCustomSorter = useCustomSorter;
	}

	public void setSorterMapList(List<Map<String, Object>> sorterMapList) {
		this.sorterMapList = sorterMapList;
	}

	public int getReturnSize() {
		return returnSize;
	}

	public void setReturnSize(int returnSize) {
		this.step = returnSize;
		this.returnSize = returnSize;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
}
