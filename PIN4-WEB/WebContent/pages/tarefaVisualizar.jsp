<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
	<head>
		<meta charset="UTF-8">
		<title>Tela de Visualização de Tarefas</title>
		<script type="text/javascript">
			function submitForm(act, id){
				document.getElementById("tarefaId").value = id;
				document.getElementById("theForm").action="${ pageContext.request.contextPath}/tars/" + act;
				form.submit();
				return true;
			}
		</script>
		<style type="text/css">
			section{
				border:1px solid black;
				width:800px;
			}
			
			article{
				display:block;
				border:1px solid black;
				width:800px;
			}
			
			.botao1{
				border-radius:5px;
				background-color:#99FF99;
				float:left;
			}
			 
			.botao2{
				border-radius:5px;
				background-color:#FFCCCC;
				float:left;
			}
			
			.botao3{
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
				<form>
				<header>
					<dl>
						<dt>
							<label>Tarefa: ${table.updatedItem.id}</label>
						</dt>
						<dt>
							<label>${table.updatedItem.status}</label>
						</dt>
						<dt>
							<label>${table.updatedItem.resumo}</label>
						</dt>
					</dl>
				</header>
				<dl>
					<dt>
						<label>Projeto:${table.updatedItem.projeto.nome}</label>
					</dt>
					<dt>
						<label>Responsável:${table.updatedItem.usu_resp.nome}</label>
					</dt>
					<dt>
						<label>Tempo Previsto:${table.updatedItem.tempo_previsto}</label>
					</dt>
					<dt>
						<label>Tempo Gasto:${table.updatedItem.tempo_gasto}</label>
					</dt>
				</dl>
				<label>Descrição</label>
				<hr />
				<label>${table.updatedItem.descricao}</label>
				<footer>
				<c:if test="${ userLogado.tipo_usuario eq 'A' || table.projResp || table.tarResp }">
					<input type="submit" class="botao1" id="concluido" value="Conluído" />
					<input type="submit" class="botao2" id="pendente" value="Pendente" />
					<input type="submit" class="botao3" id="editarTarefa" value="Editar" onclick="return submitForm('edit', ${table.updatedItem.id});" />
				</c:if>
				</footer>
				<input type="hidden" id="visualizarTarefaID" />
				</form>
				<a href="${ pageContext.request.contextPath}/tars/" class="botao3"><button>Fechar</button></a>
			</article>
		</section>
	</body>
</html>