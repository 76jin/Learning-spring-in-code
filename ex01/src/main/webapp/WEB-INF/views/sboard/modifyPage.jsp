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
					<h3 class="box-title">MODIFY BOARD</h3>
				</div> <!-- /.box-header -->
				
				<form role="form" method="post">
				    <!-- 페이지 정보 저장용 정보 -->
				    <input type="hidden" name="page" value="${page.page }" >
				    <input type="hidden" name="perPageNum" value="${page.perPageNum }" >
				    <!-- 
				        TODO: 이 소스코드 필요한데, 없어야 정상 동작함. 이유 모르겠음.
                    <input type="hidden" name="searchType" value="${page.searchType}" >
                    <input type="hidden" name="keyword" value="${page.keyword}" >
                     -->
				    
					<!-- 수정용 정보 출력 -->
				    <div class="box-body">
				        <div class="form-group">
				            <label for="exampleInputEmail1">BNO</label>
				            <input type="text" name ="bno" class="form-control"
				                    readonly="readonly" value="${boardVO.bno}" >
				        </div>
				        <div class="form-group">
				            <label for="exampleInputEmail1">Title</label>
				            <input type="text" name ="title" class="form-control"
				                    placeholder="Enter Title" 
				                    value="${boardVO.title}" >
				        </div>
				        <div class="form-group">
				            <label for="exampleInputEmail1">Content</label>
				            <textarea rows="3" class="form-control" name="content"
				                placeholder="Enter ..." >${boardVO.content}</textarea>
				        </div>
				        <div class="form-group">
				            <label for="exampleInputEmail1">Writer</label>
				            <input type="text" name="writer" class="form-control"
				                placeholder="Enter Writer" readonly="readonly" 
				                value="${boardVO.writer}" >
				        </div>
				    </div> <!-- /.box-body -->
				</form>
			    
			    <div class="box-footer">
			        <button type="submit" class="btn btn-primary" id="saveBtn">Save</button>
			        <button type="submit" class="btn btn-warning" id="cancelBtn">Cancel</button>
			    </div>

			</div> <!-- /.box -->
		</div> <!--/.col (left) -->

	</div> <!-- /.row -->
</section> <!-- /.content -->

<script type="text/javascript">
$(document).ready(function() {
	var formObj = $("form[role='form']");
	console.log(formObj);
	
    $("#saveBtn").on("click", function() {
        formObj.submit();
    });
    
    $("#cancelBtn").on("click", function() {
    	self.location = "/sboard/list?page=${page.page}&perPageNum=${page.perPageNum}"
    			+ "&searchType=${page.searchType}&keyword=${page.keyword}";
    });
    
});

</script>

<%@ include file="../include/footer.jsp" %>
