<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<nav class="navbar navbar-expand-sm navbar-dark bg-dark mb-5">
		<div class="collapse navbar-collapse justify-content-end" id="navbarNav4">
		<h1 class="mb-3" style="color:white">顧客管理システム</h1>
			<ul class="navbar-nav">
				<li class="nav-item">
					<a class="nav-link" href="<%=request.getContextPath()%>/Logout" onclick="return Logout_Dialog()">ログアウト</a>
				</li>
			</ul>
		</div>
	</nav>
</body>
<script type="text/javascript">
	function Logout_Dialog(){
		var res = confirm("ログアウトします。よろしいですか？");
		if(res){
			return true;
		} else {
			return false;
		};
	};
</script>
</html>