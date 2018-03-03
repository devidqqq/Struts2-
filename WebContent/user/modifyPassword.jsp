<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript">
	function checkForm(){
		var password = '${currentUser.password}';
		var userId = '${currentUser.userId}';
		var oldpassword = $('#oldpassword').val();
		var newpassword = $('#newpassword').val();
		var confirmpassword = $('#confirmpassword').val();
		if(oldpassword == null || oldpassword == ""){
			$('#error').text("请输入原始密码!");
			return false;
		}
		if(password!=oldpassword){
			$('#error').text("原始密码不一致，请重新输入!");
			return false;
		}
		if(newpassword == null || newpassword == ""){
			$('#error').text("请输入新密码!");
			return false;
		}
		if(confirmpassword == null || confirmpassword == ""){
			$('#error').text("请确认密码!");
			return false;
		}
		if(newpassword!=confirmpassword){
			$('#error').text("原始密码不一致，请重新输入!");
			return false;
		}
		
		$.post('user!update',{userId:userId,password:newpassword},
			function(result){
				if(result.success){
					alert('修改成功,请重新登陆！');
					window.location.href='${pageContext.request.contextPath}/login.jsp';
				}else{
					alert("result.errorMsg");
			}
		},'json');
	}
</script>

<div class="row">
	<div class="col-md-12">
		<div class="data_list">
			<form class="form-horizontal">
				<div class="form-group">
					<div class="col-sm-2"></div>
					<label class="col-sm-2 control-label">管理员:</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" id="userName" name="user.userName" value="${currentUser.userName }" readonly="readonly">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2"></div>
					<label class="col-sm-2 control-label">旧密码:</label>
					<div class="col-sm-4">
					 	<input type="password" class="form-control" id="oldpassword">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2"></div>
					<label class="col-sm-2 control-label">新密码:</label>
					<div class="col-sm-4">
					 	<input type="password" class="form-control" id="newpassword">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2"></div>
					<label class="col-sm-2 control-label">确认密码:</label>
					<div class="col-sm-4">
					 	<input type="password" class="form-control" id="confirmpassword">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-5 col-sm-8">
						<button type="button" class="btn btn-primary" onclick="checkForm()">提交</button>&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="btn btn-default" onclick="javascript:history.back()">返回</button>&nbsp;&nbsp;&nbsp;&nbsp;<font color="red" id="error"></font>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>