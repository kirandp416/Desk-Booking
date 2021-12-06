package uk.ac.cf.nsa.team2.deskbookingapp.wrapper;

import org.apache.commons.lang3.StringUtils;
import uk.ac.cf.nsa.team2.deskbookingapp.utils.XssFilterUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @description:
 * @author: yiting zheng
 * @time: 2021/12/2
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

    HttpServletRequest orgRequest = null;

    private boolean isIncludeRichText = false;
    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request The request to wrap
     * @throws IllegalArgumentException if the request is null
     */
    public XssHttpServletRequestWrapper(HttpServletRequest request, boolean isIncludeRichText) {
        super(request);
        orgRequest = request;
        this.isIncludeRichText = isIncludeRichText;
    }

    @Override
    public String getParameter(String name) {
        if (("content".equals(name) || name.endsWith("WithHtml")) && !isIncludeRichText) {
            return super.getParameter(name);
        }
        name = XssFilterUtil.clean(name);
        String value = super.getParameter(name);
        if (StringUtils.isNotBlank(value)) {
            value = XssFilterUtil.clean(value);
        }
        return value;
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] arr = super.getParameterValues(name);
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = XssFilterUtil.clean(arr[i]);
            }
        }
        return arr;
    }

    @Override
    public String getHeader(String name) {
        name = XssFilterUtil.clean(name);
        String value = super.getHeader(name);
        if (StringUtils.isNotBlank(value)) {
            value = XssFilterUtil.clean(value);
        }
        return value;
    }

    public HttpServletRequest getOrgRequest() {
        return orgRequest;
    }

    public static HttpServletRequest getOrgRequest(HttpServletRequest req) {
        if (req instanceof XssHttpServletRequestWrapper) {
            return ((XssHttpServletRequestWrapper) req).getOrgRequest();
        }
        return req;
    }
}
