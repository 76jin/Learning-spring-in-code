<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp" %>

<!-- Main content -->
<section class="content">
	<div class="row">
		<!-- left column -->
		<div class="col-md-12">
			<!-- general form elements -->
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">READ BOARD</h3>
				</div> <!-- /.box-header -->
				
				<!-- 수정/삭제 작업에서 bno 정보가 필요하다. -->
				<form role="form" method="post" action="modifyPage">
				    <input type="hidden" name="bno" value="${boardVO.bno}" >
				    <input type="hidden" name='page' value="${page.page }" >
				    <input type="hidden" name='perPageNum' value="${page.perPageNum }" >
				</form>
				
				<!-- 조회 정보 출력 -->
			    <div class="box-body">
			        <div class="form-group">
			            <label for="exampleInputEmail1">Title</label>
			            <input type="text" name ="title" class="form-control"
			                    readonly="readonly" value="${boardVO.title}" >
			        </div>
			        <div class="form-group">
			            <label for="exampleInputEmail1">Content</label>
			            <textarea rows="3" class="form-control" name="content"
			                readonly="readonly">${boardVO.content}</textarea>
			        </div>
			        <div class="form-group">
			            <label for="exampleInputEmail1">Writer</label>
			            <input type="text" name="writer" class="form-control"
			                readonly="readonly" value="${boardVO.writer}" >
			        </div>
			    </div> <!-- /.box-body -->
			    
			    <div class="box-footer">
			        <button type="submit" class="btn btn-warning" id="modifyBtn">Modify</button>
			        <button type="submit" class="btn btn-danger" id="removeBtn">Remove</button>
			        <button type="submit" class="btn btn-primary" id="listAllBtn">LIST ALL</button>
			    </div>

			</div> <!-- /.box -->
		</div> <!--/.col (left) -->

	</div> <!-- /.row -->
</section> <!-- /.content -->

<script type="text/javascript">
$(document).ready(function() {
	var formObj = $("form[role='form']");
	console.log(formObj);
	
	$("#modifyBtn").on("click", function() {
		formObj.attr("action", "/board/modifyPage");
		formObj.attr("method", "get");
		formObj.submit();
	});
	
    $("#removeBtn").on("click", function() {
        formObj.attr("action", "/board/removePage");
        formObj.submit();
    });
    
    $("#listAllBtn").on("click", function() {
//    	self.location = "/board/listAll";
        formObj.attr("method", "get");
        formObj.attr("action", "/board/listPage");
        formObj.submit();
    });
    
});

</script>

<%@ include file="../include/footer.jsp" %>
