package io.ziyi.spider.viu.dao;

import io.ziyi.spider.viu.model.Category;
import io.ziyi.spider.viu.model.Series;
import io.ziyi.spider.viu.model.SeriesCategory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Component
public class SeriesDao extends CommonDao {

    @Transactional(rollbackFor = Exception.class)
    public void saveSeries(Series series, long categoryId) {
        SeriesCategory sc = new SeriesCategory(series.getSeriesId(), categoryId);
        super.save(series);
        super.save(sc);
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveSeriesCategory(SeriesCategory sc) {
        super.save(sc);
    }

    @Transactional(readOnly = true)
    public List<Category> findCategories() {
        return super.query(Category.class, Collections.emptyMap(), null);
    }

    @Transactional(readOnly = true)
    public Category findCategory(long id) {
        return super.find(Category.class, id);
    }

}
