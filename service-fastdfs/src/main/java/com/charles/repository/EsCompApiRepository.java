package com.charles.repository;

import com.charles.model.EsCompApi;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author: GanZiB
 * Date: 2019-04-29
 * Time: 16:30
 */
public interface EsCompApiRepository extends ElasticsearchRepository<EsCompApi,String> {
}
