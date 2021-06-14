package com.projetointegrador.illuminer.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.projetointegrador.illuminer.controller"))
				.paths(PathSelectors.any()).build().apiInfo(metadata()).useDefaultResponseMessages(false)
				.globalResponses(HttpMethod.GET,responseMessageForGET())
				.globalResponses(HttpMethod.POST,responseMessageForPOST())
				.globalResponses(HttpMethod.PUT,responseMessageForPUT())
				.globalResponses(HttpMethod.DELETE,responseMessageForDELETE());
	}
	
	public static ApiInfo metadata() {
		return new ApiInfoBuilder()
				.title("illuminer API")
				.description("API desenvolvida pelo grupo 01 da turma 21 para o Projeto Integrador do bootcamp da Generation Brasil.")
				.version("1.0.0")
				.license("Apache License Version 2.0")
				.licenseUrl("http://localhost:8080/swagger-ui/")
				.contact(new Contact("Grupo 01 illuminer", "https://github.com/DavidCostaBKE/Projeto-Integrador", ""))
				.build();
	}
	
	private static List<Response> responseMessageForGET(){
		List<Response> respostas = new ArrayList<>();
		respostas.add(new ResponseBuilder().code("200").description("Sucesso").build());
		respostas.add(new ResponseBuilder().code("401").description("Não autorizado!").build());
		respostas.add(new ResponseBuilder().code("404").description("Não encontrado").build());
		respostas.add(new ResponseBuilder().code("400").description("Requisão inválida").build());
		respostas.add(new ResponseBuilder().code("405").description("Método não pemitido").build());
		respostas.add(new ResponseBuilder().code("500").description("Erro no servidor!").build());
		return respostas;
	}
	
	private static List<Response> responseMessageForPOST(){
		List<Response> respostas = new ArrayList<>();
		respostas.add(new ResponseBuilder().code("201").description("Criado").build());
		respostas.add(new ResponseBuilder().code("401").description("Não autorizado!").build());
		respostas.add(new ResponseBuilder().code("404").description("Não encontrado").build());
		respostas.add(new ResponseBuilder().code("400").description("Requisão inválida").build());
		respostas.add(new ResponseBuilder().code("405").description("Método não pemitido").build());
		respostas.add(new ResponseBuilder().code("500").description("Erro no servidor!").build());
		return respostas;
	}
	
	private static List<Response> responseMessageForPUT(){
		List<Response> respostas = new ArrayList<>();
		respostas.add(new ResponseBuilder().code("200").description("Sucesso").build());
		respostas.add(new ResponseBuilder().code("401").description("Não autorizado!").build());
		respostas.add(new ResponseBuilder().code("404").description("Não encontrado").build());
		respostas.add(new ResponseBuilder().code("400").description("Requisão inválida").build());
		respostas.add(new ResponseBuilder().code("405").description("Método não pemitido").build());
		respostas.add(new ResponseBuilder().code("500").description("Erro no servidor!").build());
		return respostas;
	}
	
	private static List<Response> responseMessageForDELETE(){
		List<Response> respostas = new ArrayList<>();
		respostas.add(new ResponseBuilder().code("204").description("Sem conteúdo").build());
		respostas.add(new ResponseBuilder().code("401").description("Não autorizado!").build());
		respostas.add(new ResponseBuilder().code("404").description("Não encontrado").build());
		respostas.add(new ResponseBuilder().code("400").description("Requisão inválida").build());
		respostas.add(new ResponseBuilder().code("405").description("Método não pemitido").build());
		respostas.add(new ResponseBuilder().code("500").description("Erro no servidor!").build());
		return respostas;
	}
}
