<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


		<style type='text/css'>
			
			nav {
				display:inline-block;
			}
			
			nav ul{
				padding:0px;
				margin:0px;
				background-color:#EDEDED;
				list-style:none;
			}
			
			nav ul li{
				display:inline;
				float:left;
			}
			
			nav ul li a {
				padding: 2px 10px;
				display: inline-block;
				background-color:#EDEDED;
				color: #333;
				text-decoration: none;
				border-bottom:3px solid #EDEDED;
			}
			
			nav ul li a:hover {
				background-color:#D6D6D6;
				color: #6D6D6D;
				border-bottom:3px solid #EA0000;
			}
		</style>
		<nav>
			<ul>
				<c:if test="${userLogado.tipo_usuario == 'A' }">
					<li><a href="${ pageContext.request.contextPath}/users">Usuários</a></li>
					<li><a href="${ pageContext.request.contextPath}/tecs">Tecnologias</a></li>
					<li><a href="${ pageContext.request.contextPath}/projs">Projetos</a></li>
				</c:if>
				<li><a href="${ pageContext.request.contextPath}/tars">Tarefas</a></li>
				<li><a href="${ pageContext.request.contextPath}/banco">Base de conhecimento</a></li>
				<li><a href="${ pageContext.request.contextPath }/logout">Logout</a></li>
			</ul>
		</nav>
