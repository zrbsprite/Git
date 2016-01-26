package com.penzias.datatransfer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.framework.util.DESEncryptUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.penzias.datatransfer.db.DBTool;
import com.penzias.datatransfer.model.DataSourceMainModel;


/**
 * 描述：数据处理start<br>
 * 作者：ZRB <br>
 */
public class App {
	
	private static Logger logger = LoggerFactory.getLogger(App.class);
	
	private static final String ENCRYPT_KEY = "U2FsdGVkX19Fcum6/spC9RBqB41+jVWmLEFU4CyZoWn077DYvFKrUHKFodO4Q54hX9zSxevF";
	
	private static String[] keyArray = new String[]{"A.","B.","C.","D.","E.","F.","G.","H.","I.","J.","K.","L.","M.","N.","O.","P.",
			"Q.","R.","S.","T.","U.","V.","W.","X.","Y.","Z."};
	
	public static void main(String[] args) {

		final String splitor = "@@"; 
		Connection connection = DBTool.getConnection();
		try {
			connection.setAutoCommit(false);
			String srcTableCountSql = "select count(Examid) from tblexam";
			QueryRunner runner = new QueryRunner();
			int count = ((Long)runner.query(connection, srcTableCountSql, new ScalarHandler<Long>(1))).intValue();
			int pernum = 1000;
			int totalPage = (count+1) / pernum;
			for(int index=1;index<totalPage;index++){
				int start = pernum * (index - 1);
				String srcTableListSql = "select * from tblexam limit " + start + "," + pernum;
				List<DataSourceMainModel> list = (List<DataSourceMainModel>) runner.query(connection,srcTableListSql, new BeanListHandler<DataSourceMainModel>(DataSourceMainModel.class));
				Object[][] params = new Object[list.size()][];
				int i = 0;
				for(DataSourceMainModel model : list){
					float diff = getDifficult(model.getExamdiff());
					String selectContent = model.getExamsolu();
					String[] scArray = selectContent.split(splitor);
					selectContent = "";
					int m = 0;
					for(String str : scArray){
						selectContent += keyArray[m] + str;
						m++;
					}
					m = 0;
					selectContent = DESEncryptUtil.encrypt(selectContent, ENCRYPT_KEY);
					String correctKey = model.getTruesolu();
					int examType = model.getExamtype().intValue();
					if(15==examType||18==examType||25==examType||27==examType||33==examType){
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
					}
					correctKey = DESEncryptUtil.encrypt(correctKey, ENCRYPT_KEY);
					params[i] = new Object[]{model.getExamid(),model.getExamsubject(),model.getExamtype(), model.getExamimage(), model.getExamcontent1(),
							1, new Date(), diff, 0, selectContent, correctKey, 0, 0, '0', '0'};
					i++;
				}
				String batchInsertSql = "insert into exam_item(item_id, subject_id, type_id, item_content, creator_id, version, p_value, pump_times,"
						+ " correct_key, selected_content, test_times, test_correct_times, have_patient, item_flag) values(?,?,?,?,?,?,?,?,?,?,?,?)";
				int[] effectRow = runner.batch(connection, batchInsertSql, params);
				logger.info("批量导入数据"+index+"结果是：" + effectRow.toString());
			}
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally{
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
}
