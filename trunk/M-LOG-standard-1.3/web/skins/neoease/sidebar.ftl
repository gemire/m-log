<div class="side">
    <#if notice?has_content>
    <div>
        <h4>公告</h4>
        <div class="notice">${notice}</div>
    </div>
    </#if>
    <div>
        <h4>分类目录</h4>
        <ul class="side-li">
            <@m.widget path="/widget/listCatalog" />
        </ul>
    </div>
    <div>
        <h4>最新文章</h4>
        <ul class="side-li">
            <@m.widget path="/widget/recentPost" />
        </ul>
    </div>
    <div>
        <h4>热门文章</h4>
        <ul class="side-li">
            <@m.widget path="/widget/mostViewPost" />
        </ul>
    </div>
    <div>
        <h4>最新评论</h4>
        <ul class="side-li">
            <@m.widget path="/widget/recentComment" />
        </ul>
    </div>
    <div>
        <h4>链接</h4>
        <ul class="side-li">
            <@m.widget path="/widget/links" />
        </ul>
    </div>
</div>
