package io.ziyi.spider.viu.web;

import io.ziyi.spider.viu.common.BaseComponent;
import io.ziyi.spider.viu.service.M3uService;
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
@RequestMapping(path = "/api/m3u8")
public class M3u8Controller extends BaseComponent {

    @Autowired
    private M3uService service;

    @RequestMapping(path = "/download/product/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CommonResponse<Integer> downloadByProduct(
            HttpServletRequest request, HttpServletResponse response,
            @PathVariable("id") long id
    ) {
        logger.info("Download m3u8", "Request: product={}", id);
        CommonResponse.Builder<Integer> builder = CommonResponse.builder();
        CommonResponse<Integer> result;
        try {
            int data = service.refreshByProduct(id);
            result = builder.ok(data);
        } catch ( Throwable error ) {
            logger.error(error, "Download m3u8", "Internal server error");
            result = builder.error(500, "Internal server error");
        }
        logger.info("Download m3u8", "Result: product={}, body={}", id, result);
        return result;
    }

    @RequestMapping(path = "/download/series/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CommonResponse<Integer> downloadBySeries(
            HttpServletRequest request, HttpServletResponse response,
            @PathVariable("id") long id
    ) {
        logger.info("Download m3u8", "Request: series={}", id);
        CommonResponse.Builder<Integer> builder = CommonResponse.builder();
        CommonResponse<Integer> result;
        try {
            int data = service.refreshBySeries(id);
            result = builder.ok(data);
        } catch ( Throwable error ) {
            logger.error(error, "Download m3u8", "Internal server error");
            result = builder.error(500, "Internal server error");
        }
        logger.info("Download m3u8", "Result: series={}, body={}", id, result);
        return result;
    }

    @RequestMapping(path = "/status", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CommonResponse<Integer> status(
            HttpServletRequest request, HttpServletResponse response
    ) {
        logger.info("Check status", "Request: ");
        CommonResponse.Builder<Integer> builder = CommonResponse.builder();
        CommonResponse<Integer> result;
        try {
            int size = service.getTasksCount();
            result = builder.ok(size);
        } catch ( Throwable error ) {
            logger.error(error, "Check status", "Internal server error");
            result = builder.error(500, "Internal server error");
        }
        logger.info("Check status", "Result: body={}", result);
        return result;
    }
}
