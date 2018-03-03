<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript">
	function checkForm(){
		var ddTypeId = $('#ddTypeName').val();
		if(ddTypeId == null || ddTypeId == ""){
			$('#error').text("数据字典名称不能为空!");
			return false;
		}
		return true;
	}
</script>

<div class="row">
	<div class="col-md-12">
		<div class="data_list">
			<form class="form-horizontal" action="ddType!save" method="post" onsubmit="return checkForm()">
				<div class="form-group">
					<div class="col-sm-2"></div>
					<label class="col-sm-2 control-label">数据字典名称:</label>
					<div class="col-sm-4">
					 	<input type="text" class="form-control" id="ddTypeName" name="dataDicType.ddTypeName" value="${dataDicType.ddTypeName }" >
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2"></div>
					<label class="col-sm-2 control-label">数据字典备注:</label>
					<div class="col-sm-4">
					 	<textarea class="form-control" rows="5" id="ddTypeDesc" name="dataDicType.ddTypeDesc" >${dataDicType.ddTypeDesc }</textarea>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2"></div>
					<div class="col-sm-1">
						<input type="hidden" class="form-control" id="ddTypeId" name="ddTypeId" value="${dataDicType.ddTypeId}">
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