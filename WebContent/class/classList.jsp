<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript">
function deleteClass(classId){
	if(confirm('您确定要删除这条数据吗？')){
		$.post('class!delete',{classId:classId},function(result){
				if(result.success){
					alert(result.message);
					location.reload();//重新加载页面
				}else{
					alert(result.error);
				}
			},'json'
		);
	}
}
</script>
<div class="row">
	<div class="col-md-12">
		<div class="data_list">
			<div class="data_content">
				<form class="form-inline" action="class!classList" method="post">
					<div class="form-group">
					    <label>年级名称：</label>
					    <input type="text" class="form-control" id="gradeName" name="gradeName" value="${gradeName }">
					</div>
					<button type="submit" class="btn btn-primary">查询</button>
					<button type="button" class="btn btn-primary btn-right" onclick="javascript:window.location='class!preSave'">添加班级</button>
				</form>
			</div>
			<div class="data_content">
				<table class="table table-bordered table-hover">
					<tr>
						<th>序号</th>
						<th>班级名称</th>
						<th>所在年级</th>
						<th>班级备注</th>
						<th>操作</th>
					</tr>
					<c:forEach var="sclass" items="${sclassList }" varStatus="status">
						<tr>
							<td>${status.index+1+(page-1)*8 }</td>
							<td>${sclass.className }</td>
							<td>${sclass.gradeName }</td>
							<td>${sclass.classDesc }</td>
							<td>
								<button type="button" class="btn btn-info btn-sm" onclick="javascript:window.location='class!preSave?classId=${sclass.classId}'">修改</button>
								<button type="button" class="btn btn-danger btn-sm" onclick="deleteClass(${sclass.classId})">删除</button>
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