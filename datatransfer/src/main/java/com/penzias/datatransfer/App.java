package com.penzias.datatransfer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang.StringUtils;
import org.framework.util.DESEncryptUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.penzias.datatransfer.db.DBTool;
import com.penzias.datatransfer.model.DataSourceMainModel;
import com.penzias.datatransfer.model.SubSrcModel;


/**
 * 描述：数据处理start<br>
 * 作者：ZRB <br>
 */
@SuppressWarnings("unused")
public class App {
	
	private static Logger logger = LoggerFactory.getLogger(App.class);
	
	private static final String ENCRYPT_KEY = "U2FsdGVkX19Fcum6/spC9RBqB41+jVWmLEFU4CyZoWn077DYvFKrUHKFodO4Q54hX9zSxevF";
	
	private static String[] keyArray = new String[]{"A.","B.","C.","D.","E.","F.","G.","H.","I.","J.","K.","L.","M.","N.","O.","P.",
			"Q.","R.","S.","T.","U.","V.","W.","X.","Y.","Z."};
	
	public static void main(String[] args) {
		logger.info("开始执行主表数据导入……");
		long mainStart = System.currentTimeMillis();
		importMianTableData();
		long mainEnd = System.currentTimeMillis();
		logger.info("执行主表数据导入结束……");
		logger.info("共耗时：" + (mainEnd - mainStart)/(1000f*60f) +"s");
		logger.info("开始执行子表数据导入……");
		long subStart = System.currentTimeMillis();
		importSubTableData();
		long subEnd = System.currentTimeMillis();
		logger.info("执行子表数据导入结束……");
		logger.info("共耗时：" + (subEnd - subStart)/(1000f*60f) +"s");
	}


	/**方法名称: importMianTableData<br>
	 * 描述：			<br/>
	 * 作者: ZRB<br/>
	 * 修改日期：2016年1月27日下午3:32:32<br/>
	 */
	private static void importMianTableData() {
		final String splitor = "@@"; 
		try {
			Connection countConnection = DBTool.getConnection();
			String srcTableCountSql = "select count(Examid) from tblexam";
			QueryRunner runner = new QueryRunner();
			int count = ((Long)runner.query(countConnection, srcTableCountSql, new ScalarHandler<Long>(1))).intValue();
			logger.info("主表数据总量是：" + count);
			countConnection.close();
			int pernum = 500;
			int totalPage = (count+1) / pernum;
			for(int index=1;index<totalPage;index++){
				logger.info("主表数据开始处理第【"+index+"】页数据……");
				int start = pernum * (index - 1);
				Connection selectConnection = DBTool.getConnection();
				String srcTableListSql = "select * from tblexam limit " + start + "," + pernum;
				List<DataSourceMainModel> list = (List<DataSourceMainModel>) runner.query(selectConnection,srcTableListSql, new BeanListHandler<DataSourceMainModel>(DataSourceMainModel.class));
				selectConnection.close();
				int size = list.size();
				logger.info("查出主表数据：" + size);
				Object[][] params = new Object[size][];
				int i = 0;
				for(DataSourceMainModel model : list){
					float diff = getDifficult(model.getExamdiff());
					String selectContent = model.getExamsolu();
					/*String[] scArray = selectContent.split(splitor);
					selectContent = "";
					int m = 0;
					for(String str : scArray){
						selectContent += keyArray[m] + str;
						m++;
					}
					m = 0;*/
					String correctKey = model.getTruesolu();
										int examType = model.getExamtype().intValue();
					//填空题
					if(28==examType||29==examType||30==examType||31==examType||34==examType){
						selectContent = "";
					}
					if(!StringUtils.isEmpty(selectContent)){
						selectContent = DESEncryptUtil.encrypt(selectContent, ENCRYPT_KEY);
					}
					/*if(15==examType||18==examType||25==examType||27==examType||33==examType){
						correctKey = correctKey.replaceAll(splitor, ",");
					}else if(28==examType||29==examType||30==examType||31==examType||34==examType){
						// do nothing
					}else if(26==examType){
						String[] ckArray = correctKey.split(splitor);
						correctKey = "";
						for(String str : ckArray){
							correctKey += keyArray[m] + str;
							m++;
						}
					}*/
					if(!StringUtils.isEmpty(correctKey)){
						correctKey = DESEncryptUtil.encrypt(correctKey, ENCRYPT_KEY);
					}
					params[i] = new Object[]{model.getExamid(),model.getExamsubject(),model.getExamtype(), model.getExamimage(), model.getExamcontent1(),
							1, new Date(), diff, 0, "2000-01-01 00:00:01", correctKey, selectContent, 0, 0, 0, 0, 1};
					i++;
				}
				String batchInsertSql = "insert into exam_item_temp(item_id, subject_id, type_id, item_image, item_content, creator_id, version, p_value, pump_times, lasted_pumpdate"
						+ " correct_key, selected_content, test_times, test_correct_times, have_patient, item_flag, status_id) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				/*String batchInsertSql = "insert into zrb_main(item_id, subject_id, type_id, item_image, item_content, creator_id, version, p_value, pump_times,"
						+ " correct_key, selected_content, test_times, test_correct_times, have_patient, item_flag) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";*/
				Connection insertConnection = DBTool.getConnection();
				insertConnection.setAutoCommit(false);
				try {
					runner.batch(insertConnection, batchInsertSql, params);
					insertConnection.commit();
				} catch (Exception e) {
					e.printStackTrace();
					insertConnection.rollback();
					logger.error("批量导入数据出错，系统自动退出！");
				}finally{
					insertConnection.close();
				}
			}
			logger.info("主表数据导入完毕，事务已经提交！");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	private static void importSubTableData(){
		//final String splitor = "@@"; 
		try {
			Connection countConnection = DBTool.getConnection();
			String srcTableCountSql = "select count(ExamsubID) from tblexamsub";
			QueryRunner runner = new QueryRunner();
			int count = ((Long)runner.query(countConnection, srcTableCountSql, new ScalarHandler<Long>(1))).intValue();
			countConnection.close();
			logger.info("查询子表数据总量关闭连接！");
			int pernum = 1;
			int totalPage = (count+1) / pernum;
			for(int index=1;index<totalPage;index++){
				int start = pernum * (index - 1);
				Connection selectConnection = DBTool.getConnection();
				String srcTableListSql = "select * from tblexamsub limit " + start + "," + pernum;
				List<SubSrcModel> list = (List<SubSrcModel>) runner.query(selectConnection, srcTableListSql, new BeanListHandler<SubSrcModel>(SubSrcModel.class));
				selectConnection.close();
				Object[][] params = new Object[list.size()][];
				int i = 0;
				for(SubSrcModel model : list){
					String correctSolu = model.getTrueSolu();
					if(null!=correctSolu){
						correctSolu = DESEncryptUtil.encrypt(correctSolu, ENCRYPT_KEY);
					}else{
						correctSolu = "";
					}
					String selectContent = model.getExamSolu();
					/*String[] scArray = selectContent.split(splitor);
					selectContent = "";
					int m = 0;
					for(String str : scArray){
						selectContent += keyArray[m] + str;
						m++;
					}*/
					if(!StringUtils.isEmpty(selectContent) && !selectContent.trim().equals("")){
						selectContent = DESEncryptUtil.encrypt(selectContent.trim(), ENCRYPT_KEY);
					}
					if(!StringUtils.isEmpty(selectContent) && !selectContent.trim().equals("")){
						selectContent = DESEncryptUtil.encrypt(selectContent.trim(), ENCRYPT_KEY);
					}
					params[i] = new Object[]{model.getExamSubId(), model.getExamId(), model.getExamContent(), correctSolu, selectContent};
					i++;
				}
				String batchInsertSql = "INSERT INTO exam_subitem_temp (subitem_id, item_id, sub_content, correct_key, select_item) VALUES (?,?,?,?,?)";
				//String batchInsertSql = "INSERT INTO zrb_sub (subitem_id, item_id, sub_content, correct_key, select_item) VALUES (?,?,?,?,?)";
				Connection batchInsertConnection = DBTool.getConnection();
				batchInsertConnection.setAutoCommit(false);
				runner.batch(batchInsertConnection, batchInsertSql, params);
				batchInsertConnection.commit();
				batchInsertConnection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static float getDifficult(Integer diff){
		if(null==diff)
			diff = 0;
		float result = 0f;
		switch (diff) {
		case 1:
			result = 1f;
			break;
		case 2:
			result = 0.8f;
			break;
		case 3:
			result = 0.6f;
			break;
		case 4:
			result = 0.4f;
			break;
		case 5:
			result = 0.2f;
			break;
		default:
			break;
		}
		return result;
	}
	
	private static void checkConstruction(){
		String src = "QO48BKctyHBsuH+v9PDId3llFhI/MqYcizhdIOmoTNoQ6yHxPF1e2h4gTi3H0VBRPjabbkPyeqxHCUe7gU0kQ13Ddtg2rCpuc0PlKCIAOayLHiqeiBM6jSTFBEira73lNXne7rGvEvQF4baJkWyxrcFvoZvydJG+x1T010zVclvi4Qi7unpUHrkU5G6eBqmpwb3lWcASkUw1PzrYCCJujFrvxCu3lrz9";
		String out = DESEncryptUtil.decrypt(src, ENCRYPT_KEY);
		System.out.println(out);
	}
	
	private static void checkAnswer(){
//		String src = "ceupNInVj5h+nIAUclkqYQ==";
		String src = "4SgjieHU11o5fgbUEkChpg==";
		String out = DESEncryptUtil.decrypt(src, ENCRYPT_KEY);
		System.out.println(out);
	}
}
