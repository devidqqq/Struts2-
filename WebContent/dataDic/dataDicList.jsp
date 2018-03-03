<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript">
function deletedataDic(ddId){
	if(confirm('您确定要删除这条数据吗？')){
		$.post('dataDic!delete',{ddId:ddId},function(result){
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
</script>
<div class="row">
	<div class="col-md-12">
		<div class="data_list">
			<div class="data_content">
				<form class="form-inline" action="dataDic!dataDicList" method="post">
					<div class="form-group">
					    <label>数据字典类别名称：</label>
					    <input type="text" class="form-control" id="ddTypeName" name="ddTypeName" value="${ddTypeName }">
					</div>
					<button type="submit" class="btn btn-primary">查询</button>
					<button type="button" class="btn btn-primary btn-right" onclick="javascript:window.location='dataDic!preSave'">添加数据字典类别</button>
				</form>
			</div>
			<div class="data_content">
				<table class="table table-bordered table-hover">
					<tr>
						<th>序号</th>
						<th>数据字典类别名称</th>
						<th>数据字典值</th>
						<th>数据字典备注</th>
						<th>操作</th>
					</tr>
					<c:forEach var="dataDic" items="${dataDicList }" varStatus="status">
						<tr>
							<td>${status.index+1+(page-1)*8 }</td>
							<td>${dataDic.ddTypeName }</td>
							<td>${dataDic.ddValue }</td>
							<td>${dataDic.ddDesc }</td>
							<td>
								<button type="button" class="btn btn-info btn-sm" onclick="javascript:window.location='dataDic!preSave?ddId=${dataDic.ddId}'">修改</button>
								<button type="button" class="btn btn-danger btn-sm" onclick="deletedataDic(${dataDic.ddId})">删除</button>
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