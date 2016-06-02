<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page session="false" %> <!-- TODO: Why need it??? -->

<%@ include file="../include/header.jsp" %>

<!-- Main content -->
<section class="content">
	<div class="row">
		<!-- left column -->
		<div class="col-md-12">
			<!-- general form elements -->
			<div class="box">
				<div class="box-header">
					<h3 class="box-title">BOARD LIST ALL PAGE</h3>
				</div> <!-- /.box-header -->
				
				<div class="box-body">
				
				    <table class="table table-bordered">
				        <tr>
				            <th style="width: 10px">BNO</th>
				            <th>TITLE</th>
				            <th>WRITER</th>
				            <th>REGDATE</th>
				            <th style="width: 40px">VIEWCNT</th>
				        </tr>
				        
				        <c:forEach var="boardVO" items="${list}">
				        <tr>
				            <td>${boardVO.bno}</td>
				            <td><a href='<c:url value="/board/read?bno=${boardVO.bno}" />'>${boardVO.title}</a></td>
				            <td>${boardVO.writer}</td>
				            <td><fmt:formatDate value="${boardVO.regdate}" 
				                    pattern="yyyy-MM-dd HH:mm"/></td>
				            <td><span class="badge bg-red">${boardVO.viewcnt}</span></td>
				        </tr>
				        </c:forEach>
				        
				    </table>
				
				</div> <!-- /.box-body -->
				
				<div class="box-footer">Footer</div> <!-- /.box-footer -->
				
			</div> <!-- /.box -->
		</div> <!--/.col (left) -->

	</div> <!-- /.row -->
</section> <!-- /.content -->

<%@ include file="../include/footer.jsp" %>

<script type="text/javascript">
var result = "${result}";
if (result == "success") {
	alert("처리가 완료되었습니다.");
}
</script>