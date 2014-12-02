<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
	<head>
		<meta charset="UTF-8">
		<title>Tela de Inserção/Edição de Tarefas</title>
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
			
			article form label, article form input, article form select{
				display:block;
			}
			
			input[type='submit']{
				float:right;
			}
			
			#div1{
				float:left;
				margin:10px;
				padding:0px 0px 0px 20px;
			}
			
			#div2{
				clear:both;
				margin:0px 0px 0px 30px;
			}
			
			article header label{
				font-weight:bold;
				//margin:25px 0px 0px 30px;
				padding:25px 0px 0px 0px;
				display:inline-block;
			}
			
			article header span{
				display:block;
				font-weight:bold;
				font-size:20px;
				padding:25px 0px 0px 0px;
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
						<h1>Nova Tarefa</h1>

						<c:set var="action" scope="page" value="new" />

					</c:if>

					<c:if test="${ table.items ne null }">
						<h1>Editando Tarefa: ${ table.items[0].nome }</h1>
						<label>Número:</label>
						<label>${table.items[0].id}</label>				

						<c:set var="action" scope="page" value="update" />
					</c:if>
					</hgroup>
				</header>	
				<form method="post" action="${ pageContext.request.contextPath }/projs/${ action }">
					<div id="div1">
						<label for="inputResumo">Resumo</label>
						<input id="inputResumo" name="resumo" type="text" placeholder="Digite o resumo..." value="${ table ne null ? table.items[0].resumo : ''}" />
						<label for="inputResponsável">Responsável</label>
						<c:if test="${ table.projResp }">
						<select name="cbResponsavel">
							<option value="0">Selecione...</option>
						<c:forEach var="user" items="${table.listaDeUsuarios}">
						<c:choose>
							<c:when test="${table ne null }">
								<option value="${ user.id }" ${ table.items[0].id eq user.id ? 'selected' : '' }>${ user.nome }</option>
							</c:when>
							<c:otherwise>
								<option value="${ user.id }">${ user.nome }</option>
							</c:otherwise>
						</c:choose>
						</c:forEach>
						</select>
						</c:if>
						<label for="inputTpPrevisto">Tempo Previsto</label>
						<input id="inputTpPrevisto" name="tpPrevsito" type="text" placeholder="Digite o Tempo Previsto..." value="${ table.items.tempo_previsto }" />
					</div>
					<div id="div1">
						<label for="inputProjeto">Projeto</label>
						<select name="cbProjeto">
							<option value="0" ${ useLogado ne 'A' ? "disabled" : "" }>Selecione...</option>
						<c:forEach var="proj" items="${ table.listaDeProjetos }">
							<option value="${ proj.id }" ${ useLogado ne 'A' ? "disabled" : "" }>${ proj.nome }</option>
						</c:forEach>
						</select>
						<label for="inputSituacao">Situação</label>
						<input id="inputSituacao" name="situacao" type="text" placeholder="Digite a situacao..." value="${ table.items.status }"/>
						<label for="inputTpGasto">Tempo Gasto</label>
						<input id="inputTpGasto" name="tpGasto" type="text" placeholder="Digite o Tempo Gasto..." value="${ table.items.tempo_gasto }"/>
					</div>
					<div id="div2">
						<label for="inputDescricao">Descrição</label>
						<c:if test="${ table.projResp }">
						<textarea name="descricao" cols="40" rows="5"></textarea>
						</c:if>
					</div>
					<input type="submit" value="Salvar" />
					<!-- <input type="submit" value="Cancelar" formnovalidate="formnovalidate" /> -->
					<c:if test="${ table ne null }">
						<input type="hidden" name="id" value="${ table.items[0].id }"/>
					</c:if>
				</form>		
				<a href="${ pageContext.request.contextPath}/tars/"><button>Cancelar</button></a>
			</article>
		</section>
	</body>
</html>