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
			<!-- Default box -->
			<div class="box">
			  <div class="box-header with-border">
			    <h3 class="box-title">Search Board</h3>
			  </div> <!-- /.box-header-->
			  <div class="box-body">
			     <select name="searchType">
			         <option value="n" 
			             <c:out value="${page.searchType == null ? 'selected':''}" />
			         >---</option>
			         <option value="t"
			             <c:out value="${page.searchType eq 't' ? 'selected':''}" />
			         >Title</option>
                     <option value="c"
                         <c:out value="${page.searchType eq 'c' ? 'selected':''}" />
                     >Content</option>
                     <option value="w"
                         <c:out value="${page.searchType eq 'w' ? 'selected':''}" />
                     >Writer</option>
                     <option value="tc"
                         <c:out value="${page.searchType eq 'tc' ? 'selected':''}" />
                     >Title OR Content</option>
                     <option value="cw"
                         <c:out value="${page.searchType eq 'cw' ? 'selected':''}" />
                     >Content OR Writer</option>
                     <option value="tcw"
                         <c:out value="${page.searchType eq 'tcw' ? 'selected':''}" />
                     >Title OR Content OR Writer</option>
			     </select>
			     
			     <input type="text" name="keyword" id="keywordInput" value="${page.keyword}" >
			     <button id="searchBtn">Search</button>
			     <button id="newBoardBtn">New Board</button>
			  </div> <!-- /.box-body -->
			  <div class="box-footer"></div> <!-- /.box-footer-->
			</div> <!-- /.box -->
			
			<div class="box">
				<div class="box-header">
					<h3 class="box-title">LIST PAGE</h3>
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
				            <td><a href='<c:url value="/sboard/readPage${pageMaker.makeSearch(pageMaker.page.page)}&bno=${boardVO.bno}" />'>${boardVO.title}</a></td>
				            <td>${boardVO.writer}</td>
				            <td><fmt:formatDate value="${boardVO.regdate}" 
				                    pattern="yyyy-MM-dd HH:mm"/></td>
				            <td><span class="badge bg-red">${boardVO.viewcnt}</span></td>
				        </tr>
				        </c:forEach>
				        
				    </table>
				
				</div> <!-- /.box-body -->
				
				<!-- pagination -->
				<div class="box-footer">
					<div class="text-center">
					  <ul class="pagination">
					    <c:if test="${pageMaker.prev}">
					        <li><a href="list${pageMaker.makeSearch(pageMaker.startPage - 1)}">&laquo;</a></li>
					    </c:if>
					    
					    <c:forEach begin="${pageMaker.startPage}"
					                end="${pageMaker.endPage}"
					                var="idx" >
	                        <li
	                            <c:out value="${pageMaker.page.page == idx ? 'class=active':''}" />>
	                            <a href="list${pageMaker.makeSearch(idx)}">${idx}</a>
	                        </li>
					    </c:forEach>
					    
					    <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
					        <li><a href="list${pageMaker.makeSearch(pageMaker.endPage + 1)}">&raquo;</a></li>
					    </c:if>
					  </ul>
				    </div> <!-- /.text-center -->
				</div> <!-- /.box-footer -->
				
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

$(document).ready(function() {
	
	$("#searchBtn").on("click", function(event) {
		console.log("selected:" + $("select option:selected").val());
		self.location = "list"
			  + '${pageMaker.makeQuery(1)}'
			  + '&searchType='
			  + $("select option:selected").val()
			  + '&keyword='
			  + $("#keywordInput").val();
	});
	
	$("#newBoardBtn").on("click", function(event) {
		self.location = "register";
	});
});
</script>