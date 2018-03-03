<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<script type="text/javascript">
function checkForm(){
	var stuNo = $("#stuNo").val();
	var stuName = $("#stuName").val();
	var stuSex = $("#stuSex").val();
	var stuNation = $("#stuNation").val();
	var stuZzmm = $("#zzmm").val();
	var classId = $("#classId").val();
	var stuBirthday = $("#bb").val();
	var stuRxsj = $("#br").val();
	alert(stuNo);
	alert(stuName);
	alert(stuNation);
	alert(stuSex);
	alert(classId);
	alert(stuBirthday);
	alert(stuRxsj);
	alert(stuZzmm);
	if(stuNo==null||stuNo==""){
		$("#error").html("学号不能为空！");
		return false;
	}
	if(stuName==null||stuName==""){
		$("#error").html("姓名不能为空！");
		return false;
	}
	if(stuSex==null||stuSex==""){
		$("#error").html("请选择性别！");
		return false;
	}
	if(stuNation==null||stuNation==""){
		$("#error").html("请选择名族！");
		return false;
	}
	if(stuZzmm==null||stuZzmm==""){
		$("#error").html("请选择政治面貌！");
		return false;
	}
	if(stuBirthday==null||stuBirthday==""){
		$("#error").html("出生日期不能为空！");
		return false;
	}
	if(stuRxsj==null||stuRxsj==""){
		$("#error").html("入学时间不能为空！");
		return false;
	}
	if(classId==null||classId==""){
		$("#error").html("请选择班级！");
		return false;
	}
	return true;
}
</script>
<div class="row">
	<div class="col-sm-offset-1 col-md-9">
		<div class="data_list">
			<form class="form-horizontal" action="student!save" method="post" enctype="multipart/form-data" onsubmit="return checkForm()">
				<div class="form-group">
					<label class="col-sm-2 control-label">学号：</label>
					<div class="col-sm-4">
					 	<input type="text" class="form-control" id="stuNo" name="student.stuNo" value="${student.stuNo }"/>
					</div>
					<label class="col-sm-2 control-label">姓名：</label>
					<div class="col-sm-4">
					 	<input type="text" class="form-control" id="stuName" name="student.stuName" value="${student.stuName }"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">性别：</label>
					<div class="col-sm-4">
					 	<select class="form-control" id="stuSex" name="student.stuSex">
					 		<option value="">请选择性别...</option>
					 		<c:forEach var="sex" items="${sexlist }" varStatus="status">
					 			<option value="${sex.ddValue }" ${sex.ddValue==student.stuSex?'selected':'' }>${sex.ddValue }</option>
					 		</c:forEach>
					 	</select>
					</div>
					<label class="col-sm-2 control-label">民族：</label>
					<div class="col-sm-4">
					 	<select class="form-control" id="stuNation" name="student.stuNation">
					 		<option value="">请选择民族...</option>
					 		<c:forEach var="nation" items="${nationlist }" varStatus="status">
					 			<option value="${nation.ddValue }" ${nation.ddValue==student.stuNation?'selected':'' }>${nation.ddValue }</option>
					 		</c:forEach>
					 	</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">政治面貌：</label>
					<div class="col-sm-4">
					 	<select class="form-control" id="zzmm" name="student.stuZzmm">
					 		<option value="">请选择政治面貌...</option>
					 		<c:forEach var="zzmm" items="${zzmmlist }" varStatus="status">
					 			<option value="${zzmm.ddValue }" ${zzmm.ddValue==student.stuZzmm?'selected':'' }>${zzmm.ddValue }</option>
					 		</c:forEach>
					 	</select>
					</div>
					<label class="col-sm-2 control-label">所在班级：</label>
					<div class="col-sm-4">
					 	<select class="form-control" id="classId" name="student.classId">
					 		<option value="">请选择所在班级...</option>
					 		<c:forEach var="c" items="${classList }">
								<option value="${c.classId }" ${student.classId==c.classId?'selected':'' }>${c.className }</option>
							</c:forEach>
					 	</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">出生日期：</label>
					<div class="col-sm-4">
					 	<input type="text" id="bb" class="form-control Wdate" onclick="WdatePicker()" style="border: #ccc 1px solid; height: 34px;" name="student.stuBirthday" value="<fmt:formatDate value="${student.stuBirthday }" type="date" pattern="yyyy-MM-dd" />"/>
					</div>
					<label class="col-sm-2 control-label">入学时间：</label>
					<div class="col-sm-4">
					 	<input type="text" id="br" class="form-control Wdate" onclick="WdatePicker()" style="border: #ccc 1px solid; height: 34px;" name="student.stuRxsj" value="<fmt:formatDate value="${student.stuRxsj }" type="date" pattern="yyyy-MM-dd"/>"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">个人照片：</label>
					<div class="col-sm-10">
					 	<input type="file" id="stuPic" name="uploadFile" />
					 	<input type="hidden" id="stuId" name="student.studentId" value="${student.stuPic }"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">备注：</label>
					<div class="col-sm-10">
					 	<textarea rows="5" class="form-control" id="stuDesc" name="student.stuDesc">${student.stuDesc }</textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">
						<input type="hidden" id="stuId" name="studentId" value="${student.studentId }"/>
					</label>
					<div class="col-sm-offset-6 col-sm-11">
						<button type="submit" class="btn btn-primary">保存</button>
						<button type="button" class="btn " onclick="javascript:history.back()">返回</button>&nbsp;&nbsp;&nbsp;&nbsp;<font id="error" color="red"></font>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>