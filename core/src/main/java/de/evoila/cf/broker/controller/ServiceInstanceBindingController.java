package de.evoila.cf.broker.controller;

import de.evoila.cf.broker.exception.*;
import de.evoila.cf.broker.model.ErrorMessage;
import de.evoila.cf.broker.model.ServiceInstanceBindingRequest;
import de.evoila.cf.broker.model.ServiceInstanceBindingResponse;
import de.evoila.cf.broker.service.impl.BindingServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Map;

/** @author Johannes Hiemer. */
@Controller
@RequestMapping(value = "/v2/service_instances")
public class ServiceInstanceBindingController extends BaseController {

	private final Logger log = LoggerFactory.getLogger(ServiceInstanceBindingController.class);

	public static final String SERVICE_INSTANCE_BINDING_BASE_PATH = "/v2/service_instances/{instanceId}/service_bindings";

	@Autowired
	private BindingServiceImpl bindingService;

	@PutMapping(value = "/{instanceId}/service_bindings/{bindingId}")
	public ResponseEntity<ServiceInstanceBindingResponse> bindServiceInstance(
			@PathVariable("instanceId") String instanceId, @PathVariable("bindingId") String bindingId,
			@Valid @RequestBody ServiceInstanceBindingRequest request)
					throws ServiceInstanceDoesNotExistException, ServiceInstanceBindingExistsException,
					ServiceBrokerException, ServiceDefinitionDoesNotExistException {

		log.debug("PUT: " + SERVICE_INSTANCE_BINDING_BASE_PATH + "/{bindingId}"
				+ ", bindServiceInstance(), instanceId = " + instanceId + ", bindingId = " + bindingId);

		Map<String, String> bindResource = request.getBindResource();
		String route = (bindResource != null) ? bindResource.get("route") : null;
		ServiceInstanceBindingResponse response = bindingService.createServiceInstanceBinding(bindingId, instanceId, request, route);

		log.debug("ServiceInstanceBinding Created: " + bindingId);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/{instanceId}/service_bindings/{bindingId}")
	public ResponseEntity<String> deleteServiceInstanceBinding(@PathVariable("instanceId") String instanceId,
			@PathVariable("bindingId") String bindingId, @RequestParam("service_id") String serviceId,
			@RequestParam("plan_id") String planId) throws ServiceBrokerException {

		log.debug("DELETE: " + SERVICE_INSTANCE_BINDING_BASE_PATH + "/{bindingId}"
				+ ", deleteServiceInstanceBinding(),  serviceInstance.id = " + instanceId + ", bindingId = " + bindingId
				+ ", serviceId = " + serviceId + ", planId = " + planId);

		try {
			bindingService.deleteServiceInstanceBinding(bindingId, planId);
		} catch (ServiceInstanceBindingDoesNotExistsException e) {
			return new ResponseEntity<>("{}", HttpStatus.GONE);
		}

		log.debug("ServiceInstanceBinding Deleted: " + bindingId);

		return new ResponseEntity<>("{}", HttpStatus.OK);
	}

	@ExceptionHandler(ServiceInstanceDoesNotExistException.class)
	@ResponseBody
	public ResponseEntity<ErrorMessage> handleException(ServiceInstanceDoesNotExistException ex) {
		return processErrorResponse(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(ServiceInstanceBindingExistsException.class)
	@ResponseBody
	public ResponseEntity<ErrorMessage> handleException(ServiceInstanceBindingExistsException ex) {
		return processErrorResponse(ex.getMessage(), HttpStatus.CONFLICT);
	}

}
