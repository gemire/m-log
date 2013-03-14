<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />

<html>
<head>
    <title>${blogname}-微博授权</title>
</head>

<body>
<h1>${blogname} 微博授权</h1>
<h3>第一步. Get User's Authorization</h3></p>

<#if oauthParams?exists && oauthParams.errorMessage?has_content>
<font color="red"><p>Error: ${oauthParams.errorMessage}</p></font>
</#if>

<form action="${base}/t/${oauthParams.application}/authorize">
	<@spring.bind "oauthParams" />
    <table>
        <tr>
            <td>Required OAuth parameters:</td>
        </tr>
        <tr>
            <td>Requested Access Scope</td>
            <td><@spring.formInput path="oauthParams.scope" attributes='size="70"' /></td>
        </tr>
        <tr>
            <td>End-User Authorization URL:</td>
            <td><@spring.formInput path="oauthParams.authzEndpoint" attributes='size="70"' /></td>
        </tr>
        <tr>
            <td>Token Endpoint:</td>
            <td><@spring.formInput path="oauthParams.tokenEndpoint" attributes='size="70"' /></td>
        </tr>
        <tr>
            <td>Client ID:</td>
            <td><@spring.formInput path="oauthParams.clientId" attributes='size="70"' defaultValue="801226101" /></td>
        </tr>
        <tr>
            <td>Client Secret:</td>
            <td><@spring.formInput path="oauthParams.clientSecret" attributes='size="70"' defaultValue="9afb5cc19f5a3a27edbcd23ef2458ce5" /></td>
        </tr>
        <tr>
            <td>Redirect URI:</td>
            <td><@spring.formInput path="oauthParams.redirectUri" attributes='size="70"' /></td>
        </tr>
        <tr>
            <td colspan="2">
            	<@spring.formHiddenInput path="oauthParams.application" />
                <input type="submit" value="Get Authorization"/>
            </td>
        </tr>
    </table>
</form>

</body>
</html>
