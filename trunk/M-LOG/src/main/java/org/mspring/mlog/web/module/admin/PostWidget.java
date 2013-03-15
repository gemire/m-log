/**
 * 
 */
package org.mspring.mlog.web.module.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.mspring.mlog.core.ServiceFactory;
import org.mspring.mlog.entity.Catalog;
import org.mspring.mlog.entity.Post;
import org.mspring.mlog.entity.Tag;
import org.mspring.mlog.entity.security.User;
import org.mspring.mlog.service.CatalogService;
import org.mspring.mlog.service.PostService;
import org.mspring.mlog.service.TagService;
import org.mspring.mlog.service.search.HibernateSearchService;
import org.mspring.mlog.support.log.Log;
import org.mspring.mlog.support.resolver.PathParam;
import org.mspring.mlog.support.resolver.QueryParam;
import org.mspring.mlog.web.freemarker.widget.stereotype.Widget;
import org.mspring.mlog.web.module.admin.query.PostQueryCriterion;
import org.mspring.mlog.web.security.SecurityUtils;
import org.mspring.platform.persistence.support.Page;
import org.mspring.platform.persistence.support.Sort;
import org.mspring.platform.utils.StringUtils;
import org.mspring.platform.web.render.JSONRender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Gao Youbo
 * @since 2012-7-23
 * @Description
 * @TODO
 */
@Widget
@RequestMapping("/admin/post")
public class PostWidget extends AbstractAdminWidget {
	private static final Logger log = Logger.getLogger(PostWidget.class);

	@Autowired
	private PostService postService;
	@Autowired
	private CatalogService catalogService;
	@Autowired
	private TagService tagService;

	@SuppressWarnings("rawtypes")
	@RequestMapping("/list")
	@Log
	public String listPost(@ModelAttribute Page<Post> postPage,
			@ModelAttribute Post post, @QueryParam Map queryParams,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		if (postPage == null) {
			postPage = new Page<Post>();
		}
		postPage.setSort(new Sort("isTop desc, id desc", ""));

		// 默认查看已发布的文章
		if (post == null) {
			post = new Post();
		}
		if (queryParams.get("status") == null
				|| StringUtils.isBlank(queryParams.get("status").toString())) {
			post.setStatus(Post.Status.PUBLISH);
			queryParams.put("status", Post.Status.PUBLISH);
		}

		postPage = postService.findPost(postPage, new PostQueryCriterion(
				queryParams));
		model.addAttribute("postPage", postPage);
		model.addAttribute("status", Post.Status.getStatusMap());
		return "/admin/post/listPost";
	}

	@RequestMapping("/create")
	public String createPostView(@ModelAttribute Post post,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		// 文章分类
		List<Catalog> catalogs = catalogService.findAllCatalog();
		model.addAttribute("catalogs", catalogs);

		// 是否开启评论
		Map<String, String> commentStatus = new HashMap<String, String>();
		commentStatus.put(Post.CommentStatus.OPEN, "开启");
		commentStatus.put(Post.CommentStatus.CLOSE, "关闭");
		model.addAttribute("commentStatus", commentStatus);

		Map<String, String> isTop = new HashMap<String, String>();
		isTop.put("true", "是");
		isTop.put("false", "否");
		model.addAttribute("isTop", isTop);
		return "/admin/post/createPost";
	}

	@RequestMapping("/create/save")
	@Log
	public String create_save(@ModelAttribute Post post,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		User user = SecurityUtils.getCurrentUser(request);
		if (post.getAuthor() == null) {
			post.setAuthor(user);
		}
		post.setPostIp(request.getRemoteAddr());
		postService.createPost(post);
		return "redirect:/admin/post/list";
	}

	/**
	 * 将文章移入回收站
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/trash")
	@Log
	public String trashPost(@RequestParam(required = false) Long[] id,
			@ModelAttribute Page<Post> postPage, @ModelAttribute Post post,
			@QueryParam Map queryParams, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		if (id != null && id.length > 0) {
			postService.discardPost(id);
		}
		return listPost(postPage, post, queryParams, request, response, model);
	}

	/**
	 * 从回收站还原文章
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/trash2Publish")
	@Log
	public String trash2Publish(@RequestParam(required = false) Long[] id,
			@ModelAttribute Page<Post> postPage, @ModelAttribute Post post,
			@QueryParam Map queryParams, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		if (id != null && id.length > 0) {
			postService.trash2Publish(id);
		}
		// return "redirect:/admin/post/list";
		return listPost(postPage, post, queryParams, request, response, model);
	}

	/**
	 * 彻底删除文章
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/delete")
	@Log
	public String deletePost(@RequestParam(required = false) Long[] id,
			@ModelAttribute Page<Post> postPage, @ModelAttribute Post post,
			@QueryParam Map queryParams, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		if (id != null && id.length > 0) {
			postService.deletePost(id);
		}
		return listPost(postPage, post, queryParams, request, response, model);
	}

	/**
	 * 清空回收站
	 * 
	 * @return
	 */
	@RequestMapping("clearTrash")
	@Log
	public String clearTrash(@ModelAttribute Page<Post> postPage,
			@ModelAttribute Post post, @QueryParam Map queryParams,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		postService.clearTrash();
		return listPost(postPage, post, queryParams, request, response, model);
	}

	@RequestMapping("/edit")
	public String editPostView(@ModelAttribute Post post,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		if (post == null || post.getId() == null) {
			Object obj = getSessionAttribute(request, "PostWidget_edit_id");
			if (obj != null) {
				Long id = (Long) obj;
				post = new Post(id);
			}
		}
		setSessionAttribute(request, "PostWidget_edit_id", post.getId());

		if (post == null || post.getId() == null) {
			return prompt(model, "请先选择要修改的文章");
		}
		post = postService.getPostById(post.getId());

		Map<String, String> isTop = new HashMap<String, String>();
		isTop.put("true", "是");
		isTop.put("false", "否");
		model.addAttribute("isTop", isTop);
		model.addAttribute("post", post); // 文章
		model.addAttribute("catalogs", catalogService.findAllCatalog()); // 文章分类
		model.addAttribute("commentStatus",
				Post.CommentStatus.getCommentStatusMap()); // 是否开启评论
		return "/admin/post/editPost";
	}

	@RequestMapping("/edit/save")
	@Log
	public String edit_save(@ModelAttribute Post post,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		postService.updatePost(post);
		return "redirect:/admin/post/list";
	}

	/**
	 * 更新文章索引
	 * 
	 * @return
	 */
	@RequestMapping("/updateIndex")
	@ResponseBody
	@Log
	public String updateLuceneIndex() {
		try {
			HibernateSearchService hibernateSearchService = ServiceFactory
					.getHibernateSearchService();
			hibernateSearchService.updateAllIndex(Post.class);
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("update post index failure!", e);
			return "false";
		}
		return "true";
	}

	/**
	 * 标签自动补全
	 * 
	 * @param keyword
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/autocomplete")
	public void autocomplete(@PathParam(required = false) String keyword,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Tag> tags = tagService.findLikeByName(keyword);
		JSONRender render = new JSONRender(tags, false);
		render.render(response);
	}
}
