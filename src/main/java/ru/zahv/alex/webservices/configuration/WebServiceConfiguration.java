package ru.zahv.alex.webservices.configuration;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfiguration {
	@Bean
	public ServletRegistrationBean messageDispatcherServlet(
			ApplicationContext applicationContext) {
		MessageDispatcherServlet messageServlet = new MessageDispatcherServlet();
		messageServlet.setApplicationContext(applicationContext);
		messageServlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(messageServlet, "/ws/*");
	}

	@Bean(name = "hello")
	public DefaultWsdl11Definition defaultWsdl11Definition(
			XsdSchema helloSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("HelloPort");
		wsdl11Definition.setLocationUri("/ws");
		wsdl11Definition.setTargetNamespace("http://www.example.org/hello");
		wsdl11Definition.setSchema(helloSchema);
		return wsdl11Definition;
	}

	@Bean
	public XsdSchema helloSchema() {
		return new SimpleXsdSchema(new ClassPathResource("hello.xsd"));
	}
}
