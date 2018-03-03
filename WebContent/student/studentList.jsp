<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script type="text/javascript">
function deleteStudent(studentId){
	if(confirm('您确定要删除这条数据吗？')){
		$.post('student!delete',{studentId:studentId},function(result){
				if(result.success){
					alert(result.message);
					location.reload();//重新加载页面
				}else{
					alert("删除失败！");
				}
			},'json'
		);
	}
}

function loadClassInfo(){
	var gradeName=$("#gradeName").val();
	$("#classId option[value!='-1']").remove();
	if(gradeName=="" || gradeName==null){
		$("#classId").val("-1");
		return;
	}
	$.post("class!getClassByGradeName",{gradeName:gradeName},
		function(result){
			$("#classId").val("-1");
			var result=eval("("+result+")");
			$.each(result,function(i,item){
				$("<option value='"+item.classId+"'>"+item.className+"</option>").appendTo($("#classId"));
			});
		}		
	);
}
function resetValue(){
	$("#stuNo").val("");
	$("#stuName").val("");
	$("#stuSex").val("-1");
	$("#stuNation").val("-1");
	$("#gradeName").val("");
	$("#classId").val("-1");
	$("#bb").val("");
	$("#eb").val("");
	$("#er").val("");
	$("#br").val("");
}
</script>
<div class="row">
	<div class="col-sm-offset-1 col-md-9">
		<div class="data_list">
			<form class="form-horizontal" action="student!list" method="post">
				<div class="form-group">
					<label class="col-sm-2 control-label">学号：</label>
					<div class="col-sm-4">
					 	<input type="text" class="form-control" id="stuNo" name="s_student.stuNo" value="${s_student.stuNo }"/>
					</div>
					<label class="col-sm-2 control-label">姓名：</label>
					<div class="col-sm-4">
					 	<input type="text" class="form-control" id="stuName" name="s_student.stuName" value="${s_student.stuName }"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">性别：</label>
					<div class="col-sm-4">
					 	<select class="form-control" id="stuSex" name="s_student.stuSex">
					 		<option value="-1">请选择性别...</option>
					 		<c:forEach var="sex" items="${sexlist }" varStatus="status">
					 			<option value="${sex.ddValue }" ${sex.ddValue==s_student.stuSex?'selected':'' }>${sex.ddValue }</option>
					 		</c:forEach>
					 	</select>
					</div>
					<label class="col-sm-2 control-label">民族：</label>
					<div class="col-sm-4">
					 	<select class="form-control" id="stuNation" name="s_student.stuNation">
					 		<option value="-1">请选择民族...</option>
					 		<c:forEach var="nation" items="${nationlist }" varStatus="status">
					 			<option value="${nation.ddValue }" ${nation.ddValue==s_student.stuNation?'selected':'' }>${nation.ddValue }</option>
					 		</c:forEach>
					 	</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">所在年级：</label>
					<div class="col-sm-4">
					 	<select class="form-control" id="gradeName" name="s_student.gradeName" onchange="loadClassInfo()">
					 		<option value="">请选择所在年级...</option>
					 		<c:forEach var="grade" items="${gradeList }" varStatus="status">
					 			<option value="${grade.gradeName }" ${grade.gradeName==s_student.gradeName?'selected':'' }>${grade.gradeName }</option>
					 		</c:forEach>
					 	</select>
					</div>
					<label class="col-sm-2 control-label">所在班级：</label>
					<div class="col-sm-4">
					 	<select class="form-control" id="classId" name="s_student.classId">
					 		<option value="-1">请选择所在班级...</option>
					 		<c:forEach var="c" items="${sclassList }">
								<option value="${c.classId }" ${s_student.classId==c.classId?'selected':'' }>${c.className }</option>
							</c:forEach>
					 	</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">出生日期：</label>
					<div class="col-sm-4">
					 	<input type="text" id="bb" class="form-control Wdate" onclick="WdatePicker()" style="border: #ccc 1px solid; height: 34px;" name="s_student.bstuBirthday" value="${s_student.bstuBirthday }"/>
					</div>
					<label class="col-sm-1 control-label">到&nbsp;&nbsp;&nbsp;&nbsp;</label>
					<div class="col-sm-4">
					 	<input type="text" id="eb" class="form-control Wdate" onclick="WdatePicker()" style="border: #ccc 1px solid; height: 34px;" name="s_student.estuBirthday" value="${s_student.estuBirthday }"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">入学时间：</label>
					<div class="col-sm-4">
					 	<input type="text" id="br" class="form-control Wdate" onclick="WdatePicker()" style="border: #ccc 1px solid; height: 34px;" name="s_student.bRxsj" value="${s_student.bRxsj }"/>
					</div>
					<label class="col-sm-1 control-label">到&nbsp;&nbsp;&nbsp;&nbsp;</label>
					<div class="col-sm-4">
					 	<input type="text" id="er" class="form-control Wdate" onclick="WdatePicker()" style="border: #ccc 1px solid; height: 34px;" name="s_student.eRxsj" value="${s_student.eRxsj }"/>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-6 col-sm-11">
						<button type="submit" class="btn btn-primary">查找</button>
						<button type="button" class="btn " onclick="resetValue()">重置</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="col-md-12">
		<div class="data_list">
			<div class="data_content">
				<table class="table table-bordered table-hover">
					<tr>
						<th>序号</th>
						<th>学号</th>
						<th>姓名</th>
						<th>性别</th>
						<th>出生日期</th>
						<th>入学日期</th>
						<th>所在班级</th>
						<th>所属年级</th>
						<th>操作</th>
					</tr>
					<c:forEach var="student" items="${stuList }" varStatus="status">
						<tr>
							<td>${status.index+1 }</td>
							<td>${student.stuNo }</td>
							<td>${student.stuName }</td>
							<td>${student.stuSex }</td>
							<td><fmt:formatDate value="${student.stuBirthday }" type="date" pattern="yyyy-MM-dd" /></td>
							<td><fmt:formatDate value="${student.stuRxsj }" type="date" pattern="yyyy-MM-dd"/></td>
							<td>${student.className }</td>
							<td>${student.gradeName }</td>
							<td>
								<button type="button" class="btn btn-info btn-sm" onclick="javascript:window.location='student!allInfo?studentId=${student.studentId}'">查看详细信息</button>
								<button type="button" class="btn btn-info btn-sm" onclick="javascript:window.location='student!preSave?studentId=${student.studentId}'">修改</button>
								<button type="button" class="btn btn-danger btn-sm" onclick="deleteStudent(${student.studentId})">删除</button>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<!-- 分页 -->
			<div >
				<nav aria-label="Page navigation" class="pageCode">
  					<ul class="pagination">
  						${pageCode}
  					</ul>
				</nav>
			</div>
		</div>
	</div>
</div>