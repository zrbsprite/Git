//д��ע�ͣ�ͨ����������д��
//����ע��
//����ע��
//����ע��
//����ע��
//����ע��
//����ע��
function showInfo(){
	alert("�����˰�ť");
}
function compareDate(dateLeft,dateRight){
	var strLefts = dateLeft.split("-");
	var strRights = dateRight.split("-");
	
	dateLeft = new Date(strLefts[0],strLefts[1],strLefts[2]);
	dateRight = new Date(strRights[0],strRights[1],strRights[2]);
	
	return dateRight > dateLeft;
}