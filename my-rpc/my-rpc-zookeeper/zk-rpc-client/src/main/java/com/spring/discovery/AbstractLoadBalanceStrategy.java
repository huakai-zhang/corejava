package com.spring.discovery;

import java.util.List;

/**
 * @author 春阳
 * @date 2021-02-04 14:42
 */
public abstract class AbstractLoadBalanceStrategy implements LoadBalanceStrategy {

    @Override
    public String selectHost(List<String> repos) {
        //repos可能为空， 可能只有一个。
        if(repos==null||repos.size()==0){
            return null;
        }
        if(repos.size()==1){
            return repos.get(0);
        }
        return doSelect(repos);
    }

    protected abstract String doSelect(List<String> repos);

}
