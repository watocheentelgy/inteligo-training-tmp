<#assign footer = getterUtil.getString(themeDisplay.getThemeSetting("footer")) /> 
<#assign title_value = getterUtil.getString(themeDisplay.getThemeSetting("titleValue")) /> 

<#assign hide_footer = false>
<#attempt>  
	<#assign hide_footer = getterUtil.getBoolean(layout.getExpandoBridge().getAttribute("hide-footer"))>
<#recover> 
</#attempt>
