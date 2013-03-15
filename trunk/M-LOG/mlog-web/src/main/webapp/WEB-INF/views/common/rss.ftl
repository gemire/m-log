<?xml version="1.0"?>
<rss version="2.0">
	<channel>
      	<title>${title}</title>
      	<link>${link}</link>
      	<description>${description}</description>
      	<language>${language!"zh_CN"}</language>
      	<copyright>${copyright!""}</copyright>
      	<pubDate>${pubDate!""}</pubDate>
      	<lastBuildDate>${lastBuildDate!""}</lastBuildDate>
      	<docs>${docs!""}</docs>
      	<generator>${generator!""}</generator>
      	<managingEditor>${managingEditor!""}</managingEditor>
      	<webMaster>${webMaster!""}</webMaster>
      	<#if (posts?exists && posts?size > 0)>
      	<#list posts as post>
		<item>
        	<link><@absolute_post_url /></link>
        	<title><![CDATA[${post.title}]]></title>
        	<author>${post.author.email}(${post.author.alias})</author>
        	<category><![CDATA[ <#if (post.catalogs?exists && post.catalogs?size > 0)><#list post.catalogs as catalog>${catalog.name} </#list></#if> ]]></category>
        	<pubDate>${post.createTime}</pubDate>
        	<guid><@absolute_post_url /></guid>    
        	<description><![CDATA[${post.content}]]></description>
    	</item>
    	</#list>
    	</#if>
   	</channel>
</rss>