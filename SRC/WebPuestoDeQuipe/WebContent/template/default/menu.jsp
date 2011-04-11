<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:set name="user" value="USER" scope="session"/>

<div id="topmenu">
<ul class="glossymenu">
	<li class="current" id="inicioMenu"><a href="<%=request.getContextPath()%>/Home.do"><b>Inicio</b></a></li>
	<li id="ordenarMenu"><a href="<%=request.getContextPath()%>/Ordenar.do"><b>Ordenar</b></a></li>
	<li id="especialesMenu"><a href="#"><b>Especiales</b></a></li>	
	<li id="nosotrosMenu"><a href="<%=request.getContextPath()%>/Nosotros.do"><b>¿Nosotros?</b></a></li>	
	<s:if test="#session.USER != null">
	<li id="administracionMenu"><a href="<%=request.getContextPath()%>/admin/Productos.do"><b>Administraci&oacute;n</b></a>
		 <ul id="submenu">
			  <li><a href="<%=request.getContextPath()%>/admin/Productos.do">Productos</a></li>
			  <li><a href="<%=request.getContextPath()%>/admin/Especiales.do">Especiales</a></li>
			  <li><a href="<%=request.getContextPath()%>/admin/Especiales.do">Cocina</a></li>
  		</ul>
	</li>		
	</s:if>
</ul>
</div>