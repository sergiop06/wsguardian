package com.wsguardian.engine.process;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.wsguardian.engine.entities.Policy;
import com.wsguardian.engine.entities.SoapRequestEntity;
import com.wsguardian.engine.repository.ServiceRepository;

@Service
public class EngineServiceBean {

	@Autowired
	private ServiceRepository serviceRepository;

	private final String backendAdminPoliticas;

	public EngineServiceBean() {
		this.backendAdminPoliticas = System.getenv()
				.getOrDefault("ADMINISTRADOR_POLITICAS", "http://localhost:8090/orquestador").toString();
	}

	@SuppressWarnings("unused")
	private String processWSDL(String path) {
		// consulta a BD

		System.out.println("path " + path);
		if (path.equals("/isbnService.wsdl")) {

			// String site="http://192.168.0.15:8080/ws/courses.wsdl";
			String site = "http://webservices.daehosting.com/services/isbnservice.wso?WSDL";
			String filename = "isbnservice.wsdl";

			String WSDL_URL = System.getenv()
					.getOrDefault("WSDLURL", "http://192.168.0.11:8081/service/isbnService.wsdl").toString();

			try {
				URL url = new URL(site);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				int filesize = connection.getContentLength();
				float totalDataRead = 0;

				BufferedInputStream in = new BufferedInputStream(connection.getInputStream());
				FileOutputStream fos = new FileOutputStream(filename);
				BufferedOutputStream bout = new BufferedOutputStream(fos, 1024);
				byte[] data = new byte[1024];
				int i = 0;
				System.out.println("hp-1");
				while ((i = in.read(data, 0, 1024)) >= 0) {
					totalDataRead = totalDataRead + i;
					bout.write(data, 0, i);
				}
				bout.close();
				in.close();
			} catch (Exception e) {
				// TODO: handle exception
			}

			// MODIFY WSDL FILE
			Reader fileReader;
			try {
				fileReader = new FileReader("isbnservice.wsdl");
				BufferedReader bufReader = new BufferedReader(fileReader);

				StringBuilder sb = new StringBuilder();
				String line = bufReader.readLine();
				while (line != null) {
					if (line.contains("location")) {
						String replaceString = line.replaceAll("location=\".*\"", "location=\"" + WSDL_URL + "\"");
						sb.append(replaceString).append("\n");
					} else {
						sb.append(line).append("\n");
					}

					line = bufReader.readLine();
				}
				String xml2String = sb.toString();
				System.out.println(xml2String);
				bufReader.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return "melo";
	}

	public void processRequest(HttpServletRequest request, HttpServletResponse response) {

		/*
		 * Decisiones de arquitectura : Como pasar informacion Planteamiento : pasar un
		 * DTO llamado SoaprequestEntity que contiene los headers y el body del
		 * HttpServletRequest
		 */
		SoapRequestEntity requestInfoDTO = new SoapRequestEntity();
		// requestInfoDTO.setRequest(request);
		// requestInfoDTO.setResponse(response);

		/*
		 *
		 * Paso 2 : Sacar headers y body [Envelope] de la peticion HTTP [request] PAso 3
		 * : Verificar politicas asociadas a la peticion Paso 4 : Llamar al orquestador
		 */

		System.out.println("request->>" + request.getPathInfo());
		String path = request.getPathInfo();

		/*
		 * Full path
		 */
		String fullUri = request.getScheme() + "://" + // "http" + "://
				request.getServerName() + // "myhost"
				":" + // ":"
				request.getServerPort() + // "8080"
				request.getRequestURI() + // "/people"
				"?" + // "?"
				request.getQueryString(); // "lastname=Fox&age=30"

		String fullBody = null;

		// Lista de politicas a aplicar al servicio
		ObjectMapper om = new ObjectMapper();
		try {

			// lista de politicas
			List<Policy> listaPoliticas = consultarPeticionEnBD(path);
			consultarPeticionEnBD1(path);

			List<Policy> politicas = this.consultarPeticionEnBD(path);
			String jsonPolitica = om.writeValueAsString(politicas);
			String jsonPeticion = om.writeValueAsString(path);

			request.setAttribute("politicas", jsonPolitica);
			request.setAttribute("peticion", jsonPeticion);

			final String uri = this.backendAdminPoliticas;

			// HTTPRequestHeaders como un mapa , se guarda en headersMap
			HttpHeaders headers = new HttpHeaders();
			Map<String, String> headersMap = new HashMap<String, String>();
			Enumeration headerNames = request.getHeaderNames();
			// System.out.println("Headers==");
			while (headerNames.hasMoreElements()) {
				String key = (String) headerNames.nextElement();
				String value = request.getHeader(key);
				headers.add(key, value);
				headersMap.put(key, value);
				// System.out.println(key+ " - "+headersMap.get(key) +"\n");
			}

			// Parametros del request como mapa , se guarda en parametersMap
			Map<String, String[]> parametersMap = new HashMap<String, String[]>();
			Map<String, String[]> parameters = request.getParameterMap();
			// System.out.println("parameters==");
			for (String key : parameters.keySet()) {
				// los parametros que estoy agregando no logro imprimirlos como string :(
				parametersMap.put(key, parameters.get(key));
				// System.out.println(key+" - "+parametersMap.get(key)+"\n");
			}

			// HttpServletRequest body as a string (donde esta contenido el envelope de la
			// petición)
			String encodeType = "";
			if (request.getCharacterEncoding() != null || request.getCharacterEncoding() != "") {
				encodeType = request.getCharacterEncoding();
			} else {
				encodeType = "UTF-8";
			}

			try {
				fullBody = org.apache.commons.io.IOUtils.toString(request.getInputStream(), encodeType);
				// System.out.println("fullbody== "+fullBody+"\n");
			} catch (IOException e1) {
				// System.out.println("no pudo completar la conversion del request body a
				// string");
				e1.printStackTrace();
			}

			// RequestInfoDTO
			requestInfoDTO.setHeaders(headersMap);
			requestInfoDTO.setParameters(parametersMap);
			requestInfoDTO.setPoliticas(politicas);
			requestInfoDTO.setBody(fullBody);
			requestInfoDTO.setFullUri(fullUri);
			if (fullUri.toUpperCase().contains("WSDL")) {
				requestInfoDTO.setWsdl(true);
			} else {
				requestInfoDTO.setWsdl(false);
			}
			requestInfoDTO.setPathInfo(request.getPathInfo());

			// ---------DTO A JSON-------------------------------------------
			// create the mapper
			ObjectMapper mapper = new ObjectMapper();

			// enable pretty printing
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			mapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);

			// Serialise the object
			// byte[] json = mapper.writeValueAsBytes(requestInfoDTO);
			String jsonString = mapper.writeValueAsString(requestInfoDTO);
			System.out.println("JSOn object==\n" + jsonString + "\n");

			// String encodeJson = new String
			// (Base64.getMimeEncoder().encode(jsonString.getBytes("UTF-8")));
			String encodeJson = DatatypeConverter.printBase64Binary(jsonString.getBytes());
			System.out.println("Base64 object== \n" + encodeJson + "\n");
			// -------------------------------------------------------------------

			// -------llamado rest-----------------------------
			RestTemplate rt = new RestTemplate();
			MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
			converter.setSupportedMediaTypes(Arrays.asList(MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON));
			rt.getMessageConverters().add(0, converter);
			rt.getMessageConverters().add(new StringHttpMessageConverter());

			ResponseEntity<Boolean> validacion = rt.postForEntity("http://localhost:8080/orquestador", encodeJson,
					boolean.class);

			// -----------------------------------------------

			/*
			 * ResponseEntity<Boolean> validacion =
			 * rt.postForEntity("http://192.168.1.21:8090/new",requestInfoDTO,
			 * boolean.class); //ResponseEntity<Boolean> validacion2 = rt.postForEntity(uri,
			 * encodedRequest,boolean.class);
			 * System.out.println("validacion-Response-REST::::::  " +
			 * validacion.getBody());
			 * 
			 * 
			 * /* HttpEntity<Map<String, String>> requestOrquestador = new
			 * HttpEntity<Map<String, String>>( mapRequestParams, headers);
			 * 
			 * 
			 * //String encodedRequest = codificarRequest(requestOrquestador);
			 * 
			 * ResponseEntity<SoapRequestEntity> respnseOrquestador =
			 * restTemplate.exchange(uri, HttpMethod.POST, encodedRequest, STRING.class);
			 */

			// rest llamado
			/*
			 * RestTemplate rt = new RestTemplate(); rt.getMessageConverters().add(new
			 * MappingJackson2HttpMessageConverter()); rt.getMessageConverters().add(new
			 * StringHttpMessageConverter()); ResponseEntity<Boolean> validacion =
			 * rt.postForEntity(uri, encodedRequest,boolean.class);
			 * System.out.println("validacion-Response-REST::::::  " +
			 * validacion.getBody());
			 */
		} catch (Exception e) {
			System.out.println("ERROR: " + e.toString());

		}

	}

	private List<Policy> consultarPeticionEnBD(String peticion) {
		List<Policy> politicas = new ArrayList<Policy>();
		// Aquí se debe hacer una petición a la base de datos
		if (peticion.equals("/IsValidISBN13")) {
			politicas.add(new Policy(1, "politica_calendario", "horarios específicos para tráfico"));
			// politicas.add(new Politica(2, "politica_firmas_digitales",
			// "firmascertificadas"));
		} else if (peticion.equals("/IsValidISBN10")) {
			// politicas.add(new Politica(1, "politica_calendario", "horarios específicos
			// para tráfico"));
			politicas.add(new Policy(2, "politica_firmas_digitales", "firmas certificadas"));
		}

		return politicas;

	}

	private void consultarPeticionEnBD1(String peticion) {

		List<Policy> politicas = new ArrayList<Policy>();
		System.out.println("ENTRE bd1");
		// Aquí se debe hacer una petición a la base de datos
		// Iterable<Wsg_service> serviciosBD = this.guardianRepository.findAll();

		System.out.println("count=" + serviceRepository.count());
	}

}
