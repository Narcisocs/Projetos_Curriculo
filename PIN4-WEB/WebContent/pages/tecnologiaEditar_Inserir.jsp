<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
	<head>
		<meta charset="UTF-8">
		<title>Tela de Inserção/Edição de Tecnologias</title>
		<style type="text/css">
			section{
				display:block;
				text-align:center;
			}
			
			article{
				display:inline-block;
				border:1px solid black;
				width:400px;
				text-align:left;
				padding:20px;
			}
			
			article form label, article form input{
				display:block;
			}
			
			input[type='submit']{
				float:right;
			}
		</style>
	</head>
	<body>
		<header>
			<hgroup>
				<h1>PIN4 WEB - ${ userLogado.tipo_usuario eq "A" ? "Administrador" : "Usuário" }</h1>
				<jsp:include page="menu.jsp"></jsp:include>
			</hgroup>
		</header>
		<section>
			<article>
				<header>
					<hgroup>
						<c:if test="${ table eq null }">
						<h1>Nova Tecnologia</h1>

						<c:set var="action" scope="page" value="new" />

					     </c:if>

					<c:if test="${ table ne null }">
						<h1>Editando Tecnologia: ${ table.items[0].nome }</h1>

						<c:set var="action" scope="page" value="update" />
					</c:if>
					</hgroup>
				</header>
				<form method="post" action="${ pageContext.request.contextPath }/tecs/${ action }">
					<label for="inputCodigo">Codigo</label>
					<input id="inputCodigo" name="codigo" type="text" placeholder="Digite o codigo..." value="${ table ne null ? table.items[0].codigo : ''}"/>
					<label for="inputNome">Nome</label>
					<input id="inputNome" name="nome" type="text" placeholder="Digite o nome..." value="${ table ne null ? table.items[0].nome : ''}"/>
					<label for="inputHomePage">Home Page</label>
					<input id="inputHomePage" name="home_page" type="text" placeholder="Digite a Home Page..." value="${ table ne null ? table.items[0].home_page : ''}"/>
					<input type="submit" value="Salvar" />
					<!--  <input type="submit" value="Cancelar"/> -->
					<c:if test="${ table ne null }">
						<input type="hidden" name="id" value="${ table.items[0].id }"/>
					</c:if>
				</form>
					<a href="${ pageContext.request.contextPath}/tecs/"><button>Cancelar</button></a>
			</article>
		</section>
	</body>
</html>