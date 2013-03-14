<?xml version="1.0" encoding="UTF-8"?>
<urlset xmlns="http://www.sitemaps.org/schemas/sitemap/0.9" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
	xsi:schemaLocation="http://www.sitemaps.org/schemas/sitemap/0.9 http://www.sitemaps.org/schemas/sitemap/0.9/sitemap.xsd">
	<#if urlset?exists>
	<#list urlset as item>
	<url>
		<loc>${item.loc}</loc>
		<priority>${item.priority!""}</priority>
		<lastmod>${item.lastmod}</lastmod>
		<changefreq>${item.changefreq}</changefreq>
	</url>
	</#list>
	</#if>
</urlset>