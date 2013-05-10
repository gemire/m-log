/**
 * 
 */
package org.mspring.msns.api.spider.service.impl;

import java.util.List;

import org.mspring.msns.api.spider.service.SpiderRuleService;
import org.mspring.msns.api.spider.vo.Rule;
import org.mspring.platform.core.AbstractServiceSupport;
import org.mspring.platform.persistence.query.QueryCriterion;
import org.mspring.platform.persistence.support.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gao Youbo
 * @since 2013-3-8
 * @description
 * @TODO
 */
@Service
@Transactional
public class SpiderRuleServiceImpl extends AbstractServiceSupport implements SpiderRuleService {

    @Override
    public Rule getRuleById(Long id) {
        // TODO Auto-generated method stub
        return (Rule) getById(Rule.class, id);
    }

    @Override
    public Rule createRule(Rule rule) {
        // TODO Auto-generated method stub
        rule.setEnabled(true);
        Long id = (Long) create(rule);
        return getRuleById(id);
    }

    @Override
    public void updateRule(Rule rule) {
        // TODO Auto-generated method stub
        update(rule);
    }

    @Override
    public void deleteRule(Long... id) {
        // TODO Auto-generated method stub
        remove(Rule.class, id);
    }

    @Override
    public Page<Rule> findRulePage(QueryCriterion queryCriterion, Page<Rule> rulePage) {
        // TODO Auto-generated method stub
        return findPage(queryCriterion, rulePage);
    }

    @Override
    public List<Rule> findAllEnabledRules() {
        // TODO Auto-generated method stub
        return find("select rule from Rule rule where rule.enabled = true");
    }

}
