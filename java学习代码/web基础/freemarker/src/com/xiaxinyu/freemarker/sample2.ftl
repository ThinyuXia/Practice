<#list list as c>
	${c_index + 1} <#-- 循环索引 -->
	${c.price}
	${c.model}
</#list>


<#list computerMap?keys  as k>
	 ${computerMap[k].model}
</#list> 