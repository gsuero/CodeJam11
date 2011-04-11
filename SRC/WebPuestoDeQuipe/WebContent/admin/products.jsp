<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ui/jquery.ui.core.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ui/jquery.ui.widget.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ui/minified/jquery.ui.dialog.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ui/minified/jquery.ui.position.min.js"></script>
<script src="<%=request.getContextPath()%>/js/grid.locale-sp.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/jquery.jqGrid.min.js" type="text/javascript"></script>
<script>
$.jgrid.useJSON = true;
var ingredientsPage = CONTEXT_PATH + "/admin/Ingredientes.do";
$(document).ready(function() {
	var lastsel;
	jQuery("#rowed3").jqGrid(
			{
				url : CONTEXT_PATH+'/ajax/admin/listadoProductos.do',
				datatype: "json",
			    ajaxGridOptions: { contentType: "application/json" },
			    jsonReader : { 
			        root: "rows", 
			        page: "page", 
			        total: "total", 
			        records: "records", 
			        repeatitems: false
			    },
			    headertitles: true,
				colNames : [ 'ID', 'Producto', 'Precio', ''],
				colModel : [ {
					name : 'productId',
					index : 'productId',
					align : "right",
					width : 50, 
					editable : false,
					hidden: true, 
					editable: true, 
					editrules: { edithidden: false }, 
					hidedlg: true
				}, {
					name : 'productName',
					index : 'productName',
					width : 300,
					editable : true,
					required : true,
					sortable:false, 
					search:false
				}, {
					name : 'price',
					index : 'price',
					align : "right",
					width : 120,
					editable : true,
					required : true,
					sortable:false, 
					search:false,
					editrules:{number:true }
				},
				 {
					name : 'actionClick',
					index : 'actionClick',
					width : 120,
					align : "center",
					editable : false,
					required : false,
					sortable:false, 
					search:false
				 } ],
				rowNum : 20,
				rowList : [ 20, 40, 60, 80 ],
				pager : '#prowed3',
				sortname : 'productId',
				viewrecords : true,
				sortorder : "desc",
				gridComplete: function() {
			        var grid = jQuery("#rowed3");
			        var ids = grid.jqGrid('getDataIDs');
			        for (var i = 0; i < ids.length; i++) {
			            var rowId = ids[i];
			            var page = ingredientsPage+"?productId=" +  grid.getCell(rowId, 'productId');
			            
			            var actionPopup = "opennormalpopup('"+page+"', 'IngredientToProduct',400 , 380)";
			            var checkOut = "<input style='height:22px;width:75px;' " +
			                           "type='button' value='Ingredientes' " +
			                           "onclick=\""+actionPopup+"\" />";
			            grid.jqGrid('setRowData', rowId, { actionClick: checkOut });
			        }
			    },
				onSelectRow : function(id) {
					if (id && id !== lastsel) {
						jQuery('#rowed3').jqGrid('restoreRow', lastsel);
						jQuery('#rowed3').jqGrid('editRow', id, true);
						lastsel = id;
					}
				},
				editurl : CONTEXT_PATH+'/ajax/admin/actualizarProductos.do',
				caption : "Productos"
			});
	jQuery("#rowed3").jqGrid('navGrid', "#prowed3", {
		edit : false,
		add : true,
		del : true
	});
})
</script>
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/ui.jqgrid.css" />
<br />
<br />
<h1>Productos</h1>
 			<s:actionerror id="actionError" theme="css_xhtml" />
			<s:actionmessage id="actionMessage" theme="css_xhtml" />
	<table id="centerTableFull">
		<tr>
			<td align="center">
				<table id="rowed3"></table> <div id="prowed3"></div> <br /> 
			</td>
		</tr>
	</table>
