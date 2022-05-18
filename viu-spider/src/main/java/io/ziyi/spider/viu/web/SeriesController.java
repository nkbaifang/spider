package io.ziyi.spider.viu.web;

import io.ziyi.spider.viu.common.BaseComponent;
import io.ziyi.spider.viu.service.SeriesService;
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
@RequestMapping(path = "/api/series")
public class SeriesController extends BaseComponent {

    @Autowired
    private SeriesService service;

    @RequestMapping(path = "/refresh/category/{category}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CommonResponse<Object> refreshWithCategory(
            HttpServletRequest request, HttpServletResponse response,
            @PathVariable("category") long category
    ) {
        logger.info("Refresh category", "Request: category={}", category);
        CommonResponse.Builder<Object> builder = CommonResponse.builder();
        CommonResponse<Object> result;
        try {
            service.refreshSeries(category);
            result = builder.ok(null);
        } catch ( Throwable error ) {
            logger.error(error, "Refresh category", "Internal server error");
            result = builder.error(500, "Internal server error");
        }
        logger.info("Refresh category", "Result: body={}", result);
        return result;
    }

    @RequestMapping(path = "/refresh/categories", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CommonResponse<Object> refreshAll(
            HttpServletRequest request, HttpServletResponse response
    ) {
        logger.info("Refresh categories", "Request: with all categories");
        CommonResponse.Builder<Object> builder = CommonResponse.builder();
        CommonResponse<Object> result;
        try {
            service.refreshSeries();
            result = builder.ok(null);
        } catch ( Throwable error ) {
            logger.error(error, "Refresh categories", "Internal server error");
            result = builder.error(500, "Internal server error");
        }
        logger.info("Refresh categories", "Result: body={}", result);
        return result;
    }
}
