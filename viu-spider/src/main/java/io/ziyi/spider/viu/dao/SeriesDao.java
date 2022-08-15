package io.ziyi.spider.viu.dao;

import io.ziyi.spider.util.MapUtils;
import io.ziyi.spider.viu.model.Category;
import io.ziyi.spider.viu.model.ProductStream;
import io.ziyi.spider.viu.model.Series;
import io.ziyi.spider.viu.model.SeriesCategory;
import io.ziyi.spider.viu.model.SeriesProduct;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
public class SeriesDao extends CommonDao {

    @Transactional(readOnly = true)
    public Series findSeries(long seriesId) {
        return super.find(Series.class, seriesId);
    }

    @Transactional(readOnly = true)
    public List<Series> findSeriesWithoutProducts() {
        return super.query(Series.class, "query_series_without_products", null);
    }

    @Transactional(readOnly = true)
    public SeriesProduct findProduct(long id) {
        return super.find(SeriesProduct.class, id, false);
    }

    @Transactional(readOnly = true)
    public List<SeriesProduct> findProductsWithoutStreams() {
        return super.query(SeriesProduct.class, "query_products_without_streams", null);
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveSeries(Series series) {
        super.save(series);
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveSeries(Series series, long categoryId) {
        SeriesCategory sc = new SeriesCategory(series.getId(), categoryId);
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

    @Transactional(rollbackFor = Exception.class)
    public void saveSeriesProduct(SeriesProduct product) {
        super.save(product);
    }

    @Transactional(readOnly = true)
    public List<SeriesProduct> findSeriesProducts(long seriesId) {
        Map<String, Object> filter = MapUtils.build("seriesId", seriesId);
        Map<String, Boolean> sorter = asc("number");
        return super.query(SeriesProduct.class, filter, sorter, false);
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveProductStream(ProductStream stream) {
        super.save(stream);
    }

    @Transactional(readOnly = true)
    public ProductStream findProductStream(long id) {
        return super.find(ProductStream.class, id, false);
    }

    @Transactional(readOnly = true)
    public List<ProductStream> findProductStreams(long productId) {
        Map<String, Object> filter = MapUtils.build("productId", productId);
        return super.query(ProductStream.class, filter, null);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateProductStreams(long productId, Collection<ProductStream> streams) {
        Map<String, Object> filter = MapUtils.build("productId", productId);
        super.delete(ProductStream.class, filter);
        super.saveAll(streams);
    }

    private List<ProductStream> findReadyStreams(int count) {
        Map<String, Object> filter = MapUtils.build("status", ProductStream.Status.ready);
        Map<String, Boolean> sorter = asc("id");
        List<ProductStream> list = super.query(ProductStream.class, filter, sorter, 0, count, true);
        list.forEach(stream -> stream.setStatus(ProductStream.Status.downloading));
        super.saveAll(list);
        return list;
    }

    @Transactional(rollbackFor = Exception.class)
    public List<ProductStream> searchAndUpdateStreams(ProductStream.Status expected, ProductStream.Status update, int maxCount) {
        Map<String, Object> filter = MapUtils.build("status", expected);
        Map<String, Boolean> sorter = asc("id");
        List<ProductStream> list = super.query(ProductStream.class, filter, sorter, 0, maxCount, true);
        list.forEach(stream -> stream.setStatus(update));
        super.saveAll(list);
        return list;
    }

}
