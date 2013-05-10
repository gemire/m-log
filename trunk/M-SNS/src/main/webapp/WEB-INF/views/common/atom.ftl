<?xml version="1.0" encoding="utf-8"?>
<feed xmlns="http://www.w3.org/2005/Atom">
	<title type="text">${title!""}</title>
	<subtitle type="html">${subtitle!""}</subtitle>
	<updated>${updated!""}</updated>
	<id>${siteurl}</id>
	<link href="${link}" rel="self" type="application/atom+xml" />
	<rights>${rights!""}</rights>
	<generator uri="${generator_uri}" version="${generator_version}">${generator}</generator>
	
	<#if (posts?exists && posts?size > 0)>
	<#list posts as post>
		<entry>
			<title><![CDATA[${post.title}]]></title>
			<link href="<@absolute_post_url />" />
			<updated>${updated}</updated>
			<published>${post.createTime}</published>
			<author>
				<name>${post.author.alias}</name>
				<uri>${siteurl}</uri>
				<email>${post.author.email}</email>
			</author>
			<#if (post.summary?exists) && (post.summary?trim?length > 0)>
			<summary type="html"><![CDATA[${post.summary!""}]]></summary>
			</#if>
			<content type="html"><![CDATA[${post.content}]]></content>
		</entry>
	</#list>
	</#if>
</feed>



