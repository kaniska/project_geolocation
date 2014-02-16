<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<h3>Search Geo Location Info:</h3>

<c:url var="Url" value="/ip" />
<form:form modelAttribute="searchCriteria" action="${Url}" method="get"
	cssClass="inline">
	<span class="errors span-18"> <form:errors path="*" />
	</span>
	<fieldset>
		<div class="span-4">
			<label for="searchString">IP:</label>
			<form:input id="searchString" path="searchString" />
			<script type="text/javascript">
				Spring.addDecoration(new Spring.ElementDecoration({
					elementId : "searchString",
					widgetType : "dijit.form.ValidationTextBox",
					widgetAttrs : { promptMessage : "Search IP." }}));
			</script>
		</div>

		
		<div class="span-4 last">
			<button type="submit" >Search</button>
		</div>
	</fieldset>
</form:form>

<div id="searchResults">

<display:table name="entityList" id="bizEntity" pagesize="20" requestURI="" varTotals="totals">
<display:setProperty name="paging.banner.placement" value="bottom" />

<c:forEach var="columnLabel" items="${columnLabelList}">
          <td></td>
        <display:column title="${columnLabel}" sortable="true">
            <c:out value="${bizEntity.getNextValue()}"/>
        </display:column>
</c:forEach>
 
<display:footer>
  </display:footer>
</display:table>
</div>	
