package io.ziyi.spider.showmax.web;

import io.ziyi.spider.showmax.common.BaseComponent;
import io.ziyi.spider.showmax.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(path = "/api/movie")
public class MovieController extends BaseComponent {

    @Autowired
    private MediaService service;

    @RequestMapping(path = "/refresh", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CommonResponse<Object> refresh(HttpServletRequest request, HttpServletResponse response) {
        logger.info("Refresh movies", "Request");
        CommonResponse.Builder<Object> builder = CommonResponse.builder();
        CommonResponse<Object> result;
        try {
            service.refreshMovie();
            result = builder.ok(null);
        } catch ( Throwable error ) {
            logger.error(error, "Refresh movie", "Internal server error");
            result = builder.error(500, "Internal server error");
        }
        logger.info("Refresh movies", "Result: body={}", result);
        return result;
    }

    @RequestMapping(path = "/download/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CommonResponse<Integer> download(
            HttpServletRequest request, HttpServletResponse response,
            @PathVariable("id") String id
    ) {
        logger.info("Download movie", "Request: id={}", id);
        CommonResponse.Builder<Integer> builder = CommonResponse.builder();
        CommonResponse<Integer> result;
        try {
            int count = service.downloadMovie(id);
            result = builder.ok(count);
        } catch ( Throwable error ) {
            logger.error(error, "Download movie", "Internal server error");
            result = builder.error(500, "Internal server error");
        }
        logger.info("Download movie", "Result: id={}, body={}", id, result);
        return result;
    }

    @RequestMapping(path = "/download/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CommonResponse<Object> downloadAll(
            HttpServletRequest request, HttpServletResponse response
    ) {
        logger.info("Download movies", "");
        CommonResponse.Builder<Object> builder = CommonResponse.builder();
        CommonResponse<Object> result;
        try {
            service.downloadAll();;
            result = builder.ok(null);
        } catch ( Throwable error ) {
            logger.error(error, "Download movies", "Internal server error");
            result = builder.error(500, "Internal server error");
        }
        logger.info("Download movies", "Result: body={}", result);
        return result;
    }

    @RequestMapping(path = "/query", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CommonResponse<Object> query(
            HttpServletRequest request, HttpServletResponse response,
            @RequestParam(name = "start") int start,
            @RequestParam(name = "num") int num
    ) {
        logger.info("Query movie", "Request: start={}, num={}", start, num);
        CommonResponse.Builder<Object> builder = CommonResponse.builder();
        CommonResponse<Object> result;
        try {
            service.list(start, num);
            result = builder.ok(null);
        } catch ( Throwable error ) {
            logger.error(error, "Query movie", "Internal server error");
            result = builder.error(500, "Internal server error");
        }
        logger.info("Query movie", "Result: start={}, num={}", start, num);
        return result;
    }
}
