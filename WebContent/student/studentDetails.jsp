<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="row">
	<div class="col-md-9">
		<div class="data_list">
			<form class="form-horizontal">
				<div class="form-group">
					<label class="col-sm-2 control-label">学号:</label>
					<div class="col-sm-4">
					 	<input type="text" class="form-control" value="${student.stuNo }" readonly>
					</div>
					<label class="col-sm-2 control-label">姓名:</label>
					<div class="col-sm-4">
					 	<input type="text" class="form-control" value="${student.stuName }" readonly>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">性别:</label>
					<div class="col-sm-4">
					 	<input type="text" class="form-control" value="${student.stuSex }" readonly>
					</div>
					<label class="col-sm-2 control-label">出生日期:</label>
					<div class="col-sm-4">
					 	<input type="text" class="form-control" value="<fmt:formatDate value="${student.stuBirthday }" type="date" pattern="yyyy-MM-dd" />" readonly />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">民族:</label>
					<div class="col-sm-4">
					 	<input type="text" class="form-control" value="${student.stuNation }" readonly>
					</div>
					<label class="col-sm-2 control-label">政治面貌:</label>
					<div class="col-sm-4">
					 	<input type="text" class="form-control" value="${student.stuZzmm }" readonly>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">入学时间:</label>
					<div class="col-sm-4">
					 	<input type="text" class="form-control" value="<fmt:formatDate value="${student.stuRxsj }" type="date" pattern="yyyy-MM-dd"/>" readonly />
					</div>
					<label class="col-sm-2 control-label">所在班级:</label>
					<div class="col-sm-4">
					 	<input type="text" class="form-control" value="${student.className }" readonly>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">学生备注:</label>
					<div class="col-sm-10">
					 	<textarea class="form-control" rows="5" readonly>${student.stuDesc }</textarea>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-6 col-sm-12">
						<button type="button" class="btn btn-primary" onclick="javascript:history.back()">返回</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="col-md-3">
		<c:choose>
			<c:when test="${student.stuPic==null || student.stuPic==''}">
				<img src="${pageContext.request.contextPath }/stuImage/default.jpg" style="width: 260px;height: 310px;margin-top:10px;"/>
			</c:when>
			<c:otherwise>
				<img src="${pageContext.request.contextPath }/stuImage/${student.stuPic}" style="width: 260px;height: 310px;margin-top:10px;"/>
			</c:otherwise>
		</c:choose>
		<%-- <s:if test="${student.stuPic==null || student.stuPic==''}">
			<img src="${pageContext.request.contextPath }/stuImage/20140529102659.jpg" style="width: 260px;height: 310px;margin-top:10px;"/>
		</s:if>
		<s:else>
			<img src="${pageContext.request.contextPath }/stuImage/${student.stuPic}" style="width: 260px;height: 310px;margin-top:10px;"/>
		</s:else> --%>
	</div>
</div>