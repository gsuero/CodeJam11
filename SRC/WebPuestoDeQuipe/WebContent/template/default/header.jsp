<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:set name="user" value="USER" scope="session"/>

<table id="tableHeader" border="0">
	<tr>
	<td width="25%" valign="top" align="left">
		&nbsp;
	</td>
	<td width="50%" valign="top" height="5px" align="center">
		<img src="<%=request.getContextPath()%>/images/Logo.png" border="0" width="70" height="70" />
	</td>
	<td width="25%" valign="top" height="5px">
		<div id="headerLinks">
		<s:if test="#session.USER != null">
			Bienvenido <s:property value="#session.USER" />
			(<a href="<%=request.getContextPath()%>/admin/Salir.do" >Salir</a>)
		</s:if>
		</div>
	</td>
	</tr>
</table>
