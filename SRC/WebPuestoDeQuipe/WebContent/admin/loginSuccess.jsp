<%@ taglib prefix="s" uri="/struts-tags" %>
<s:actionmessage theme="css_xhtml" />
<script>
function delayer(){
    window.location = "<%=request.getContextPath()%>/admin/Productos.do";
}
setTimeout('delayer()', 3000);
</script>