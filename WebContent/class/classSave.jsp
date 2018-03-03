<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript">
	function checkForm(){
		var className = $('#className').val();
		var gradeName = $('#gradeName').val();
		if(className == null || className == ""){
			$('#error').text("班级名称不能为空!");
			return false;
		}
		if(gradeName == null || gradeName == ""){
			$('#error').text("所在年级不能为空!");
			return false;
		}
		return true;
	}
</script>

<div class="row">
	<div class="col-md-12">
		<div class="data_list">
			<form class="form-horizontal" action="class!save" method="post" onsubmit="return checkForm()">
				<div class="form-group">
					<div class="col-sm-2"></div>
					<label class="col-sm-2 control-label">所在年级:</label>
					<div class="col-sm-4">
					 	<select class="form-control" id="gradeName" name="sclass.gradeId">
					 		<option value="">请选择所在年级...</option>
					 		<c:forEach var="grade" items="${gradeList }" varStatus="status">
					 			<option value="${grade.gradeId }" ${grade.gradeId==sclass.gradeId?'selected':'' }>${grade.gradeName }</option>
					 		</c:forEach>
					 	</select>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2"></div>
					<label class="col-sm-2 control-label">班级名称:</label>
					<div class="col-sm-4">
					 	<input type="text" class="form-control" id="className" name="sclass.className" value="${sclass.className }" >
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2"></div>
					<label class="col-sm-2 control-label">班级备注:</label>
					<div class="col-sm-4">
					 	<textarea class="form-control" rows="5" id="classDesc" name="sclass.classDesc" >${sclass.classDesc }</textarea>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2"></div>
					<div class="col-sm-1">
						<input type="hidden" class="form-control" id="classId" name="classId" value="${sclass.classId}">
					</div>
					<div class="col-sm-offset-1 col-sm-8">
						<button type="submit" class="btn btn-primary">保存</button>&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="btn btn-default" onclick="javascript:history.back()">返回</button>&nbsp;&nbsp;&nbsp;&nbsp;<font color="red" id="error"></font>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>