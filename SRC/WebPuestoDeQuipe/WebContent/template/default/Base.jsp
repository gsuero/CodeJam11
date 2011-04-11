<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	<title><tiles:insertAttribute name="title" ignore="true" /></title>
	<link  rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/template/default/css/styles.css" />
<!-- 	<link type="text/css" href="<%=request.getContextPath()%>/css/blitzer/jquery-ui-1.8.custom.css" rel="Stylesheet" />  -->	
	<link type="text/css" href="<%=request.getContextPath()%>/js/themes/blitzer/jquery.ui.all.css" rel="stylesheet" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.8.1.custom.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/commons.js"></script>
	<script type="text/javascript">
		droplinemenu.buildmenu("topmenu");
		var CONTEXT_PATH = "<%=request.getContextPath()%>";
		var LOGGED_USER = false;
	</script>
	<s:if test="#session.USER != null">
	<script type="text/javascript">
		LOGGED_USER = true;
	</script>
	</s:if>
</head>
<body>
    <div id="backgroundFilter"></div>

	<div align="center" id="bigContainer">
	<div id="container" align="center"> <!-- class="ui-corner-all-container"> -->
		<table id="layoutTable">
			<tr>
				<td>
					<div id="headerdiv">
						<tiles:insertAttribute name="header" />
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div id="menudiv">
						<tiles:insertAttribute  name="menu" />
					</div>
				</td>
						
			</tr>
			<tr>
				<td align="left">
					<div id="bodydiv">
						<tiles:insertAttribute  name="body" />
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div id="footerdiv">
						<tiles:insertAttribute  name="footer" />
					</div>
				</td>
			</tr>	
		</table>
	</div>
	</div>
</body>
</html>