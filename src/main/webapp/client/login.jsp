<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="icon" type="image/png"
	href="https://salt.tikicdn.com/media/upload/2018/12/03/0054c1410e38f1b9f3609c244974dd9c.png"
	sizes="32x32" />
<link rel="icon" type="image/png"
	href="https://salt.tikicdn.com/media/upload/2018/12/03/8bd2f9756646e32ebb076b2f6b050380.png"
	sizes="16x16" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.theme.default.css" />
<link
	href="${pageContext.request.contextPath}/assets/client/sass/style.css"
	rel="stylesheet" type="text/css" />
</head>
<body>
	<div id="main">
		<jsp:include page="header.jsp" />

		<c:if test="${message != null}">
			<div align="center">
				<h4 class="message">${message}</h4>
			</div>
		</c:if>

		<div>
			<h2>Đăng nhập:</h2>
			<form id="loginForm" action="login" method="post">
				<table>
					<tr>
						<td>Email:</td>
						<td><input type="text" name="email" id="email"></td>
					</tr>
					<tr>
						<td>Mật khẩu:</td>
						<td><input type="password" name="password" id="password"
							size="20"></td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<button type="submit">Đăng nhập</button>
						</td>
					</tr>
				</table>
			</form>
		</div>

		<jsp:include page="footer.jsp" />

	</div>
</body>
</html>