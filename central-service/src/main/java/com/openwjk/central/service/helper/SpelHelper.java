package com.openwjk.central.service.helper;

import com.openwjk.commons.utils.Constant;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/10 16:00
 */
@Service
public class SpelHelper {
    private static final ExpressionParser PARSER = new SpelExpressionParser();

    public String getValue(String[] spel, Map<String, Object> param) {
        String result = Constant.EMPTY_STR;
        if (spel == null || spel.length == 0 || MapUtils.isEmpty(param)) return result;
        EvaluationContext context = new StandardEvaluationContext();
        for (Map.Entry<String, Object> entry : param.entrySet()) {
            context.setVariable(entry.getKey(), entry.getValue());
        }
        for (int i = 0; i < spel.length; i++) {
            if (StringUtils.isBlank(spel[i])) continue;
            result += getValue(spel[i], context);
        }
        return result;
    }

    public String getValue(String spel, EvaluationContext context) {
        Expression keyExpression = PARSER.parseExpression(spel);
        String result = keyExpression.getValue(context, String.class);
        return result;
    }

    public String getValue(String spel, Map<String, Object> param) {
        EvaluationContext context = new StandardEvaluationContext();
        for (Map.Entry<String, Object> entry : param.entrySet()) {
            context.setVariable(entry.getKey(), entry.getValue());
        }
        Expression keyExpression = PARSER.parseExpression(spel);
        String result = keyExpression.getValue(context, String.class);
        return result;
    }
}
