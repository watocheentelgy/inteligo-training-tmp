<#assign hide_head = false>
<#attempt>  
	<#assign hide_head = getterUtil.getBoolean(layout.getExpandoBridge().getAttribute("hide-footer"))>
<#recover> 
</#attempt>

<#assign title_value = getterUtil.getString(themeDisplay.getThemeSetting("titleValue")) /> 
<#assign footer = getterUtil.getString(themeDisplay.getThemeSetting("footer")) /> 