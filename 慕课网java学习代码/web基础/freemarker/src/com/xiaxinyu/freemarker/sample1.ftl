<#-- Freemarker取值 -->

${site}-${url}
<#-- 默认值 -->

${ip!"ip不存在"}

<#-- 格式化输出 -->
${date?string("yyyy-MM-dd-HH-mm-ss")}
${number?string("0.00")}

${c1.state}

${c1.info["cpu"]}

<#if c1.state == 1>
状态：正在使用
<#elseif c1.state == 2>
状态：闲置
<#else>
状态：已报废
</#if>
<#-- 在if标签内不需要使用花括号
判断字符串是否相等时时直接用 == 即可 -->
<#if computer.user??> <#-- 对象不为空的时候执行 -->
 
</#if>