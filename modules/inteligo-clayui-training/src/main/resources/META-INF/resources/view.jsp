<%@ include file="/init.jsp"%>

<p>
	<b><liferay-ui:message key="inteligoclayuitraining.caption" /></b>
</p>
<div>
	<clay:alert message="This is an info message." displayType="info"
		title="Info" dismissible="true" />

	<br>
	<hr>
	<br>
	<clay:stripe message="This is a warning message." displayType="warning"
		title="Warning" dismissible="true" />

	<br>
	<hr>
	<br>
	<clay:alert displayType="success" title="This is an inline variant."
		variant="inline" />
	<br>
	<hr>
	<br>
	<clay:alert displayType="danger" title="This is a feedback variant."
		variant="feedback" />

	<br>
	<hr>
	<br>
	<clay:sticker displayType="danger" icon="users" size="sm" />
	<clay:sticker displayType="dark" icon="users" size="md" />
	<clay:sticker displayType="info" icon="calendar" size="lg" />

	<clay:sticker displayType="light" icon="users" size="xl" />
	<clay:sticker displayType="secondary" icon="users" size="xl" />
	<clay:sticker displayType="success" icon="users" size="lg" />
	<clay:sticker displayType="unstyled" icon="chatbot" size="md" />

	<clay:sticker displayType="warning" icon="book" size="sm" />
	<br>
	<hr>
	<br>
	<clay:progressbar maxValue="<%=100%>" minValue="<%=0%>"
		status="warning" value="<%=70%>" />
	<clay:progressbar status="complete" />
	<br>
	<hr>
	<br>
	<clay:icon symbol="folder" />
 	<br>
 	<br>
 	<clay:sticker displayType="light" icon="github" size="xl" />
</div>