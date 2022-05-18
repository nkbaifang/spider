package io.ziyi.spider.viu.web;

import io.ziyi.spider.viu.common.BaseComponent;
import io.ziyi.spider.viu.service.StreamService;
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
@RequestMapping(path = "/api/stream")
public class StreamController extends BaseComponent {

    @Autowired
    private StreamService service;

    @RequestMapping(path = "/refresh/series/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CommonResponse<Long> refreshSeries(
            HttpServletRequest request, HttpServletResponse response,
            @PathVariable("id") long id
    ) {
        logger.info("Refresh streams", "Request: series={}", id);
        CommonResponse.Builder<Long> builder = CommonResponse.builder();
        CommonResponse<Long> result;
        try {
            long data = service.refreshStreams(id);
            result = builder.ok(data);
        } catch ( Throwable error ) {
            logger.error(error, "Refresh streams", "Internal server error");
            result = builder.error(500, "Internal server error");
        }
        logger.info("Refresh streams", "Result: body={}", result);
        return result;
    }

    @RequestMapping(path = "/refresh/all", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CommonResponse<Integer> refreshSeries(
            HttpServletRequest request, HttpServletResponse response
    ) {
        logger.info("Refresh streams", "Request: all");
        CommonResponse.Builder<Integer> builder = CommonResponse.builder();
        CommonResponse<Integer> result;
        try {
            int data = service.refreshStreams();
            result = builder.ok(data);
        } catch ( Throwable error ) {
            logger.error(error, "Refresh series", "Internal server error");
            result = builder.error(500, "Internal server error");
        }
        logger.info("Refresh streams", "Result: body={}", result);
        return result;
    }
}
