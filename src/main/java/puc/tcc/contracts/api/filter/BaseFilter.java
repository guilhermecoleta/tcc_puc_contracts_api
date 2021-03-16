package puc.tcc.contracts.api.filter;

import javax.servlet.http.HttpServletResponse;

public class BaseFilter {

    public HttpServletResponse corsResponse(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, Content-Length, X-Requested-With");
        return response;
    }
}
