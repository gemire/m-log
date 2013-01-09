<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />
<html>
<head>
    <title>OAuth V2.0 Client Application</title>
</head>

<body>
<h1>Sample OAuth V2.0 Client Application</h1>

<h2>Web Server Flow</h2>

<h3>Step 3. Get Resource</h3></p>

<#if oauthParams?exists && oauthParams.errorMessage?has_content>
<font color="red"><p>Error: ${oauthParams.errorMessage}</p></font>
</#if>

<form commandName="oauthParams" action="/get_resource">
	<@spring.bind "oauthParams" />
    <table>
        <tr>
            <td>Required OAuth parameters:</td>
        </tr>
        <tr>
            <td>Resource URL:</td>
            <td><@spring.formInput path="oauthParams.resourceUrl" attributes='size="70"'/></td>
        </tr>
        <tr>
            <td>Access Token:</td>
            <td><@spring.formInput path="oauthParams.accessToken" attributes='readonly="true" size="70"'/></td>
        </tr>
        <tr>
            <td>Expires In:</td>
            <td><@spring.formInput path="oauthParams.expiresIn" attributes='readonly="true" size="70"'/></td>
        </tr>
        <tr>
            <td>Refresh Token:</td>
            <td><@spring.formInput path="oauthParams.refreshToken" attributes='readonly="true" size="70"'/></td>
        </tr>
        <tr>
            <td>Requested Access Scope</td>
            <td><@spring.formInput path="oauthParams.scope" attributes='readonly="true" size="70"'/></td>
        </tr>
        <tr>
            <td>End-User Authorization URL:</td>
            <td><@spring.formInput path="oauthParams.authzEndpoint" attributes='readonly="true" size="70"'/></td>
        </tr>
        <tr>
            <td>Token Endpoint:</td>
            <td><@spring.formInput path="oauthParams.tokenEndpoint" attributes='readonly="true" size="70"'/></td>
        </tr>
        <tr>
            <td>Client ID:</td>
            <td><@spring.formInput path="oauthParams.clientId" attributes='readonly="true" size="70"'/></td>
        </tr>
        <tr>
            <td>Client Secret:</td>
            <td><@spring.formInput path="oauthParams.clientSecret" attributes='readonly="true" size="70"'/></td>
        </tr>
        <tr>
            <td>Redirect URI:</td>
            <td><@spring.formInput path="oauthParams.redirectUri" attributes='readonly="true" size="70"'/></td>
        </tr>
        <tr>
            <td colspan="2">
                <@spring.formHiddenInput path="oauthParams.application" />
                <input type="submit" value="Get Resource"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
