package com.wsguardian.soapwebservices.soap;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.wsguardian.courses.CourseDetails;
import com.wsguardian.courses.DeleteCourseDetailsRequest;
import com.wsguardian.courses.DeleteCourseDetailsResponse;
import com.wsguardian.courses.GetAllCourseDetailsRequest;
import com.wsguardian.courses.GetAllCourseDetailsResponse;
import com.wsguardian.courses.GetCourseDetailsRequest;
import com.wsguardian.courses.GetCourseDetailsResponse;
import com.wsguardian.soapwebservices.soap.bean.Course;
import com.wsguardian.soapwebservices.soap.exception.CourseNotFoundException;
import com.wsguardian.soapwebservices.soap.service.CourseDetailsService;
import com.wsguardian.soapwebservices.soap.service.CourseDetailsService.Status;

@Endpoint
public class CourseDetailsEndpoint {
	
	@Autowired
	CourseDetailsService service;
	// method
	// input - GetCourseDetailsRequest
	// output - GetCourseDetailsResponse

	// http://in28minutes.com/courses
	// GetCourseDetailsRequest
	@PayloadRoot(namespace = "http://wsguardian.com/courses", localPart = "GetCourseDetailsRequest")
	@ResponsePayload
	public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request,MessageContext context) {
		//Extraer request del message context
	
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    try {
	        context.getRequest().writeTo(outputStream);
	        String httpMessage = new String(outputStream.toByteArray());
	        System.out.println("message context ::::::  "+ httpMessage);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    
	    // a byte para base 64
	    String encodedString = Base64.getEncoder().encodeToString(outputStream.toByteArray());
	    System.out.println("message context en Base 64::::::  "+ encodedString);
		
	    //rest llamado
	    RestTemplate rt = new RestTemplate();
        rt.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        rt.getMessageConverters().add(new StringHttpMessageConverter());

        String uri = new String("http://localhost:9090/signature");
	    
        ResponseEntity<Boolean> validacion = rt.postForEntity(uri, encodedString,boolean.class);
        System.out.println("Respuesta validacion firma::::::  "+ validacion.getBody());
        
		//logica para realizar la respuesta de la peticion de getcoursedetails 
		Course course = service.findById(request.getId());
		
		if(course == null) {
			throw new CourseNotFoundException("Invalid course id " + request.getId());
		}
		return mapCourseDetails(course);
	}

	private GetCourseDetailsResponse mapCourseDetails(Course course) {
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();
		
		response.setCourseDetails(mapCourse(course));
		
		return response;
	}
	
	private GetAllCourseDetailsResponse mapAllCourseDetails(List<Course> courses) {
		GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();
		
		for(Course c:courses) {
			CourseDetails mapCourse = mapCourse(c);
			response.getCourseDetails().add(mapCourse);
		}
		
		return response;
	}

	private CourseDetails mapCourse(Course course) {
		CourseDetails courseDetails = new CourseDetails();
		courseDetails.setId(course.getId());
		courseDetails.setName(course.getName());
		courseDetails.setDescription(course.getDescription());
		return courseDetails;
	}
	
	@PayloadRoot(namespace = "http://wsguardian.com/courses", localPart = "GetAllCourseDetailsRequest")
	@ResponsePayload
	public GetAllCourseDetailsResponse processAllCourseDetailsRequest(
			@RequestPayload GetAllCourseDetailsRequest request) {

		List<Course> courses = service.findAll();

		return mapAllCourseDetails(courses);
	}
	
	@PayloadRoot(namespace = "http://wsguardian.com/courses", localPart = "DeleteCourseDetailsRequest")
	@ResponsePayload
	public DeleteCourseDetailsResponse deleteCourseDetailsRequest(
			@RequestPayload DeleteCourseDetailsRequest request) {

		Status status = service.deleteById(request.getId());
		DeleteCourseDetailsResponse response = new DeleteCourseDetailsResponse();
		
		response.setStatus(mapStatus(status));
		
		return response;
		
	}

	private com.wsguardian.courses.Status mapStatus(Status status) {
		// TODO Auto-generated method stub
		if(status==Status.FAILURE) {
			return com.wsguardian.courses.Status.FAILURE;
		}
		return com.wsguardian.courses.Status.SUCCESS;
	}

}
