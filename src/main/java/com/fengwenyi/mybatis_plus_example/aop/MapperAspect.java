package com.fengwenyi.mybatis_plus_example.aop;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fengwenyi.mybatis_plus_example.model.Dto.WebInDto;
import com.github.pagehelper.PageHelper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MapperAspect {
    @Pointcut("execution(public * com.fengwenyi.mybatis_plus_example.mapper.*Mapper.*(..))")
    public void mapper() {
    }

    @Before("mapper()")
    public void doBefore(JoinPoint jp) {
        setPageInfo(jp);
    }

    private void setPageInfo(JoinPoint pjp) {
        Object[] params = pjp.getArgs();
        if (params == null || params.length == 0) {
            return;
        }
        for (Object param : params) {
            if ((param instanceof WebInDto)) {
                setPageInfo((WebInDto) param);
            } else if (param instanceof Wrapper) {
                Object entity = ((Wrapper) param).getEntity();
                if (entity != null && entity instanceof WebInDto) {
                    setPageInfo((WebInDto) entity);
                }
            }
        }
    }

    private void setPageInfo(WebInDto param) {
        if (param.getPageNum() != null)
        {
            Integer pageNum = param.getPageNum();
            if (pageNum == null || pageNum < 0)
            {
                pageNum = 0;
            }

            Integer pageSize = param.getPageSize();
            if (pageSize == null || pageSize < 0)
            {
                pageSize = Integer.MAX_VALUE;
            }
            PageHelper.startPage(pageNum, pageSize, true);
        }

        if (StringUtils.isNotEmpty(param.getOrder()))
        {
            PageHelper.orderBy(param.getOrder() + " " + param.getSort());
        }
    }
}