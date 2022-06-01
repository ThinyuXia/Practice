${name?cap_first}
${brand?upper_case}
${brand?lower_case}
${brand?length}
${words?replace("blood","*****")} <#-- 会替换所有blood字符串而不是一个 -->
${words?index_of("blood")}
${(words?index_of("blood") != -1) ? string("包含敏感词汇","不包含敏感词汇")} 

${n?round}
${n?floor}
${n?ceiling}

${list?size}
${list?first.model}
${list?last.model}

<#list list?sort_by("price")?reverse as c>
	${c.price}
</#list>