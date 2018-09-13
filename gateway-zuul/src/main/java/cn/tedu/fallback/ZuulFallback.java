package cn.tedu.fallback;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

//@Component
public class ZuulFallback implements ZuulFallbackProvider{

	@Override
	public String getRoute() {
		return "provider-user";
	}

	@Override
	public ClientHttpResponse fallbackResponse() {
		return new ClientHttpResponse() {
			
			@Override  //设置头信息；
			public HttpHeaders getHeaders() {
				HttpHeaders httpHeaders = new HttpHeaders();
				httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
				return httpHeaders;
			}
			
			@Override //设置内容
			public InputStream getBody() throws IOException {
				return new ByteArrayInputStream(new String("tony02").getBytes());
			}
			 
			@Override //响应状态文本；
			public String getStatusText() throws IOException {
				return HttpStatus.BAD_REQUEST.getReasonPhrase();
			}
			
			@Override //响应状态码;
			public HttpStatus getStatusCode() throws IOException {
				return HttpStatus.BAD_REQUEST;
			}
			
			@Override 
			public int getRawStatusCode() throws IOException {
				return HttpStatus.BAD_REQUEST.value();
			}
			
			@Override
			public void close() {
				
			}

		};
	}

}
