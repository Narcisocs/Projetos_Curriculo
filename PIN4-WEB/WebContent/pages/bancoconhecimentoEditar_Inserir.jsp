<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
	<head>
		<meta charset="UTF-8">
		<title>Tela de Inserção de Conhecimentos</title>
		<style type="text/css">
			section{
				display:block;
				text-align:center;
			}
			
			article{
				display:inline-block;
				border:1px solid black;
				width:550px;
				text-align:left;
				padding:20px;
			}
		
			article form label, article form input, article form select{
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
						<c:if test="${ table.items eq null }">
						<h1>Novo Conhecimento</h1>

						<c:set var="action" scope="page" value="new" />

					</c:if>

					<c:if test="${ table.items ne null }">
						<h1>Editando Conhecimento: ${ table.items[0].nome }</h1>

						<c:set var="action" scope="page" value="update" />
					</c:if>
					</hgroup>
				</header>
				<form method="post" action="${ pageContext.request.contextPath }/banco/${ action }">
					<label for="inputTecnologia">Tecnologia</label>
					<select name="cbTecnologia">
						<option value="0">Selecione...</option>
					<c:forEach var="tec" items="${ table.listaDeTecnologias }">
						<c:choose>
						<c:when test="${ table ne null }">
							<option value="${ tec.id }" ${ table.items[0].id eq tec.id ? 'selected' : ''}>${ tec.nome }</option>
						</c:when>
						<c:otherwise>
							<option value="${ tec.id }">${ tec.nome }</option>
						</c:otherwise>
						</c:choose>
					</c:forEach>
					</select>
					<label for="inputResumo">Resumo</label>
					<input id="inputBaseResumo" name="resumo" type="text" placeholder="Digite o Resumo..." value="${ table ne null ? table.items[0].resumo : ''}" /> 
					<label for="inputDescricao">Descrição</label>
					<textarea name="descricao" cols="60" rows="5" value="${ table ne null ? table.items[0].descricao : ''}"></textarea>
					<input type="submit" value="Salvar" />
					<input type="hidden" name="usuarioId" value="${ userLogado.id }" />
					<!--  <input type="submit" value="Cancelar" formnovalidate="formnovalidate" />-->
					<c:if test="${ table.items ne null }">
						<input type="hidden" name="id" value="${ table.items[0].id }"/>
					</c:if>
				</form>		
				<a href="${ pageContext.request.contextPath}/banco/"><button>Cancelar</button></a>
			</article>
		</section>
	</body>
</html>