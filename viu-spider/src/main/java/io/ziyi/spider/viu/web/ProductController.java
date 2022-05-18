package io.ziyi.spider.viu.web;

import io.ziyi.spider.viu.common.BaseComponent;
import io.ziyi.spider.viu.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(path = "/api/product")
public class ProductController extends BaseComponent {

    @Autowired
    private ProductService service;

    @RequestMapping(path = "/refresh/series/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CommonResponse<Long> refreshBySeries(
            HttpServletRequest request, HttpServletResponse response,
            @PathVariable("id") long id
    ) {
        logger.info("Refresh product", "Request: series={}", id);
        CommonResponse.Builder<Long> builder = CommonResponse.builder();
        CommonResponse<Long> result;
        try {
            long data = service.refreshProducts(id);
            result = builder.ok(data);
        } catch ( Throwable error ) {
            logger.error(error, "Refresh product", "Internal server error");
            result = builder.error(500, "Internal server error");
        }
        logger.info("Refresh product", "Result: body={}", result);
        return result;
    }

    @RequestMapping(path = "/refresh/series", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CommonResponse<Long> refreshAll(
            HttpServletRequest request, HttpServletResponse response
    ) {
        logger.info("Refresh product", "Request: all");
        CommonResponse.Builder<Long> builder = CommonResponse.builder();
        CommonResponse<Long> result;
        try {
            long data = service.refreshProducts();
            result = builder.ok(data);
        } catch ( Throwable error ) {
            logger.error(error, "Refresh product", "Internal server error");
            result = builder.error(500, "Internal server error");
        }
        logger.info("Refresh product", "Result: body={}", result);
        return result;
    }

}
