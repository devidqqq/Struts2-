<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript">
	function checkForm(){
		var ddValue = $('#ddValue').val();
		if(ddValue == null || ddValue == ""){
			$('#error').text("数据字典名称不能为空!");
			return false;
		}
		return true;
	}
</script>

<div class="row">
	<div class="col-md-12">
		<div class="data_list">
			<form class="form-horizontal" action="dataDic!save" method="post" onsubmit="return checkForm()">
				<div class="form-group">
					<div class="col-sm-2"></div>
					<label class="col-sm-2 control-label">数据字典类别名称:</label>
					<div class="col-sm-4">
					 	<select class="form-control" name="dataDic.ddTypeId">
					 		<option value="">请选择数据字典类别...</option>
					 		<c:forEach var="dataDicType" items="${dataDicTypeList }" varStatus="status">
					 			<option value="${dataDicType.ddTypeId }" ${dataDicType.ddTypeId==dataDic.ddTypeId?'selected':'' }>${dataDicType.ddTypeName }</option>
					 		</c:forEach>
					 	</select>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2"></div>
					<label class="col-sm-2 control-label">数据字典值:</label>
					<div class="col-sm-4">
					 	<input type="text" class="form-control" id="ddValue" name="dataDic.ddValue" value="${dataDic.ddValue }" >
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2"></div>
					<label class="col-sm-2 control-label">数据字典备注:</label>
					<div class="col-sm-4">
					 	<textarea class="form-control" rows="5" id="ddDesc" name="dataDic.ddDesc" >${dataDic.ddDesc }</textarea>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2"></div>
					<div class="col-sm-1">
						<input type="hidden" class="form-control" id="ddId" name="ddId" value="${dataDic.ddId}">
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