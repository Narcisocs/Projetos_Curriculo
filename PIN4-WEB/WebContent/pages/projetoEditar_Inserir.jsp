<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
	<head>
		<meta charset="UTF-8">
		<title>Tela de Inserção/Edição de Projetos</title>
		<style type="text/css">
			section{
				display:block;
				text-align:center;
			}
			
			section > header{
				text-align:left;
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
						<c:if test="${ table.items eq null }">
						<h1>Novo Projeto</h1>

						<c:set var="action" scope="page" value="new" />

					</c:if>

					<c:if test="${ table.items ne null }">
						<h1>Editando Projeto: ${ table.items[0].nome }</h1>

						<c:set var="action" scope="page" value="update" />
					</c:if>
					</hgroup>
				</header>
				<form method="post" action="${ pageContext.request.contextPath }/projs/${ action }">
					<label for="inputCodigo">Código</label>
					<input id="inputCodigo" name="codigo" type="text" placeholder="Digite o nome..." value="${ table ne null ? table.items[0].nome : ''}"/>
					<label for="inputTitulo">Título</label>
					<input id="inputTitulo" name="titulo" type="text" placeholder="Digite o titulo..." value="${ table ne null ? table.items[0].titulo : ''}"/>
					<label for="inputResponsável">Responsável</label>
					<select name="cbResponsavel">
						<option value="0">Selecione...</option>
					<c:forEach var="user" items="${ table.listaDeUsuario }">
						<c:choose>
							<c:when test="${ table ne null }">
								<option value="${ user.id }" ${ table.items[0].id eq user.id ? 'selected' : ''}>${ user.nome }</option>
							</c:when>
							<c:otherwise>
								<option value="${ user.id }">${ user.nome }</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					</select>
					<label for="inputTecnologia">Tecnologia</label>
					<select name="cbTecnologia">
						<option value="0">Selecione...</option>
						<c:forEach var="tecs" items="${ table.listaDeTecnologia }">
							<c:choose>
								<c:when test="${ table ne null }">
									<option value="${ tecs.id }" ${ table.items[0].id eq tecs.id ? 'selected' : '' }>${ tecs.nome }</option>
								</c:when>
								<c:otherwise>
									<option value="${ tecs.id }">${ tecs.nome }</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
					<label for="inputOrcamento">Orçamento</label>
					<input id="inputOrcamento" name="orcamento" type="text" placeholder="Digite o orcamento..." value="${ table ne null ? table.items[0].orcamento : ''}"/>
					<input type="submit" value="Salvar" />
					<!--  <input type="submit" value="Cancelar" formnovalidate="formnovalidate" />-->
				<c:if test="${ table ne null }">
						<input type="hidden" name="id" value="${ table.items[0].id }"/>
					</c:if>
				</form>		
				<a href="${ pageContext.request.contextPath}/projs/"><button>Cancelar</button></a>
			</article>
		</section>
	</body>
</html>