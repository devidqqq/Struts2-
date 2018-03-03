<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript">
function deletegrade(gradeId){
	if(confirm('您确定要删除这条数据吗？')){
		$.post('grade!delete',{gradeId:gradeId},function(result){
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
				<button type="button" class="btn btn-primary btn-sm btn-right" onclick="javascript:window.location='grade!preSave'">添加年级</button>
			</div>
			<div class="data_content">
				<table class="table table-bordered table-hover">
					<tr>
						<th>序号</th>
						<th>年级名称</th>
						<th>年级备注</th>
						<th>操作</th>
					</tr>
					<c:forEach var="grade" items="${gradeList }" varStatus="status">
						<tr>
							<td>${status.index+1 }</td>
							<td>${grade.gradeName }</td>
							<td>${grade.gradeDesc }</td>
							<td>
								<button type="button" class="btn btn-info btn-sm" onclick="javascript:window.location='grade!preSave?gradeId=${grade.gradeId}'">修改</button>
								<button type="button" class="btn btn-danger btn-sm" onclick="deletegrade(${grade.gradeId})">删除</button>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</div>