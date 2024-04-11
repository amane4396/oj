package org.oj.exception;

import org.oj.constant.ResCode;
import org.oj.util.JsonRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 *
 * @author DH
 * @create 2022-06-20
 */
@Slf4j
@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(ActiveException.class)
    @ResponseBody
    public JsonRes handleException(ActiveException ex) {
        switch (ex.getCode()) {
            case ResCode.SUCCESS:
                return JsonRes.success(ex.getMsg());
            case ResCode.BAD:
                return JsonRes.bad(ex.getMsg());
            case ResCode.UNAUTHORIZED:
                return JsonRes.unauthorized();
            case ResCode.ACCESS_DENIED:
                return JsonRes.accessDenied();
            case ResCode.NOT_FOUND:
                return JsonRes.notFound();
            case ResCode.FAIL:
                return JsonRes.fail(ex.getMsg());
            default:
                return JsonRes.bad();
        }
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public JsonRes handleException(Exception ex) {
        log.error(ex.toString(), ex);
        return JsonRes.fail();
    }
}
